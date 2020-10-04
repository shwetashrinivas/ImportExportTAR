package com.importexport.tarfiles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.importexport.tarfiles.model.TarFile;

public interface TarFileRepository extends MongoRepository<TarFile, String> { 
	
}
