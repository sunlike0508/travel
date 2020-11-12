package com.travel.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
	@JoinColumn(name = "boardDetail_id")
	private BoardDetail boardDetail;
	
	@Column(nullable=false)
	private String originalFileName;
	
	@Column(nullable=false)
	private String storedFilePath;
	
	@Column(nullable=false)
	private long fileSize;

	@Column(nullable=false)
	private String creatorId;
	
	@Column(nullable=false)
	private LocalDateTime createdDatetime = LocalDateTime.now();
	
	private String updaterId;
	
	private LocalDateTime updatedDatetime;
}
