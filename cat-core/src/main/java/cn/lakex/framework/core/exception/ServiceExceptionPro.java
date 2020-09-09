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

package cn.lakex.framework.core.exception;

import net.dreamlu.mica.core.exception.ServiceException;
import net.dreamlu.mica.core.result.IResultCode;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import org.springframework.lang.Nullable;

import java.util.Optional;

/**
 * 通用的系统异常接口
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019/10/17 15:28
 * @since 3.0.0
 */
public class ServiceExceptionPro extends ServiceException {
    /**
     * 异常等级
     */
    @Nullable
    private ExceptionLevel level;

    public ServiceExceptionPro(R<?> result) {
        super(result);
    }

    public ServiceExceptionPro(Throwable cause) {
        super(cause);
    }

    public ServiceExceptionPro(String message) {
        super(message);
    }

    public ServiceExceptionPro(IResultCode rCode) {
        super(rCode);
    }

    public ServiceExceptionPro(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceExceptionPro(IResultCode rCode, String message) {
        super(rCode, message);
    }

    public ServiceExceptionPro(IResultCode rCode, Throwable cause) {
        this(rCode, rCode.getMsg(), cause);
    }

    public ServiceExceptionPro(IResultCode rCode, String message, Throwable cause) {
        super(message, cause);
        doFillInStackTrace();
    }

    public ServiceExceptionPro(String format, Object... objects) {
        super(String.format(format, objects));
    }

    public ServiceExceptionPro(Throwable cause, String format, Object... objects) {
        super(String.format(format, objects), cause);
    }



    // ---------------------------------------------------------
    // ---------- 增加对异常的等级进行分级管理，以便细分异常 -----------
    // ---------------------------------------------------------

    public ExceptionLevel getLevel() {
        // 如果 level 为空，默认返回 unknown
        return Optional.ofNullable(level).orElse(ExceptionLevel.UNKNOWN);
    }

    public ServiceExceptionPro message() {
        this.level = ExceptionLevel.MESSAGE;
        return this;
    }

    public ServiceExceptionPro debug() {
        this.level = ExceptionLevel.DEBUG;
        return this;
    }

    public ServiceExceptionPro info() {
        this.level = ExceptionLevel.INFO;
        return this;
    }

    public ServiceExceptionPro warning() {
        this.level = ExceptionLevel.WARNING;
        return this;
    }

    public ServiceExceptionPro error() {
        this.level = ExceptionLevel.ERROR;
        return this;
    }

    public ServiceExceptionPro fatal() {
        this.level = ExceptionLevel.FATAL;
        return this;
    }
}
