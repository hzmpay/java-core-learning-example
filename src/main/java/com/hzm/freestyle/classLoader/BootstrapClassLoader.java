package com.hzm.freestyle.classLoader;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月10日
 */
public class BootstrapClassLoader {

    /**
     Returns the class loader for the class. Some implementations may use null to represent the bootstrap class loader. This method will return null in such implementations if this class was loaded by the bootstrap class loader.
     */
//    public ClassLoader getClassLoader() {
//        ClassLoader cl = getClassLoader0();
//        if (cl == null)
//            return null;
//        SecurityManager sm = System.getSecurityManager();
//        if (sm != null) {
//            ClassLoader ccl = ClassLoader.getCallerClassLoader();
//            if (ccl != null && ccl != cl && !cl.isAncestor(ccl)) {
//                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
//            }
//        }
//        return cl;
//    }

    public static void main(String[] args) {


    }
}
