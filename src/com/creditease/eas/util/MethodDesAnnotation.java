package com.creditease.eas.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/***
 *系统留痕需要的注解
 * @Title:MethodDesAnnotation.java
 * @Package com.creditease.eas.util
 * created at 2014-6-3 下午02:15:18 by ryl
 * @author ygq
 * @version 1.0
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodDesAnnotation {
	public String description() default "description";
}
