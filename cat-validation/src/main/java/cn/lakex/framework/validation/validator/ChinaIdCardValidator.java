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

package cn.lakex.framework.validation.validator;

import cn.lakex.framework.validation.annotation.ChinaIdCard;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * China ID-CARD Validator
 * 身份证号码校验
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/8/14 16:19
 * @see <a href="https://zh.wikipedia.org/wiki/中华人民共和国公民身份号码">中华人民共和国公民身份号码</a>
 * @since 1.0.0
 */
@Slf4j
public class ChinaIdCardValidator implements ConstraintValidator<ChinaIdCard, String> {

    /**
     * Example: 503821 1989 0315 002 2
     * ^开头
     * [1-9] 第一位1-9中的一个      5
     * \\d{5} 五位数字           03821（前六位省市县地区编码）
     * (18|19|20)                19（根据实际情况取值范围为：18xx-20xx年）
     * \\d{2}                    89（年份）
     * ((0[1-9])|(10|11|12))     03（月份）
     * (([0-2][1-9])|10|20|30|31)15（日期）
     * \\d{3} 三位数字            002（第十七位奇数代表男，偶数代表女）
     * [0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）2
     * $结尾
     */
    private static final Pattern ID_CARD_PATTERN =
            Pattern.compile("^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$");

    /**
     * 身份证加权因子
     */
    private static final int[] ID_CARD_VI = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 身份证最后一位校验码范围
     */
    private static final String[] ID_CARD_Y = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "X"};
    private static final Integer ID_CARD_LENGTH = 18;

    @Override
    public void initialize(ChinaIdCard constraints) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        // match China-Id-Card pattern
        boolean matches = ID_CARD_PATTERN.matcher(value).matches();
        if (matches && value.length() == ID_CARD_LENGTH) {
            try {
                char[] chartArray = value.toCharArray();
                int sum = 0;
                for (int i = 0; i < ID_CARD_VI.length; i++) {
                    int current = Integer.parseInt(String.valueOf(chartArray[i]));
                    int count = current * ID_CARD_VI[i];
                    sum += count;
                }
                // Get the last letter of ID-CARD
                char idCardLastLetter = chartArray[ID_CARD_LENGTH - 1];
                // Get the mod 11
                int idCardMod = sum % 11;
                return ID_CARD_Y[idCardMod].toUpperCase().equals(String.valueOf(idCardLastLetter).toUpperCase());
            } catch (Exception e) {
                log.error("Valid error for [{}]", value, e);
                return false;
            }
        }
        return matches;
    }


}
