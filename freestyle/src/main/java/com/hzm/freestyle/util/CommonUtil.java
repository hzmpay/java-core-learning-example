package com.hzm.freestyle.util;

import com.hzm.freestyle.jdk.FunctionExecute;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年10月24日
 */
public class CommonUtil {

    private static final int DEFAULT_COUNT = 100;

    /**
     * 方法执行时间统计
     *
     * @param functionExecute
     * @param count
     * @return void
     * @author Hezeming
     */
    public static void timeStat(FunctionExecute functionExecute, int count) {
        final long stat = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            functionExecute.run();
        }
        System.out.println(System.currentTimeMillis() - stat);
    }

    /**
     * 方法执行时间统计
     *
     * @param functionExecute
     * @return void
     * @author Hezeming
     */
    public static void timeStat(FunctionExecute functionExecute) {
        timeStat(functionExecute, DEFAULT_COUNT);
    }

}

