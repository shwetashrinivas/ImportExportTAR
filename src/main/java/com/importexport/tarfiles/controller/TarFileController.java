package com.importexport.tarfiles.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;

import com.importexport.tarfiles.exceptions.TarFileAlreadyExistsException;
import com.importexport.tarfiles.exceptions.TarFileNotFoundException;
import com.importexport.tarfiles.model.TarFile;
import com.importexport.tarfiles.service.TarFileService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tar")
public class TarFileController {

	private TarFileService tarFileService;

	@Autowired
	public TarFileController(TarFileService tarFileService) {
		this.tarFileService = tarFileService;
	}

	@PostMapping("/import")
	public ResponseEntity<?> importTAR(@RequestParam("tarFile") MultipartFile tarFile) throws TarFileAlreadyExistsException {
	
		try {
            TarFile savetarFile = new TarFile();
            savetarFile.setFile(new Binary(BsonBinarySubType.BINARY, tarFile.getBytes()));
            String fileName = StringUtils.cleanPath(tarFile.getOriginalFilename());
            savetarFile.setFileTitle(fileName);
            return new ResponseEntity<>(tarFileService.importTARFile(savetarFile), HttpStatus.CREATED);
        } catch (TarFileAlreadyExistsException tarFileAlreadyExistsException) {
            return new ResponseEntity<>(tarFileAlreadyExistsException.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	
	@GetMapping("/export/{fileTitle:.+}")
	public ResponseEntity<?> exportTAR(@PathVariable String fileTitle) {
		try {
			TarFile tarFile = tarFileService.exportTARFile(fileTitle);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("application/tar+gzip"))
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + tarFile.getFileTitle() + "\"")
					.body(new ByteArrayResource(tarFile.getFile().getData()));
		} catch (TarFileNotFoundException tarFileNotFoundException) {
			return new ResponseEntity<>(tarFileNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/files")
	public ResponseEntity<?> listTARFiles() {
		try {
			return new ResponseEntity<>(tarFileService.listAllTarFiles(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
