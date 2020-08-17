/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.validator;

import cn.lakex.framework.validation.annotation.VersionValue;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 版本号校验处理器
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 16:15
 * @since 1.0.0
 */
public class VersionValueValidator implements ConstraintValidator<VersionValue, String> {

    private static final Pattern PATTERN_VERSION = Pattern.compile("(([0-9]|([1-9]([0-9]*))).){2}([0-9]|([1-9]([0-9]*)))");

    @Override
    public void initialize(VersionValue constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return Boolean.TRUE;
        }
        return PATTERN_VERSION.matcher(value).matches();
    }
}
