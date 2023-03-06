package com.hzm.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * 启动时加载agent
 *
 * @author hezeming
 * @version 1.0
 * @date 2022年12月18日
 */
public class PreMainAgent {

    /**
     * javaagent自动执行方法，注意：方法名必须是premain
     *
     * @param agentArgs       命令最后=后面的参数
     * @param instrumentation
     * @return void
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("preMain1 execute ====================================>");
        System.out.println(agentArgs);

        instrumentation.addTransformer(new CustomClassFileTransformer());
    }

    public static void premain(String agentArgs) {
        System.out.println("preMain2 execute ====================================>");
        System.out.println(agentArgs);
    }

    public static void agentmain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("runtime1 execute ====================================>");
        System.out.println(agentArgs);
    }

    public static void agentmain(String agentArgs) {
        System.out.println("runtime2 execute ====================================>");
        System.out.println(agentArgs);
    }
}
