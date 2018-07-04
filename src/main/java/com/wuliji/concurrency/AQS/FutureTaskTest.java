package com.wuliji.concurrency.AQS;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<String> result = new FutureTask<String>(new Callable<String>() {

			@Override
			public String call() throws Exception {
				System.out.println("thread start");
				Thread.sleep(5000);
				return "Done";
			}
		});
		
		new Thread(result).start();
		Thread.sleep(1000);
		System.out.println(result.get());
	}
	
}
