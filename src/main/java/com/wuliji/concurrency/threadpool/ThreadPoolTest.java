package com.wuliji.concurrency.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	
	public static void main(String[] args) {
		scheduledThreadPool();
	}
	
	/**
	 * cachedThreadPool
	 */
	public static void cachedThreadPool() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 10; i++) {
			final int index = i;
			cachedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
		cachedThreadPool.shutdown();
	}
	
	/**
	 * fixedThreadPool
	 */
	public static void fixedThreadPool() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		
		for(int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
		fixedThreadPool.shutdown();
	}
	
	/**
	 * singleThreadPool
	 */
	public static void singleThreadPool() {
		ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
		for(int i = 0; i < 10; i++) {
			final int index = i;
			singleThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
		singleThreadPool.shutdown();
	}
	
	/**
	 * scheduledThreadPool
	 */
	public static void scheduledThreadPool() {
		ScheduledExecutorService sechedThreadPool = Executors.newScheduledThreadPool(5);
/*		sechedThreadPool.schedule(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("run");
			}
		}, 3, TimeUnit.SECONDS);*/
		
		sechedThreadPool.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("run");
			}
		}, 1, 3, TimeUnit.SECONDS);//延迟三秒输出后再延迟一秒执行下一个任务
		//sechedThreadPool.shutdown();
	}
}
