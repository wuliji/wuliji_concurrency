package com.wuliji.concurrency.commonunsafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class StringBuTest {
	
	//请求总数
	public static int clientTotal = 5000;
	
	//同时并发执行的线程数
	public static int threadTotal = 200;
	
	public static StringBuilder stringBuilder = new StringBuilder();
	
	//public static StringBuffer stringBuffer = new StringBuffer();线程安全
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for(int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					update();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("String长度为:" + stringBuilder.length());
		
	}
	private static void update() {
		stringBuilder.append("1");
	}
}
