package com.wuliji.concurrency.example.publish;

import com.wuliji.concurrency.annotation.NotRecommend;
import com.wuliji.concurrency.annotation.NotThreadSafe;

@NotThreadSafe
@NotRecommend
public class Escape {

	
	public Escape() {
		new InnerClass();
	}
	private int thisCanbeEscape = 0;
	
	private class InnerClass{
		
		public InnerClass() {
			System.out.println(Escape.this.thisCanbeEscape);
		}
	}
	
	public static void main(String[] args) {
		new Escape();
	}
}
