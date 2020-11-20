package com.travel.board.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class BoardFileDTO {

	private long id;

	private String boardDetailId;

	private String photoPath;

	private long fileSize;

	private String creatorId;

	private MultipartFile multipartFile;
}
