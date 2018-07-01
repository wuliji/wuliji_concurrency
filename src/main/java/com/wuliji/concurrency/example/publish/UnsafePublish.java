package com.wuliji.concurrency.example.publish;

import java.util.Arrays;

import com.wuliji.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
/**
 * 发布不安全的对象
 * @author Administrator
 *
 */
public class UnsafePublish {

	private String[] states = {"a", "b", "c"};
	
	public String[] getStates() {
		return states;
	}
	
	public static void main(String[] args) {
		UnsafePublish unsafe = new UnsafePublish();
		System.out.println(Arrays.toString(unsafe.getStates()));
		unsafe.getStates()[0] = "d";
		System.out.println(Arrays.toString(unsafe.getStates()));
	}
}
