package com.wuliji.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicReference;

import com.wuliji.concurrency.annotation.ThreadSafe;

@ThreadSafe
/**
 * 原子修改某一类型字段的值
 * @author Administrator
 *
 */
public class AtomicReferenceTest {
	
	private static AtomicReference<Integer> count = new AtomicReference<>(0);
	
	public static void main(String[] args) {
		count.compareAndSet(0, 2);//如果是0更新成2
		count.compareAndSet(0, 1);
		count.compareAndSet(1, 3);
		count.compareAndSet(2, 4);
		count.compareAndSet(3, 5);
		System.out.println("count:" + count);
	}
}
