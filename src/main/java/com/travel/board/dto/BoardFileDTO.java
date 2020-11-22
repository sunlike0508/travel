package com.travel.board.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class BoardFileDTO {

	private long id;

	private long boardDetailId;

	private String photoPath;

	private long fileSize;

	private MultipartFile multipartFile;
}
