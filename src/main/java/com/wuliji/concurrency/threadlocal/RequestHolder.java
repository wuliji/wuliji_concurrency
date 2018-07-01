package com.wuliji.concurrency.threadlocal;

public class RequestHolder {
	
	private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();
	
	public static void add(Long id) {
		requestHolder.set(id);//传入到ThreadLocal中的键是当前线程对象，值是id值
	}
	
	public static Long getId() {
		return requestHolder.get();//相当与传入了当前线程对象到threadlocal中去，然后在根据键值对进行取值
	}
	
	public static void remove() {
		requestHolder.remove();
	}
	
}
