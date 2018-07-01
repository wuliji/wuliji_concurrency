package com.wuliji.concurrency.immutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.wuliji.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
public class ImmutableTest {
	
	private final static Integer a = 1;
	private final static String b = "2";
	private final static Map<Integer, Integer> finalMap = new HashMap<>();
	private static Map<Integer, Integer> map = new HashMap<>();
	static {
		finalMap.put(1, 2);
		finalMap.put(3, 2);
		finalMap.put(5, 6);
		map.put(1, 1);
		map = Collections.unmodifiableMap(map);//将map设置为不可改变的map
	}
	
	public static void main(String[] args) {
//		a = 2;
//		b = "a";
//		map = new HashMap<>();
		finalMap.put(1, 3);
//		map.put(1, 1);//会抛出异常
		
//		list.add(1); 会抛出异常
		
		
	}
	
	/**
	 * Immutable类的使用，对这些类的基础数据修改都会抛异常
	 */
	private final static ImmutableList list = ImmutableList.of(1,2,3);
	
	private final static ImmutableSet set = ImmutableSet.copyOf(list);
	
	private final static ImmutableMap<Integer, Integer> immuMap = ImmutableMap.of(1, 2, 3, 4);
	
	private final static ImmutableMap<Integer, Integer> immuMap2 = 
			ImmutableMap.<Integer, Integer>builder().put(1, 2).put(3, 4).build();
	
	
	
}
