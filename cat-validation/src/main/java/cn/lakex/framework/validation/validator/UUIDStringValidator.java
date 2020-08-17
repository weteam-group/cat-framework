/*
 * Copyright (c) 2011 Born-To-Learn Ltd. All rights reserved.
 */

package cn.lakex.framework.validation.validator;

import cn.lakex.framework.validation.annotation.UUIDString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * UUID 字符串校验处理器
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 10:39
 * @since 3.0.0
 */
public class UUIDStringValidator implements ConstraintValidator<UUIDString, String> {

    public static final Pattern UUID_PATTERN = Pattern.compile("[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}");

    @Override
    public void initialize(UUIDString uuidString) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return UUID_PATTERN.matcher(value).matches();
    }
}
