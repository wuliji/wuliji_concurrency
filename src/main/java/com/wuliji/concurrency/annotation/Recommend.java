package com.wuliji.concurrency.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记【推荐】的类或写法注解
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.SOURCE)
@Target(ElementType.TYPE)//对类进行标注
public @interface Recommend {
	
	String value() default "";
}
