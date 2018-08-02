package com.wissen.justhire.service;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class StorageService {
	Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private final Path rootLocation = Paths.get("upload-dir");
	private final Path resumePath = Paths.get("Resumes");
 
	public Path getRootLocation() {
		return rootLocation;
	}

	public Path getResumePath() {
		return resumePath;
	}

	public String store(MultipartFile file) {
		String name=null;
		try {
			name = String.valueOf(System.currentTimeMillis()/1000L)+".csv";
			Files.copy(file.getInputStream(), this.rootLocation.resolve(name));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
		return name;
	}
	
	public void storeResume(MultipartFile file, int candidateId) {
		try {
			Files.copy(file.getInputStream(), this.resumePath.resolve(candidateId+".pdf"));
		} catch (Exception e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public Resource loadFile(String filename) {
		try {
			Path file = resumePath.resolve(filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			} else {
				throw new RuntimeException("FAIL!");
			}
		} catch (MalformedURLException e) {
			throw new RuntimeException("FAIL!");
		}
	}
 
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}
 
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize storage!");
		}
	}

}
