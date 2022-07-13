package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.PackageEntity;
import com.example.demo.persistence.PackageRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PackageService {


	@Autowired
	private PackageRepository repository;

		
	public List<PackageEntity> create(final PackageEntity entity) {

		repository.save(entity); 
		log.info("Entity Id : {} is saved.");
		return output();
	}
		
	public List<PackageEntity> output() {
		return repository.findAll();
	}
	
	public List<PackageEntity> update(final PackageEntity entity) {
		final Optional<PackageEntity> original = repository.findById(entity.getPno());
		
		if(original.isPresent()) {
			final PackageEntity pack = original.get();
			pack.setTitle(entity.getTitle());
			pack.setPrice(entity.getPrice());
			pack.setContent(entity.getContent());
			pack.setDepDate(entity.getDepDate());
			pack.setPeriod(entity.getPeriod());
			pack.setMatchDate(entity.getMatchDate());
			
			repository.save(pack);
		}
		return output();
	}
	
	public List<PackageEntity> delete(final PackageEntity entity) {
		try {
			repository.delete(entity);
		} catch(Exception e) {
			log.error("error deleting entity ", entity.getPno(), e);
			throw new RuntimeException("error deleting entity " + entity.getPno());
		}
		return output();
	}

}
