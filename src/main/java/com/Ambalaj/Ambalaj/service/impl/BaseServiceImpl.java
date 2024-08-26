package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public abstract class BaseServiceImpl<T, Id> implements BaseService<T, Id> {

    protected abstract JpaRepository<T, Id> getRepository();

    @Override
    public T addEntity(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public List<T> getEntities() {
        return getRepository().findAll();
    }

    Id getId(T entity) {
        return null;
    }

    @Override
    public T getEntityById(Id entityId, String entityName) {
        return getRepository().findById(entityId).orElseThrow(() -> new NotFoundException(entityName, entityId));
    }

    void updateEntityFields(T existingEntity, T updatedEntity) {
    }

    @Override
    public T updateEntity(T updatedEntity, Id entityId, String entityName) {
        T existingEntity = this.getEntityById(entityId, entityName);
        updateEntityFields(existingEntity, updatedEntity);
        return getRepository().save(existingEntity);
    }

    @Override
    public void deleteEntity(Id entityId, String entityName) {
        if (!getRepository().existsById(entityId)) throw new NotFoundException(entityName, entityId);
        getRepository().deleteById(entityId);
    }
}
