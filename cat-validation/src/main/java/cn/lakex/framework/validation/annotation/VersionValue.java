/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.annotation;

import cn.lakex.framework.validation.validator.VersionValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 对语义化版本号进行校验 X.Y.Z
 * 遵循 语义化版本2.0.0（https://semver.org/lang/zh-CN/）
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 16:13
 * @since 1.0.0
 */
@Documented
@Retention(RUNTIME)
@Constraint(validatedBy = {VersionValueValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
public @interface VersionValue {
    String message() default "cat.constraints.VersionValue.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
