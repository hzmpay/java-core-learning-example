package com.hzm.freestyle.classLoader.hotdeployment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hezeming
 * @version 1.0
 * @date 2022年08月01日
 */
public class CustomClassLoader extends ClassLoader {

    private String baseDir;

    /**
     * 是否开启热部署
     */
    private static volatile boolean startHotDeployment;

    private static final Map<String, Class> classCache = new HashMap<>();

    public CustomClassLoader(String baseDir, String[] classArr) {
        this.baseDir = baseDir;
        loadClassByArr(classArr);
    }

    private void loadClassByArr(String[] classArr) {
        for (String s : classArr) {
            findClass(s);
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        return super.loadClass(name);
    }

    @Override
    protected Class<?> findClass(String name) {
        Class clazz = classCache.get(name);
        if (!startHotDeployment) {
            return clazz;
        }
        StringBuffer stringBuffer = new StringBuffer(baseDir);
        String className = name.replace('.', File.separatorChar) + ".class";
        stringBuffer.append(File.separator + className);
        try {
            File classF = new File(stringBuffer.toString());
            clazz = instantiateClass(name, new FileInputStream(classF), classF.length());
            if (clazz != null) {
                classCache.put(name, clazz);
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                clazz = classCache.get(name);
                if (clazz != null) {
                    return clazz;
                }
            }
            throw new RuntimeException(e);
        }
        return clazz;
    }

    private Class instantiateClass(String name, FileInputStream fileInputStream, long len) throws IOException {
        byte[] raw = new byte[(int) len];
        fileInputStream.read(raw);
        fileInputStream.close();
        return defineClass(name, raw, 0, raw.length);
    }

}
