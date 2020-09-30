package com.hzm.freestyle.concurrent;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Hezeming
 * @version 1.0
 * @date 2020年09月16日
 */
public class ForkJoinTest {

    private static final Integer THRESHOLD = 10000;

//    public static Integer taskCount = 0;

    static class MyForkJoinTask extends RecursiveTask<Integer> {

        private AtomicInteger taskCount = new AtomicInteger();

        private Integer startValue;

        private Integer endValue;

        private boolean isInvokeAll;

        public MyForkJoinTask(Integer startValue, Integer endValue, boolean isInvokeAll, AtomicInteger taskCount) {
            taskCount.incrementAndGet();
            this.startValue = startValue;
            this.endValue = endValue;
            this.isInvokeAll = isInvokeAll;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (endValue - startValue <= THRESHOLD) {
                for (int i = startValue; i < endValue; i++) {
                    sum += i;
                }
                return sum;
            }

            int middle = (startValue + endValue) / 2;

            // 子任务递归
            MyForkJoinTask task1 = new MyForkJoinTask(startValue, middle, isInvokeAll, taskCount);
            MyForkJoinTask task2 = new MyForkJoinTask(middle + 1, endValue, isInvokeAll, taskCount);

            // fork子任务
            if (isInvokeAll) {
                invokeAll(task1, task2);
            } else {
                task1.fork();
                task2.fork();
            }

            // join子任务
            final Integer joinResult1 = task1.join();
            final Integer joinResult2 = task2.join();

            sum = joinResult1 + joinResult2;

            return sum;
        }
    }

    /**
     * 计算0到100 0000
     *
     * @param args
     * @return void
     * @author Hezeming
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        int MAX = 100 * 10000;

        int start = 1;
        int end = MAX;

        final LocalDateTime now = LocalDateTime.now();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        final MyForkJoinTask myForkJoinTask1 = new MyForkJoinTask(start, end, false, new AtomicInteger());
        final ForkJoinTask<Integer> submit = forkJoinPool.submit(myForkJoinTask1);
        final Integer result = submit.join();
        System.out.println("计算结果为 sum = " + result + ", 计算时长为 time = " + Duration.between(now, LocalDateTime.now()).toMillis() + "ms" + ", 开启任务数为 taskCount = " + myForkJoinTask1.taskCount.get());


//        ForkJoinPool fjp2 = new ForkJoinPool();
//        SumTask2 sumTask2 = new SumTask2(start, end);
//        long begin3 = System.currentTimeMillis();
//        Integer invoke = fjp2.invoke(sumTask2);
//        long end3 = System.currentTimeMillis();
//        System.out.println("计算结果3为 sum = " + invoke + ",计算时长为" + begin3 + "-" + end3 + "---  " + (end3 - begin3) + "ms");
//
//        ForkJoinPool fjp = new ForkJoinPool();
//        long begin2 = System.currentTimeMillis();
//        SumTask sumTask = new SumTask(start, end);
//        ForkJoinTask<Integer> submit = fjp.submit(sumTask);
//        Integer join = submit.join();
//        long end2 = System.currentTimeMillis();
//        System.out.println("计算结果2为 sum = " + join + ",计算时长为" + begin2 + "-" + end2 + "---   " + (end2 - begin2) + "ms");

    }

    static class SumTask extends RecursiveTask <Integer>{
        private static final Integer THRESHOLD = 1000;
        private int start;
        private int end;
        public SumTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            Integer sum = 0;
            boolean isOk = (end - start) <= THRESHOLD;
            if(isOk) {
                for(int i = start; i <= end; i ++) {
                    sum += i;
                }
                return sum;
            }

            int middle = (end + start) / 2;
            //子任务递归
            SumTask sumSubTask = new SumTask(start, middle);
            SumTask sumSubTask1 = new SumTask(middle + 1, end);

            //fork子任务
            sumSubTask.fork();
            sumSubTask1.fork();

            //join子任务
            Integer join = sumSubTask.join();
            Integer join1 = sumSubTask1.join();

            sum = join + join1;
            //计算结果
            return sum;
        }
    }

    static class SumTask2 extends RecursiveTask <Integer>{
        private static final Integer THRESHOLD = 1000;
        private int start;
        private int end;
        public SumTask2(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            Integer sum = 0;
            boolean isOk = end - start <= THRESHOLD;
            if(isOk) {
                for(int i = start; i <= end; i ++) {
                    sum += i;
                }
//            System.out.println(String.format("compute %d-%d = %d", start, end, sum));
                return sum;
            }

            //除以2
            int middle = (end + start) / 2;
            //子任务递归
//        System.out.println(String.format("fork %d-%d => %d-%d&%d-%d", start, end, start, middle - 1, middle, end));
            SumTask2 sumSubTask = new SumTask2(start, middle - 1);
            SumTask2 sumSubTask1 = new SumTask2(middle, end);

            //fork子任务
            invokeAll(sumSubTask, sumSubTask1);

            //join子任务
            Integer join = sumSubTask.join();
            Integer join1 = sumSubTask1.join();

            sum = join + join1;
            //计算结果
            return sum;
        }
    }
}
