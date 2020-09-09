/*
 * Copyright (c) 2020, WeTeam Inc.
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

package cn.lakex.framework.core.utils;

import lombok.experimental.UtilityClass;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * StringUtil Pro
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/9/9 14:04
 * @since 3.0.0
 */
@UtilityClass
public class StringUtilPro {
    public static final String COMMA_SEPARATOR = ",";

    private static final Locale LOCALE_ENGLISH = Locale.ENGLISH;

    /**
     * 特殊字符正则，sql特殊字符和空白符
     */
    private final static Pattern SPECIAL_CHARS_REGEX = Pattern.compile("[`'\"|/,;()-+*%#·•? \\s]");

    /**
     * 随机字符串因子
     */
    private static final String INT_STR = "0123456789";
    private static final String STR_STR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String ALL_STR = INT_STR + STR_STR;

    /**
     * @return true if the given value is either null or the empty string
     */
    public boolean isNullOrEmpty(String value) {
        return value == null || value.isEmpty();
    }

    /**
     * Converts a given String to lower case with Locale.ENGLISH
     *
     * @param str the string to be converted to lower case
     * @return the lower case of string, or itself if string is null/empty
     */
    public String lowerCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.toLowerCase(LOCALE_ENGLISH);
    }

    /**
     * Converts a given String to upper case with Locale.ENGLISH
     *
     * @param str the string to be converted to upper case
     * @return the upper case of string, or itself if string is null/empty
     */
    public String upperCase(String str) {
        if (isNullOrEmpty(str)) {
            return str;
        }
        return str.toUpperCase(LOCALE_ENGLISH);
    }
}
