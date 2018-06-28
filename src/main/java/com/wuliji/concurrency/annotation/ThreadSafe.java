package com.wuliji.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记【线程安全】的注解
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)//对类进行标注
public @interface ThreadSafe {
	
	String value() default "";
}
