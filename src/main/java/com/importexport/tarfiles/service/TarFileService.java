package com.importexport.tarfiles.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.importexport.tarfiles.exceptions.TarFileAlreadyExistsException;
import com.importexport.tarfiles.exceptions.TarFileNotFoundException;
import com.importexport.tarfiles.model.TarFile;

@Service
public interface TarFileService {

	public TarFile importTARFile(TarFile tarFile) throws TarFileAlreadyExistsException; 
    public TarFile exportTARFile(String fileTitle) throws TarFileNotFoundException;
	public List<TarFile> listAllTarFiles();
}
