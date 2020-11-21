package com.travel.board.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.internal.util.ToStringBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="board_base")
@Getter
@Setter
@ToString(exclude = "boardDetails")
@Entity
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

	@Column(name = "creator_Id", nullable=false)
	private String creatorId;

	@Column(name = "start_date")
	private LocalDateTime startDate;

	@Column(name = "end_date")
	private LocalDateTime endDate;

	@Column(name = "main_photo_path")
	private String mainPhotoPath;
	
	@Column(name = "create_at", nullable=false)
	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "boardBase", cascade=CascadeType.ALL)
	private List<BoardDetail> boardDetails;
}
