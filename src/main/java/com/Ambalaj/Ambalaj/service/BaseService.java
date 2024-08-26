package com.Ambalaj.Ambalaj.service;

import com.Ambalaj.Ambalaj.dto.PaginatedDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BaseService<T, Id> {
    T addEntity(T itemEntity);

    List<T> getEntities();

    T getEntityById(Id entityId, String entityName);

    T updateEntity(T entity, Id entityId, String entityName);

    void deleteEntity(Id entityId, String entityName);
}
