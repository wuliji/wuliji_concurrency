package com.wuliji.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

import com.wuliji.concurrency.annotation.ThreadSafe;

@ThreadSafe
/**
 * AtomicIntegerFiledUpdater类 指定修改某一类的属性值，该属性值必须为volatile且不能为静态
 * @author Administrator
 *
 */
public class AtomicIntegerFieldUpdaterTest {
	private volatile int count = 100;
	
	public int getCount() {
		return count;
	}

	private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater = 
			AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");
	
	public static void main(String[] args) {
		AtomicIntegerFieldUpdaterTest test = new AtomicIntegerFieldUpdaterTest();
		if(updater.compareAndSet(test, 100, 120)) {
			System.out.println("update:" + test.getCount());
		}
		if(updater.compareAndSet(test, 100, 120)) {
			System.out.println("update:" + test.getCount());
		}else {
			System.out.println("第二次未执行");
		}
		
	}
}
