package org.mihalka.exercises_be.repository;

import org.mihalka.exercises_be.model.entity.AccessoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessoryRepository extends JpaRepository <AccessoryEntity, Long>{

    public AccessoryEntity findByAccessory_id(Long id);
}
