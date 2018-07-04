package com.wuliji.concurrency.AQS;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {
	
	static class MyCallable implements Callable<String>{

		@Override
		public String call() throws Exception {
			System.out.println("thread start");
			Thread.sleep(5000);
			return "Done";
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		Future<String> result = threadPool.submit(new MyCallable());
		System.out.println("main start");
		Thread.sleep(1000);
		System.out.println(result.get());
		threadPool.shutdown();
	}
}
