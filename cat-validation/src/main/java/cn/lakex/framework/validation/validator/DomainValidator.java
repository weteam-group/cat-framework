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

import cn.lakex.framework.validation.annotation.Domain;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Validates that the value passed is mobile number
 *
 * @author LarryKoo (larrykoo@126.com)
 * @date 2019/1/8 19:06
 * @slogon 站在巨人的肩膀上
 * @since 1.0.0
 */
public class DomainValidator implements ConstraintValidator<Domain, String> {

    private final static Pattern DOMAIN_PATTERN =
            Pattern.compile("^(?=^.{3,255}$)[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+$");

    @Override
    public void initialize(Domain constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() == 0) {
            return true;
        }

        return DOMAIN_PATTERN.matcher(value).matches();
    }

}
