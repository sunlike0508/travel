package com.travel.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Table(name="board")
@Data
@Builder
@Entity
@NoArgsConstructor
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idx")
	private long idx;

	@Column(name = "title", nullable=false)
	private String title;
	
	@Column(nullable=false)
	private String contents;

	@Column(nullable=false)
	private String creatorId;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;
	
	@Column(nullable=false)
	private LocalDateTime createdDatetime = LocalDateTime.now();

	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="idx")
	private Collection<BoardFile> fileList;
}
