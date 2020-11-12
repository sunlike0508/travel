package com.travel.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="board_base")
@Data
@Entity
@NoArgsConstructor
public class BoardBase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@Column(name = "title")
	private String title;

	@Column(name = "location", nullable=false)
	private String location;

	@Column(name = "contents", nullable=false)
	private String contents;

	@Column(name = "parties", nullable=false)
	private String parties;

	@Column(name = "creatorId", nullable=false)
	private String creatorId;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "main_url")
	private String main_url;
	
	@Column(name = "created_Date_time", nullable=false)
	private LocalDateTime createdDatetime = LocalDateTime.now();

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "board", cascade=CascadeType.ALL)
	private List<BoardDetail> boardDetails;
}
