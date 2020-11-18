package com.travel.board.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class BoardBaseDTO {

	private long id;
	private String title;
	private String location;
	private String contents;
	private String parties;
	private String creatorId;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String mainPhotoPath;
	private List<BoardDetailDTO> boardDetailDTOs;
	private MultipartFile multipartFile;
}
