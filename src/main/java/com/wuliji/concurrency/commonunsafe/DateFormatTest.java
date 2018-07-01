package com.wuliji.concurrency.commonunsafe;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class DateFormatTest {
	
	//请求总数
	public static int clientTotal = 5000;
	
	//同时并发执行的线程数
	public static int threadTotal = 200;
	
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");//线程不安全
	
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
		
	}
	private static void update() {
		try {
			simpleDateFormat.parse("2018-02-08");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
