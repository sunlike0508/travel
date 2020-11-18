package com.travel.board.file;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class FileUtils {

	public String saveMultipartFile(MultipartFile multipartFile) throws Exception{

		/* 파일이 업로드될 폴더를 생성 */
		String path = "images/" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

		File file = new File(path);

		if(!file.exists()){
			file.mkdirs();
		}

		String contentType = multipartFile.getContentType();
		String filePath = null;

		if(!ObjectUtils.isEmpty(contentType)) {
			filePath = path + "/" + System.nanoTime() + multipartFile.getOriginalFilename();
			multipartFile.transferTo(new File(filePath));
		}

		return filePath;
	}
	
	public List<String> saveMultipartFiles(List<MultipartFile> multipartFiles) throws Exception{

		if(ObjectUtils.isEmpty(multipartFiles)){
			return null;
		}

		/* 파일이 업로드될 폴더를 생성 */
    	String path = "images/" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    	File file = new File(path);

		if(!file.exists()){
			file.mkdirs();
		}

		List<String> filePaths = new ArrayList<>();

		for(MultipartFile multipartFile : multipartFiles) {
			String contentType = multipartFile.getContentType();

			if(!ObjectUtils.isEmpty(contentType)) {
				String filePath = path + "/" + System.nanoTime() + multipartFile.getOriginalFilename();
				multipartFile.transferTo(new File(filePath));

				filePaths.add(filePath);
			}
		}

		return filePaths;
	}
}