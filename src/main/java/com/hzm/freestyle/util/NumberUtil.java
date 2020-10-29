package com.hzm.freestyle.util;

import java.text.DecimalFormat;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * 数字格式化工具类
 *
 * @author Hezeming
 * @version 1.0
 * @date 2018年5月25日
 */
public class NumberUtil {

    private static final ConcurrentHashMap<Integer, DecimalFormat> map = new ConcurrentHashMap<>();
    /**
     * 锁前缀
     */
    private static final String MONITOR = "NUMBER_FORMAT_";
    /**
     * random实例
     */
    private static final ThreadLocalRandom THREAD_LOCAL_RANDOM = ThreadLocalRandom.current();
    /**
     * 0-9数字
     */
    private static final String RAND_STR = "0123456789";

    private static final DecimalFormat formatTwo = new DecimalFormat("#.00");
    private static final DecimalFormat formatOne = new DecimalFormat("#.0");

    /**
     * 数字格式化
     *
     * @param number 数字
     * @param calc   位数
     * @return 如： number：123 ， calc：5 ，返回：00123
     * @author Hezeming
     */
    public static String format(Number number, int calc) {
        DecimalFormat df = map.get(calc);
        if (df == null) {
            String monitor = (MONITOR + calc).intern();
            synchronized (monitor) {
                if ((df = map.get(calc)) == null) {
                    final StringBuilder builder = new StringBuilder(calc);
                    IntStream.rangeClosed(1, calc).forEach(e -> builder.append("0"));
                    map.put(calc, df = new DecimalFormat(builder.toString()));
                }
            }
        }
        return df.format(number);
    }

    public static void main(String[] args) {
        System.out.println(format(123, 6));
    }


}
