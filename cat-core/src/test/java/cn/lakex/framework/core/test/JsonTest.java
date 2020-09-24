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

package cn.lakex.framework.core.test;

import cn.lakex.framework.core.utils.JsonUtilPro;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * JSON String testcase
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/9/24 09:53
 * @since 3.0.0
 */
public class JsonTest {

    @Test
    void testToString() {
        String json = JsonUtilPro.toJsonPretty(Arrays.asList("1", "2", "4", "8", "3"));
        System.out.println("JSON-String: \n" + json);
    }

}
