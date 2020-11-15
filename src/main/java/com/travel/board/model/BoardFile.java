package com.travel.board.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="board_file")
@Data
@Entity
@NoArgsConstructor
public class BoardFile {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_detail_id")
	private BoardDetail boardDetail;

	@Column(name = "photo_path", nullable=false)
	private String photoPath;

	@Column(name = "file_size", nullable=false)
	private long fileSize;

	@Column(name = "creator_id", nullable=false)
	private String creatorId;

	@Column(name =  "create_at", nullable=false)
	@CreationTimestamp
	private LocalDateTime createdDatetime;
}
