package com.Ambalaj.Ambalaj.service.impl;

import com.Ambalaj.Ambalaj.exception.NotFoundException;
import com.Ambalaj.Ambalaj.service.BaseService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    protected abstract Id getId(T entity);

    @Override
    public List<T> getEntitiesByIds(List<Id> entityIds, String entityName) {
        List<T> entities = getRepository().findAllById(entityIds);
        Set<Id> foundIds = entities.stream().map(this::getId).collect(Collectors.toSet());
        List<Id> missingIds = entityIds.stream().filter(id -> !foundIds.contains(id)).toList();
        if (!missingIds.isEmpty()) {
            throw new NotFoundException(entityName + " not found for IDs: " + missingIds);
        }
        return entities;
    }

    @Override
    public T getEntityById(Id entityId, String entityName) {
        return getRepository().findById(entityId)
                .orElseThrow(() -> new NotFoundException(entityName + " not found for ID: " + entityId));
    }

    protected abstract void updateEntityFields(T existingEntity, T updatedEntity);

    @Override
    public T updateEntity(T updatedEntity, Id entityId, String entityName) {
        T existingEntity = this.getEntityById(entityId, entityName);
        updateEntityFields(existingEntity, updatedEntity);
        return getRepository().save(existingEntity);
    }

    @Override
    public void deleteEntity(Id entityId, String entityName) {
        if (!getRepository().existsById(entityId))
            throw new NotFoundException(entityName + " not found with ID: " + entityId);
        getRepository().deleteById(entityId);
    }
}
