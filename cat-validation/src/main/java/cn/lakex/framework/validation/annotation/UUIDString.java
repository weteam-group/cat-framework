/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.annotation;

import cn.lakex.framework.validation.validator.UUIDStringValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * UUID 字符串合法性校验
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 10:38
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = UUIDStringValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UUIDString {

    String message() default "cat.constraints.UUIDString.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Domain[] value();
    }
}
