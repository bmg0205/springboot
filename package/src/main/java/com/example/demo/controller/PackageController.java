package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ResponseDTO;
import com.example.demo.model.PackageEntity;
import com.example.demo.service.PackageService;

@RestController 
@RequestMapping("package")
public class PackageController {
	@Autowired 
	private PackageService service;

	@PostMapping
	public ResponseEntity<?> createPackage(@RequestBody PackageEntity entity) {
		try {
			entity.setPno(0);
			List<PackageEntity> entities = service.create(entity);
			ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().data(entities).build();
			return ResponseEntity.ok().body(response);
		
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping
	public ResponseEntity<?> outputPackageList() {
		List<PackageEntity> entities = service.output();
		ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().data(entities).build();
		return ResponseEntity.ok().body(response);
	}

	@PutMapping
	public ResponseEntity<?> updatePackage(@RequestBody PackageEntity entity) {
		List<PackageEntity> entities = service.update(entity);
		ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().data(entities).build();
		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody PackageEntity entity) {
		try {
			List<PackageEntity> entities = service.delete(entity);
			ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().data(entities).build();
			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<PackageEntity> response = ResponseDTO.<PackageEntity>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}
}
