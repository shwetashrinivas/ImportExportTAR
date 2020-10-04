package com.importexport.tarfiles.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.importexport.tarfiles.exceptions.TarFileAlreadyExistsException;
import com.importexport.tarfiles.exceptions.TarFileNotFoundException;
import com.importexport.tarfiles.model.TarFile;

public interface TarFileService {

	public String importTARFile(String fileTitle, MultipartFile file) throws TarFileAlreadyExistsException, IOException; 
    public TarFile exportTARFile(String id) throws TarFileNotFoundException;
	public List<TarFile> listAllTarFiles();
}
