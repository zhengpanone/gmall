package com.zp.common.convert;

import java.util.List;

public interface BaseConvert<D, E, V> {
    /**
     * DTO to Entity
     *
     * @param dto
     * @return
     */
    E toEntity(D dto);

    V toVO(E entity);

    /**
     * Entity to DTO
     *
     * @param entity
     * @return
     */
    D toDTO(E entity);

    /**
     * DTO集合 to Entity
     *
     * @param dtoList
     * @return
     */
    List<E> toEntity(List<D> dtoList);

    /**
     * Entity集合 to DTO
     *
     * @param entityList
     * @return
     */
    List<D> toDTOs(List<E> entityList);

    List<V> toVOs(List<E> entity);


}
