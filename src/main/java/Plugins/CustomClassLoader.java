package Plugins;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.lang.reflect.Method;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import DataStore.DataStore;
import UI.App;
import UI.IApp;

public class CustomClassLoader extends ClassLoader {

    private final String jarPath;

    public CustomClassLoader(String jarPath) {
        this.jarPath = jarPath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class<?> classInterface = Class.forName(className);

        try {
            // Check if the class has already been loaded
            Class<?> clazz = findLoadedClass(className);
            if (clazz != null) {
                return clazz;
            }

            // Load the class data from a jar file
            JarFile jarFile = new JarFile(jarPath);

            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                
                if (!entry.getName().endsWith(".class") || entry.isDirectory()) {
                    continue;
                }

                URL url = new URL("jar:file:" + jarPath + "!/" + entry.getName());
                byte[] classBytes;
                try (InputStream input = url.openStream()) {
                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = input.read(buffer)) != -1) {
                        output.write(buffer, 0, bytesRead);
                    }
                    classBytes = output.toByteArray();
                }

                Class<?> loadedClass = defineClass(entry.getName().
                    replace('/', '.').substring(0, entry.getName().length() - 6), 
                    classBytes, 0, 
                    classBytes.length
                );

                if (classInterface.isAssignableFrom(loadedClass)) {
                    jarFile.close();
                    return loadedClass;
                }
            }

            jarFile.close();
            throw new ClassNotFoundException("Class not found: " + className);
            //

        //     JarEntry jarEntry = jarFile.getJarEntry(className.replace('.', '/') + ".class");
        //     if (jarEntry == null) {
        //         jarFile.close();
        //         throw new ClassNotFoundException("Class not found: " + className);
        //     }

        //     URL url = new URL("jar:file:" + jarPath + "!/" + jarEntry.getName());
        //     byte[] classBytes;
        //     try (InputStream input = url.openStream()) {
        //         ByteArrayOutputStream output = new ByteArrayOutputStream();
        //         byte[] buffer = new byte[4096];
        //         int bytesRead;
        //         while ((bytesRead = input.read(buffer)) != -1) {
        //             output.write(buffer, 0, bytesRead);
        //         }
        //         classBytes = output.toByteArray();
        //     }

        //     // Define the class using the byte array
        //     jarFile.close();
        //     return defineClass(className, classBytes, 0, classBytes.length);
        // } catch (IOException e) {
        //     throw new ClassNotFoundException("Failed to load class: " + className, e);
        // }}
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + className, e);
        }
    }

    public void load() {
        CustomClassLoader classLoader = new CustomClassLoader(this.jarPath);

        try {
            // Load class from the jar file
            Class<?> clazz = classLoader.findClass("Plugins.BasePlugin");

            Object pluginInstance = clazz.getDeclaredConstructor().newInstance();

            Class<?>[] parameterTypes = { IApp.class, DataStore.class };

            Method method = clazz.getMethod("onLoad", parameterTypes);

            IApp appService = App.getInstance();
            DataStore dataService = DataStore.getInstance();
            
            method.invoke(pluginInstance, appService, dataService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
