package Plugins;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.lang.reflect.Method;
import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JOptionPane;

import java.util.*;

import DataStore.DataStore;
import UI.App;
import UI.IApp;

public class CustomClassLoader extends ClassLoader {

    private final String jarPath;

    public CustomClassLoader(String jarPath) {
        this.jarPath = jarPath;
    }

    protected List<Class<?>> findPluginClasses(String className) throws ClassNotFoundException {
        Class<?> classInterface = Class.forName(className);
        List<Class<?>> pluginClasses = new ArrayList<Class<?>>();

        try {
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
                    pluginClasses.add(loadedClass);
                }
            }

            jarFile.close();
            
            if (pluginClasses.size() == 0) {
                throw new ClassNotFoundException("Class not found: " + className);
            }

            return pluginClasses;
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + className, e);
        }
    }

    public void load() {
        CustomClassLoader classLoader = new CustomClassLoader(this.jarPath);

        try {
            // Load class from the jar file
            List<Class<?>> plugins = classLoader.findPluginClasses("Plugins.BasePlugin");

            for (Class<?> plugin : plugins) {                
                Object pluginInstance = plugin.getDeclaredConstructor().newInstance();
                Class<?>[] parameterTypes = { IApp.class, DataStore.class };
                Method method = plugin.getMethod("onLoad", parameterTypes);
                IApp appService = App.getInstance();
                DataStore dataService = DataStore.getInstance();
                
                method.invoke(pluginInstance, appService, dataService);
                JOptionPane.showMessageDialog(null, "Plugin loaded successfully!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
