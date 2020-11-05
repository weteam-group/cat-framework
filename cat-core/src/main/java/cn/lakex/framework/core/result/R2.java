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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import net.dreamlu.mica.core.result.IResultCode;
import net.dreamlu.mica.core.result.R;
import net.dreamlu.mica.core.result.SystemCode;
import net.dreamlu.mica.core.utils.StringPool;
import org.springframework.lang.Nullable;

/**
 * A enhance R for R(Mica)
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/11/4 14:47
 * @since 3.0.0
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Schema(name = "R2", description = "响应信息主体-V2")
public class R2<T> extends R {
    private static final long serialVersionUID = -1049801173077797823L;

    @Schema(description = "链路调用 TraceId")
    private String traceId = StringPool.EMPTY;

    @Schema(description = "返回结果签名")
    private String sign = StringPool.EMPTY;

    @Schema(description = "签名算法，可选:SALT_MD5/SALT_SHA1")
    private String alg = "SALT_SHA1";

    /**
     * 构造函数
     *
     * @param rCode IResultCode 对象
     * @param data  返回结果对象
     */
    private R2(IResultCode rCode, String msg, @Nullable T data) {
        this.setCode(rCode.getCode());
        this.setMsg(msg);
        this.setSuccess(SystemCode.SUCCESS == rCode);
        this.setData(data);
    }

    private R2(IResultCode rCode) {
        this(rCode, rCode.getMsg(), null);
    }

    /**
     * 返回数据不存在
     *
     * @param <T> 泛型
     * @return {Result}
     */
    public static <T> R2<T> dataNotExist() {
        return new R2<T>(SystemCode.DATA_NOT_EXIST);
    }

    /**
     * 返回数据已存在
     *
     * @param <T> 泛型
     * @return {Result}
     */
    public static <T> R2<T> dataExisted() {
        return new R2<T>(SystemCode.DATA_EXISTED);
    }

    /**
     * 返回拒绝
     *
     * @param <T> 泛型
     * @return {Result}
     */
    public static <T> R2<T> reject() {
        return new R2<T>(SystemCode.REQ_REJECT);
    }

    /**
     * 生成返回数据签名信息
     *
     * @param <T> 泛型
     * @return 携带包含签名信息的返回结果
     */
    public <T> R2<T> sign() {
        this.setAlg("");
        this.setSign("");
        this.setTraceId("");
        return (R2<T>) this;
    }
}
