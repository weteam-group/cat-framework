/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.annotation;

import cn.lakex.framework.validation.validator.EnumValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 枚举值校验
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 09:43
 * @since 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {EnumValueValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface EnumValue {
    String message() default "cat.constraints.EnumValue.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    String enumMethod() default "validation";
}
