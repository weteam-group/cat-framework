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

package cn.lakex.framework.core.mapping;

import java.util.List;

/**
 * A Common Entity Mapper Interface with 'MapStruct'
 *
 * @author LarryKoo (larrykoo@126.com)
 * @slogon 站在巨人的肩膀上
 * @date 2020/9/11 16:20
 * @since 3.0.0
 */
public interface EntityMapper<D, E> {

    /**
     * Map a DTO to an Entity
     *
     * @param dto the dto to map
     * @return an Entity
     */
    E toEntity(D dto);

    /**
     * Map an Entity to a DTO
     *
     * @param entity to map to a DTO
     * @return a DTO
     */
    D toDto(E entity);

    /**
     * Map a List of DTOs to a List of Entities
     *
     * @param dtoList the list to map
     * @return a list of Entities
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Map a list of Entities to a list of DTOs
     *
     * @param entityList the list to map
     * @return a list of DTOs
     */
    List<D> toDto(List<E> entityList);
}
