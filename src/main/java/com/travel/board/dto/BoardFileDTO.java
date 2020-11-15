package com.travel.board.dto;

import com.travel.board.model.BoardDetail;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
public class BoardFileDTO {

	private long id;

	private String board_detail_id;

	private String photoPath;

	private long fileSize;

	private String creatorId;

}
