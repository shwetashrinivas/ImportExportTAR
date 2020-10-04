package com.importexport.tarfiles.service;

import java.io.IOException;
import java.util.List;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.importexport.tarfiles.exceptions.TarFileAlreadyExistsException;
import com.importexport.tarfiles.exceptions.TarFileNotFoundException;
import com.importexport.tarfiles.model.TarFile;
import com.importexport.tarfiles.repository.TarFileRepository;

@Service
public class TarFileServiceImpl implements TarFileService {
    
    private TarFileRepository tarFileRepo; 
    
    @Autowired
    public TarFileServiceImpl(TarFileRepository tarFileRepo) {
		super();
		this.tarFileRepo = tarFileRepo;
	}

	public String importTARFile(String fileTitle, MultipartFile file) throws TarFileAlreadyExistsException, IOException { 
        TarFile tarFile = new TarFile(fileTitle); 
        tarFile.setFile(
          new Binary(BsonBinarySubType.BINARY, file.getBytes())); 
        tarFile = tarFileRepo.insert(tarFile); return tarFile.getId(); 
    }
 
    public TarFile exportTARFile(String id) throws TarFileNotFoundException{ 
        return tarFileRepo.findById(id).get(); 
    }
    
	public List<TarFile> listAllTarFiles() {
		return tarFileRepo.findAll();
	}
}