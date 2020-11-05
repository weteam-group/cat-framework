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

package cn.lakex.framework.logging.aspect;

import cn.lakex.framework.logging.annotation.ApiLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * ApiLogAspect for {@link ApiLog}
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/10/22 15:46
 * @since 3.0.0
 */
@Slf4j
@Aspect
public class ApiLogAspect {

    /**
     * AOP Around for Annotation 'ApiLog'
     *
     * @param point  ProceedJoinPoint for method
     * @param apiLog Annotation tag object
     * @return Proceed result of method
     */
    @Around("@annotation(apiLog)")
    public Object around(ProceedingJoinPoint point, ApiLog apiLog) throws Throwable {
        // Get the className and methodName.
        String className = point.getTarget().getClass().getName();
        String methodName = point.getSignature().getName();

        // Trace the proceed duration.
        long begin = System.currentTimeMillis();
        Object result = point.proceed();
        long duration = System.currentTimeMillis() - begin;

        // Send logging

        return result;
    }
}
