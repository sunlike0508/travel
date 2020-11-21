package com.travel.board.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BoardDetailDTO {

	private long id;

	private long boardBaseId;

	private LocalDateTime travelDate;

	private List<BoardFileDTO> boardFileDTOs;
}
