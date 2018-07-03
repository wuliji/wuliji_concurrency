package com.wuliji.concurrency.AQS;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	
	private final static int threadCount = 200;
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final Semaphore semaphore = new Semaphore(3);//给定并发数量
		
		for(int i = 0; i < threadCount; i++) {
			final int threadNum = i;
			exec.execute(() ->{
				try {
					semaphore.acquire(1);//可以指定一次获得多少个许可，该方法会阻塞线程
					test(threadNum);
					semaphore.release(1);//可以指定一次释放多少个许可
				}catch (Exception e) {
					e.printStackTrace();
				} 
			});
		}
		
		exec.shutdown();
		System.out.println("finish");
	}
	
	private static void test(int threadNum) throws InterruptedException {
		System.out.println(threadNum);
		Thread.sleep(1000);
	}
}
