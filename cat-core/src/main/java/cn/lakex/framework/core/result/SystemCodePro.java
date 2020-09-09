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

package cn.lakex.framework.core.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.dreamlu.mica.core.result.IResultCode;
import net.dreamlu.mica.core.utils.JsonUtil;

import java.util.Objects;

/**
 * 系统扩展返回结果 Code
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/9/9 11:43
 * @since 3.0.0
 */
@Getter
@AllArgsConstructor
@Schema(name = "系统扩展返回结果Code")
public enum SystemCodePro implements IResultCode {

    /**
     * Token 过期
     */
    TOKEN_EXPIRED(SystemCodePro.TOKEN_EXPIRED_CODE, "Token已过期"),

    /**
     * 无效的 Token
     */
    TOKEN_INVALID(SystemCodePro.TOKEN_INVALID_CODE, "无效Token");


    /**
     * 权限校验 code
     */
    public static final int TOKEN_EXPIRED_CODE = 100200;
    public static final int TOKEN_INVALID_CODE = 100201;


    /**
     * code编码
     */
    final int code;
    /**
     * 中文信息描述
     */
    final String msg;

    @Override
    public String toString() {
        return Objects.requireNonNull(JsonUtil.toJson(this));
    }
}
