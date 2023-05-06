package UI;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class CustomClassLoader extends ClassLoader {

    private final String jarPath;

    public CustomClassLoader(ClassLoader parent, String jarPath) {
        super(parent);
        this.jarPath = jarPath;
    }

    // @Override
    // public Class<?> findClass(String name) throws ClassNotFoundException {
    //     try {
    //         // Load the class data from a URL
    //         URL url = new URL("file:C:\\Users\\Asus\\OneDrive - Institut Teknologi Bandung\\Documents\\GitHub\\Tubes2_OOP_AJG\\target\\classes\\UI\\" + name + ".class");
    //         System.out.println(url);
    //         URLConnection connection = url.openConnection();
    //         InputStream input = connection.getInputStream();
    //         ByteArrayOutputStream buffer = new ByteArrayOutputStream();

    //         // Read the class data into a byte array
    //         int data = input.read();
    //         while (data != -1) {
    //             buffer.write(data);
    //             data = input.read();
    //         }

    //         input.close();

    //         // Define the class using the byte array
    //         byte[] classData = buffer.toByteArray();
    //         return defineClass(name, classData, 0, classData.length);
    //     } catch (Exception e) {
    //         throw new ClassNotFoundException("Failed to load class " + name, e);
    //     }
    // }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            // Check if the class has already been loaded
            Class<?> clazz = findLoadedClass(className);
            if (clazz != null) {
                return clazz;
            }

            // Load the class data from a jar file
            JarFile jarFile = new JarFile(jarPath);
            JarEntry jarEntry = jarFile.getJarEntry(className.replace('.', '/') + ".class");
            if (jarEntry == null) {
                throw new ClassNotFoundException("Class not found: " + className);
            }

            URL url = new URL("jar:file:" + jarPath + "!/" + jarEntry.getName());
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

            // Define the class using the byte array
            return defineClass(className, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class: " + className, e);
        }
    }

    public void load() {
        CustomClassLoader classLoader = new CustomClassLoader(ClassLoader.getSystemClassLoader(), this.jarPath);


        try {
            // Load class from the jar file
            Class<?> clazz = classLoader.loadClass("hello.HelloWorld");

            // Get all declared methods of the class
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                System.out.println("method: " + m.getName());
            }

            // Constructor<?> constructor = clazz.getDeclaredConstructor();
            // Object instance = constructor.newInstance();
            // Method createMethod = clazz.getDeclaredMethod("getSeed");
            // createMethod.invoke(instance);

            // System.out.println(createMethod.getName());

            Method method = clazz.getMethod("main", String[].class);
            method.invoke(null, new Object[]{new String[]{}});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String path = "C:\\Users\\Asus\\OneDrive - Institut Teknologi Bandung\\Documents\\GitHub\\Tubes2_OOP_AJG\\src\\main\\java\\UI\\helloworld.jar";
        CustomClassLoader loader = new CustomClassLoader(getPlatformClassLoader(), path);
        loader.load();
    }
}
