package com.travel.board.dto;

import com.travel.board.model.BoardFile;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BoardDetailDTO {

	private long id;

	private String board_base_id;

	private String travelDate;

	private List<BoardFileDTO> boardFileDTOs;
}
