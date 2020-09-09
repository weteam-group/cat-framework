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

package cn.lakex.framework.core.model;

import io.jsonwebtoken.Claims;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 一个自定义的 JWT --> {@link Claims} 对象
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019/10/25 15:06
 * @since 3.0.0
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class JwtObject implements Serializable {
    private static final long serialVersionUID = -2725900066931887525L;

    /**
     * JWT {@code JWT ID} claims parameter name
     */
    private String id;

    /**
     * JWT {@code Issuer} claims parameter name
     */
    private String issuer;

    /**
     * JWT {@code Subject} claims parameter name
     */
    private Date issuedAt;

    /**
     * JWT {@code Subject} claims parameter name
     */
    private String subject;

    /**
     * JWT {@code Audience} claims parameter name
     */
    private String audience;

    /**
     * JWT {@code Expiration} claims parameter name
     */
    private Date expiration;

    /**
     * JWT {@code Not Before} claims parameter name
     */
    private Date notBefore;
}
