package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.MaterialSpecificationNameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialSpecificationNameRepository extends JpaRepository<MaterialSpecificationNameEntity, Long> {}
