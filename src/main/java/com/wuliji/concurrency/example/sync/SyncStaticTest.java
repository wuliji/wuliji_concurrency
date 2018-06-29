package com.wuliji.concurrency.example.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.wuliji.concurrency.annotation.ThreadSafe;

@ThreadSafe
public class SyncStaticTest {
	
	public void test1() {
		synchronized(SyncStaticTest.class) {//对该类所有对象进行加锁互斥处理
			for(int i = 0; i < 100; i++) {
				System.out.println("test1:" + i);
			}
		}
	}
	
	public static synchronized void test2() {
		for(int i = 0; i < 100; i++) {
			System.out.println("test2:" + i);
		}
	}
	
	public static void main(String[] args) {
		SyncStaticTest test = new SyncStaticTest();
		SyncStaticTest test2 = new SyncStaticTest();
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
