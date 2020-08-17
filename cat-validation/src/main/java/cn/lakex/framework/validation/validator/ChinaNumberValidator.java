/*
 * Copyright (c) 2019, WeTeam Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.lakex.framework.validation.validator;

import cn.lakex.framework.validation.annotation.ChinaNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validates that the value passed for China mobile number
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2020/08/14 19:06
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public class ChinaNumberValidator implements ConstraintValidator<ChinaNumber, String> {

    private final static Pattern DIGITS_PATTERN = Pattern.compile("[0-9]*");

    private static final String START_VALUE = "1";

    private static final int PHONE_NUMBER_LENGTH = 11;

    @Override
    public void initialize(ChinaNumber annotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        // 验证手机号长度，固定11位
        if (value.length() != PHONE_NUMBER_LENGTH) {
            context.buildConstraintViolationWithTemplate("{cat.constraints.ChinaNumber.message.1}")
                    .addConstraintViolation();
            return false;
        }

        // 验证手机号第一位数
        if (!value.startsWith(START_VALUE)) {
            context.buildConstraintViolationWithTemplate("{cat.constraints.ChinaNumber.message.2}")
                    .addConstraintViolation();
            return false;
        }

        // 验证手机号内容只能是数字
        if (!DIGITS_PATTERN.matcher(value).matches()) {
            context.buildConstraintViolationWithTemplate("{cat.constraints.ChinaNumber.message.3}")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

}
