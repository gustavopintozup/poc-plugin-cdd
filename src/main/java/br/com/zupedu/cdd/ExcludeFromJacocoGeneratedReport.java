package br.com.zupedu.cdd;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that helps Jacoco to not parse generated methods
 * 
 * @author gustavopinto
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExcludeFromJacocoGeneratedReport {}

