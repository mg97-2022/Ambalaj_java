package com.Ambalaj.Ambalaj.repository;

import com.Ambalaj.Ambalaj.model.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {}
