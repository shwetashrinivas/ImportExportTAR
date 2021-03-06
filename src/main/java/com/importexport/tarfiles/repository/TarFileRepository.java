package com.importexport.tarfiles.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.importexport.tarfiles.model.TarFile;

@Repository
public interface TarFileRepository extends MongoRepository<TarFile, String> { 
	public TarFile findByFileTitle(String fileTitle);

	boolean existsByFileTitle(String fileTitle);
}
