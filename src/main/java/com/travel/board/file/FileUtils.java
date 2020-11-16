package com.travel.board.file;

import com.travel.board.model.BoardFile;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class FileUtils {
	
	public String parseFileInfo(MultipartHttpServletRequest multipartHttpServletRequest) throws Exception{

		if(ObjectUtils.isEmpty(multipartHttpServletRequest)){
			return null;
		}

		/* 파일이 업로드될 폴더를 생성 */
    	String path = "images/" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));

    	File file = new File(path);

		if(!file.exists()){
			file.mkdirs();
		}
		
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();

		/* 파일 형식 확인 및 이미지 확장자 지정 */
		while(iterator.hasNext()){

			MultipartFile multipartFile = multipartHttpServletRequest.getFiles(iterator.next()).get(0);

			if(!multipartFile.isEmpty()) {
				String contentType = multipartFile.getContentType();

				if (!ObjectUtils.isEmpty(contentType)) {
					String newFileName = path + "/" + System.nanoTime() + multipartFile.getOriginalFilename();
					multipartFile.transferTo(new File(newFileName));

					return newFileName;
				}
			}
		}

		return null;
	}
}