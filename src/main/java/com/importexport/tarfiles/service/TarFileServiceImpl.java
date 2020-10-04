package com.importexport.tarfiles.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.importexport.tarfiles.exceptions.TarFileAlreadyExistsException;
import com.importexport.tarfiles.exceptions.TarFileNotFoundException;
import com.importexport.tarfiles.model.TarFile;
import com.importexport.tarfiles.repository.TarFileRepository;

@Service
public class TarFileServiceImpl implements TarFileService {

	private TarFileRepository tarFileRepo;

	@Autowired
	public TarFileServiceImpl(TarFileRepository tarFileRepo) {
		this.tarFileRepo = tarFileRepo;
	}

//	@Override
//	public TarFile importTARFile(TarFile file) throws TarFileAlreadyExistsException, IOException {
//		if (file.getFileTitle().matches("([a-zA-Z0-9\\s_\\\\.\\-\\(\\):])+(.tar|.tar.xz)$")) {
//			if (tarFileRepo.findByFileName(tarFile.getFileName()) == null) {
//				TarFile savedFile = tarFileRepo.save(tarFile);
//				ResponseTarFile responseTarFile = new ResponseTarFile();
//				responseTarFile.setFileId(savedFile.getFileId());
//				responseTarFile.setFileName(savedFile.getFileTitle());
//				responseTarFile.setFileUri(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/tarFile/")
//						.path(savedFile.getFileTitle()).toUriString());
//				return responseTarFile;
//			} else {
//				throw new TarFileAlreadyExistsException(environment.getProperty("tarFileAlreadyExists.message"));
//			}
//		} else {
//			throw new InvalidFileNameException(environment.getProperty("invalidFileName.message"));
//		}
//	}

	@Override
	public TarFile importTARFile(TarFile tarFile) throws TarFileAlreadyExistsException {
		if (!tarFileRepo.existsByFileTitle(tarFile.getFileTitle())) {
			TarFile file = tarFileRepo.save(tarFile);
			return file;
		} else
			throw new TarFileAlreadyExistsException("This TAR file already exists in the DB");
	}
	
	@Override
	public TarFile exportTARFile(String fileTitle) throws TarFileNotFoundException {
		TarFile tarFile = tarFileRepo.findByFileTitle(fileTitle);
		if (tarFile != null) {
			return tarFile;
		} else {
			throw new TarFileNotFoundException("Tar File doesn't exist. Please check name again!");
		}
	}

	@Override
	public List<TarFile> listAllTarFiles() {
		return tarFileRepo.findAll();
	}
}