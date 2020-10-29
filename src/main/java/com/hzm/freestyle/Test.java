package com.hzm.freestyle;

import org.springframework.beans.PropertyAccessor;
import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2019年11月20日
 */
public class Test {

    public static final int num = 100000;

    private static final ThreadLocal<Object> prototypesCurrentlyInCreation =
            new NamedThreadLocal<>("Prototype beans currently in creation");

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException {

//        int[] nums = {2, 7, 11, 15};
//
//        int target = 9;
//        System.out.println(Arrays.toString(twoSum(nums, target)));


//        Map<String, Object> map = new HashMap<>();
//        map.put("a", 1);
//
//        map.computeIfAbsent("a", k -> {
//            return k;
//        });
//
//        System.out.println(map);
//
//        map.computeIfPresent("a", (k, v) -> {
//            return k + v;
//        });
//        System.out.println(map);


//        timeStat(() -> demo1("DemoBean4"));
//        timeStat(() -> demo2("DemoBean4"));
//        timeStat(() -> demo3("DemoBean4"));

        int maxNum = 10000;

        int x = 1024 * 1024;
        int y = 2;

        int[][] arrArr = new int[x][y];

        timeStat(() -> {
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    arrArr[i][j] = 1;
                }
            }
        }, 10);

        timeStat(() -> {
            for (int j = 0; j < y; j++) {
                for (int i = 0; i < x; i++) {
                    arrArr[i][j] = 1;
                }
            }
        }, 10);



    }



//    protected AbstractNestablePropertyAccessor getPropertyAccessorForPropertyPath(String propertyPath) {
//        int pos = PropertyAccessorUtils.getFirstNestedPropertySeparatorIndex(propertyPath);
//        // Handle nested properties recursively.
//        if (pos > -1) {
//            String nestedProperty = propertyPath.substring(0, pos);
//            String nestedPath = propertyPath.substring(pos + 1);
//            AbstractNestablePropertyAccessor nestedPa = getNestedPropertyAccessor(nestedProperty);
//            return nestedPa.getPropertyAccessorForPropertyPath(nestedPath);
//        }
//        else {
//            return this;
//        }
//    }

    private static int getNestedPropertySeparatorIndex(String propertyPath, boolean last) {
        boolean inKey = false;
        int length = propertyPath.length();
        int i = (last ? length - 1 : 0);
        while (last ? i >= 0 : i < length) {
            switch (propertyPath.charAt(i)) {
                case PropertyAccessor.PROPERTY_KEY_PREFIX_CHAR:
                case PropertyAccessor.PROPERTY_KEY_SUFFIX_CHAR:
                    inKey = !inKey;
                    break;
                case PropertyAccessor.NESTED_PROPERTY_SEPARATOR_CHAR:
                    if (!inKey) {
                        return i;
                    }
            }
            if (last) {
                i--;
            }
            else {
                i++;
            }
        }
        return -1;
    }

    public static String[] toStringArray2(@NonNull Collection<String> collection) {
        return (!CollectionUtils.isEmpty(collection) ? collection.toArray(new String[]{}) : new String[]{});
    }

    public static void timeStat(FunctionExecute functionExecute, int count) {
        final long stat = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            functionExecute.run();
        }
        System.out.println(System.currentTimeMillis() - stat);
    }


    public static void timeStat(FunctionExecute functionExecute) {
        final long stat = System.currentTimeMillis();
        for (int i = 0; i < num; i++) {
            addSet();
//            addString();
            functionExecute.run();
        }
        System.out.println(System.currentTimeMillis() - stat);
    }

    public static void addString() {
        prototypesCurrentlyInCreation.set("DemoBean1");
    }

    public static void addSet() {
        final Set<Object> set = new HashSet<>();
        set.add("DemoBean1");
        set.add("DemoBean2");
        set.add("DemoBean3");
//        set.add("DemoBean4");
//        set.add("DemoBean5");
//        set.add("DemoBean6");
        prototypesCurrentlyInCreation.set(set);
    }

    public static void demo1(String beanName) {
        Object curVal = prototypesCurrentlyInCreation.get();
        if (curVal instanceof String) {
			prototypesCurrentlyInCreation.remove();
		}
		else if (curVal instanceof Set) {
			Set<String> beanNameSet = (Set<String>) curVal;
			beanNameSet.remove(beanName);
			if (beanNameSet.isEmpty()) {
				prototypesCurrentlyInCreation.remove();
			}
		}
    }

    public static void demo2(String beanName) {
        Object curVal = prototypesCurrentlyInCreation.get();
        if (curVal instanceof Set && ((Set<String>) curVal).size() > 1) {
            ((Set<String>) curVal).remove(beanName);
        }
        else {
            prototypesCurrentlyInCreation.remove();
        }
    }

    public static void demo3(String beanName) {
        Object curVal = prototypesCurrentlyInCreation.get();
        if (curVal instanceof String || ((Set<String>) curVal).size() == 1) {
            prototypesCurrentlyInCreation.remove();
        }
        else {
            ((Set<String>) curVal).remove(beanName);
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            final int calc = target - num1;
            for (int j = i + 1; j < nums.length; j++) {
                if (i != j && calc == nums[j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[2];
    }

    @FunctionalInterface
    interface FunctionExecute {
        void run();
    }
}
