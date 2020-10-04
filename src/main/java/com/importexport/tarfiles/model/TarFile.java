package com.importexport.tarfiles.model;

import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tar_files")
public class TarFile {

    @Id
    private String id;
    
    private String fileTitle;
        
    private Binary file;

	public TarFile(String id, String fileTitle, Binary file) {
		super();
		this.id = id;
		this.fileTitle = fileTitle;
		this.file = file;
	}

	public TarFile(String fileTitle) {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public Binary getFile() {
		return file;
	}

	public void setFile(Binary file) {
		this.file = file;
	}
    
    
}
