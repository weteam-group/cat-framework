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

package cn.lakex.framework.core.utils;

import cn.lakex.framework.core.exception.ServiceExceptionPro;
import cn.lakex.framework.core.model.JwtObject;
import cn.lakex.framework.core.result.SystemCodePro;
import io.jsonwebtoken.*;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * 基于 JSON-WEB-TOKEN 的加解密工具
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2019/10/21 14:13
 * @since 3.0.0
 */
@Slf4j
@UtilityClass
public class JwtUtil {

    private static final String JWT_ID = "jwt";
    private static final String JWT_TOKEN_ISSUER = "cat";
    private static final Long TOKEN_CLOCK_SKEW_MILLIS = 0L;

    /**
     * 一个月的秒
     */
    public static final Long MONTH_SECOND = Math.max(0L, 3600 * 24 * 30);

    /**
     * 一年的秒
     */
    public static final Long YEAR_SECOND = Math.max(0L, 3600 * 24 * 30 * 12);

    /**
     * 创建一个 Token 字符串
     *
     * @param signAlg    签名算法
     * @param signKey    秘钥密码
     * @param subject    承载数据
     * @param expiration 过期时间，单位秒
     * @return {@link String} jws 字符串
     */
    public String create(SignatureAlgorithm signAlg, @NotNull Key signKey, @NotBlank String subject, Long expiration) {
        // 计算过期时间，并且转换为 java.util.Date 类型
        Instant instant = new Date().toInstant();
        Date validity = Date.from(instant.plus(Duration.ofSeconds(expiration)));

        return Jwts.builder()
                .setId(JWT_ID)
                .setIssuer(JWT_TOKEN_ISSUER)
                .setExpiration(validity)
                .signWith(signAlg, signKey)
                .setSubject(subject)
                .compact();
    }

    /**
     * 验证 Token 有效性并解析返回 Subject 字符串
     *
     * @param signKey         秘钥内容
     * @param jws             待验证的 Token 字符串
     * @param clockSkewMillis 便宜时间，单位秒
     * @return token subject 字符串
     * @throws ServiceExceptionPro {@link SystemCodePro} TOKEN_EXPIRED / TOKEN_INVALID
     */
    @NotNull
    public JwtObject parse(@NotNull Key signKey, @NotBlank String jws, Long clockSkewMillis) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(signKey)
                    .setAllowedClockSkewSeconds(clockSkewMillis)
                    .parseClaimsJws(jws)
                    .getBody();

            // we can trust this JWT
            return JwtObject.builder()
                    .id(claims.getId())
                    .issuer(claims.getIssuer())
                    .audience(claims.getAudience())
                    .subject(claims.getSubject())
                    .expiration(claims.getExpiration())
                    .notBefore(claims.getNotBefore())
                    .build();
        } catch (JwtException ex) {
            if (ex instanceof ClaimJwtException) {
                throw new ServiceExceptionPro(SystemCodePro.TOKEN_EXPIRED).message();
            }
            throw new ServiceExceptionPro(SystemCodePro.TOKEN_INVALID, ex).error();
        } catch (Exception e) {
            throw new ServiceExceptionPro(e).error();
        }
    }

    /**
     * 校验一个 JWS 字符串的合法性，忽略有效时间
     *
     * @param signKey 秘钥内容
     * @param jws     待验证的 Token 字符串
     * @return 合法返回 true，不合法返回 false，不会抛出异常
     */
    public boolean valid(@NotNull Key signKey, @NotBlank String jws) {
        try {
            Jwts.parser()
                    .setSigningKey(signKey)
                    .setAllowedClockSkewSeconds(MONTH_SECOND)
                    .parseClaimsJws(jws);

            return true;
        } catch (JwtException ex) {
            log.warn("JWS verify failure: {}", SystemCodePro.TOKEN_INVALID.toString());
            return false;
        } catch (Exception e) {
            throw new ServiceExceptionPro(e).error();
        }
    }

}
