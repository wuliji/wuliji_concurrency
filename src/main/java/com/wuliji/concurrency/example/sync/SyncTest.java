package com.wuliji.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wuliji.concurrency.annotation.ThreadSafe;

@ThreadSafe
public class SyncTest {
	
	public void test1() {
		synchronized(this) {//对该对象进行加锁互斥处理
			for(int i = 0; i < 100; i++) {
				System.out.println("test1:" + i);
			}
		}
	}
	
	public synchronized void test2() {
		for(int i = 0; i < 100; i++) {
			System.out.println("test2:" + i);
		}
	}
	
	public static void main(String[] args) {
		SyncTest test = new SyncTest();
		SyncTest test2 = new SyncTest();
		ExecutorService threadPool = Executors.newCachedThreadPool();
		threadPool.execute(() -> {
			test.test1();
		});
		threadPool.execute(() -> {
			test2.test2();
		});
		threadPool.execute(() -> {
			test.test1();
		});
		threadPool.shutdown();
		
	}
}
