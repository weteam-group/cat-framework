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

/*
 * Copyright (c) 2018. Born To Learn Education Inc. Ltd All Rights Reserved.
 */

package cn.lakex.framework.validation.annotation;

import cn.lakex.framework.validation.validator.DomainValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 验证域名的合法性
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/08/14 20:31
 * @since 1.0.0
 */
@Documented
@Constraint(validatedBy = {DomainValidator.class})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
public @interface Domain {
    String message() default "cat.constraints.Domain.message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * @return an additional regular expression the annotated element must match. The default
     * is any string ('.*')
     */
    String regexp() default ".*";

    /**
     * @return used in combination with {@link #regexp()} in order to specify a regular
     * expression option
     */
    Pattern.Flag[] flags() default {};

    /**
     * Defines several {@link Domain} constraints on the same element.
     *
     * @see Domain
     */
    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        Domain[] value();
    }
}
