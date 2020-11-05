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

package cn.lakex.framework.logging.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import net.dreamlu.mica.core.utils.DateUtil;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * LogAbstract
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/10/22 17:25
 * @since 3.0.0
 */
@Data
public class LogAbstract implements Serializable {
    private static final long serialVersionUID = 2856065675470305672L;

    /**
     * 主键 ID
     */
    protected Long id;
    /**
     * 服务 Id
     */
    protected String serviceId;
    /**
     * 服务器 IP
     */
    protected String serverIp;
    /**
     * 服务器名
     */
    protected String serverHost;
    /**
     * 环境
     */
    protected String env;
    /**
     * 操作 IP 地址
     */
    protected String remoteIp;
    /**
     * 用户代理
     */
    protected String userAgent;
    /**
     * 请求URI
     */
    protected String requestUri;
    /**
     * 操作方式
     */
    protected String method;
    /**
     * 方法类
     */
    protected String methodClass;
    /**
     * 方法名
     */
    protected String methodName;
    /**
     * 操作提交的数据
     */
    protected String params;
    /**
     * 执行时间
     */
    protected String duration;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = DateUtil.PATTERN_DATETIME)
    @JsonFormat(pattern = DateUtil.PATTERN_DATETIME)
    protected Date createdTime;
}
