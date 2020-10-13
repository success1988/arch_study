package com.success.utils;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Title：
 * @Author：wangchenggong
 * @Date 2020/8/13 10:28
 * @Description
 * @Version
 */
public final class ThreadPoolUtil {
    /**
     * CPU核数
     */
    private static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final AtomicLong ATOMIC_LONG = new AtomicLong();
    /**
     * Spring线程池
     */
    private static final ThreadPoolExecutor THREAD_POOL = new ThreadPoolExecutor(NCPU << 2, NCPU << 3, 15L, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "threadPoolUtil-" + ATOMIC_LONG.incrementAndGet());
        }
    });

    /**
     * 构造函数
     */
    private ThreadPoolUtil() {
    }

    /**
     * 提交线程任务
     */
    public static void execute(Runnable task){
        THREAD_POOL.execute(task);
    }

    /**
     * 提交线程任务
     */
    public static Future submit(Runnable task){
        return THREAD_POOL.submit(task);
    }

    /**
     * 提交线程任务
     * @param task
     * @param <T>
     * @return
     */
    public static <T> Future<T> submit(Callable<T> task){
        return THREAD_POOL.submit(task);
    }

    public static void main(String[] args) throws InterruptedException {

        long start = System.currentTimeMillis();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            ThreadPoolUtil.submit(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName() + "running!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("耗时:" + (System.currentTimeMillis() - start) + "毫秒");
    }


}
