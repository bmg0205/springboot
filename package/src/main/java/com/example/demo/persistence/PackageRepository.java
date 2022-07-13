package com.example.demo.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.PackageEntity;

@Repository
public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
	
}
