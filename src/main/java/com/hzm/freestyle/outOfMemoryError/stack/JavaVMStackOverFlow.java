package com.hzm.freestyle.outOfMemoryError.stack;

/**
 * Exception in thread "main" java.lang.StackOverflowError
 *
 * @author Hezeming
 * @version 1.0
 * @date 2020年08月03日
 */
public class JavaVMStackOverFlow {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    /**
     * VM Args：-Xss128k
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) {

        JavaVMStackOverFlow javaVMStackOverFlow = new JavaVMStackOverFlow();
        try {
            javaVMStackOverFlow.stackLeak();
        } catch (Exception e) {
            System.out.println("stack length: " + javaVMStackOverFlow.stackLength);
            throw e;
        }

    }
}
