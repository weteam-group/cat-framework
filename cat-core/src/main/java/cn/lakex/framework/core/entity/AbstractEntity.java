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

package cn.lakex.framework.core.entity;

import cn.lakex.framework.core.utils.JsonUtilPro;
import net.dreamlu.mica.core.utils.BeanUtil;
import net.dreamlu.mica.core.utils.JsonUtil;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Basic domain entity
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/11/11 14:09
 * @since 3.0.0
 */
public abstract class AbstractEntity implements Serializable {
    private static final long serialVersionUID = 4555913943001696766L;

    /**
     * A initialized method of abstract
     *
     * @return {@link AbstractEntity}
     */
    public abstract AbstractEntity init();

    /**
     * Convert a object to a json string.
     *
     * @return {@link String}
     */
    public String toJson() {
        return Objects.requireNonNull(JsonUtil.toJson(this));
    }

    /**
     * Convert a object to a json string with
     *
     * @return {@link String}
     */
    public String toJsonPretty() {
        return Objects.requireNonNull(JsonUtilPro.toJsonPretty(this));
    }

    /**
     * Convert a object to a map
     *
     * @return {@link HashMap}
     */
    public Map<String, Object> toMap() {
        return BeanUtil.toMap(this);
    }

}
