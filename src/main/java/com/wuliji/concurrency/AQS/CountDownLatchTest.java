package com.wuliji.concurrency.AQS;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
	
	private final static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final CountDownLatch countDown = new CountDownLatch(threadCount);
		
		for(int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() ->{
				try {
					test(threadNum);
				}catch (Exception e) {
					e.printStackTrace();
				} finally {
					countDown.countDown();
				}
			});
		}
		countDown.await();//await方法还可以指定相关的等待时间和相关的时间单位
		exec.shutdown();
		System.out.println("finish");
	}
	
	private static void test(int threadNum) throws InterruptedException {
		Thread.sleep(100);
		System.out.println(threadNum);
		Thread.sleep(100);
	}
}
