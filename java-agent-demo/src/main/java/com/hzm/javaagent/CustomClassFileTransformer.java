package com.hzm.javaagent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 统计方法耗时
 *
 * @author hezeming
 * @version 1.0
 * @date 2022年12月19日
 */
public class CustomClassFileTransformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        try {
            ClassPool classPool = ClassPool.getDefault();
            className = className.replace("/", ".");
            CtClass ctClass = classPool.get(className);
            CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
            for (CtMethod ctMethod : declaredMethods) {
                if ("say".equals(ctMethod.getName())) {
                    //加一个局部变量
                    ctMethod.addLocalVariable("methodName", classPool.get("java.lang.String"));
                    //方法内部增加一行代码
                    ctMethod.insertBefore("methodName = \"" + ctMethod.getName() + "\";");

                    // 增加统计方法耗时
                    ctMethod.addLocalVariable("start" , CtClass.longType);
                    ctMethod.insertBefore("start = System.currentTimeMillis();");
                    ctMethod.insertAfter("System.out.println($class + \" \" + methodName +  \" time : \" + (System.currentTimeMillis() - start) + \"ms\");");
                    return ctClass.toBytecode();
                }

            }

        } catch (Exception e) {
        }

//        System.out.println("类名 = " + className);
        return classfileBuffer;
    }
}
