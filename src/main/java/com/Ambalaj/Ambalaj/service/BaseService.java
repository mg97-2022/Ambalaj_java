package com.Ambalaj.Ambalaj.service;

import java.util.List;

public interface BaseService<T, Id> {
    T addEntity(T itemEntity);

    List<T> getEntities();

    List<T> getEntitiesByIds(List<Id> entityIds, String entityName);

    T getEntityById(Id entityId, String entityName);

    T updateEntity(T entity, Id entityId, String entityName);

    void deleteEntity(Id entityId, String entityName);
}
