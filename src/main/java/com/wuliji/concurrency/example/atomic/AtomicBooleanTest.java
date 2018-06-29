package com.wuliji.concurrency.example.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

import com.wuliji.concurrency.annotation.ThreadSafe;

@ThreadSafe
/**
 * 将布尔值改成CAS操作
 * @author Administrator
 *
 */
public class AtomicBooleanTest {
	
	private static AtomicBoolean isHappend = new AtomicBoolean(false);
	
	public static int clientTotal = 5000;
	
	public static int threadTotal = 200;
	
	public static void main(String[] args) throws Exception{
		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for(int i = 0; i < clientTotal; i++) {
			executorService.execute(() -> {
				try {
					semaphore.acquire();
					test();
					semaphore.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("isHappend:"+isHappend.get());
	}
	
	private static void test() {
		if(isHappend.compareAndSet(false, true)) {
			System.out.println("excute");
		}
	}
}
