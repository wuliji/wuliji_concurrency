package com.wuliji.concurrency.AQS;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	
	private static CyclicBarrier barrier = new CyclicBarrier(5);
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 10; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			threadPool.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					
				}
			});
		}
	}
	
	private static void race(int threadNum) throws Exception{
		Thread.sleep(1000);
		System.out.println(threadNum + ":ready");
		barrier.await();//可以指定等待的时间，如果到了时间还处于等待状态就直接接下来执行
		System.out.println(threadNum + ":contiune");
	}
}
