package com.travel.board.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="board_detail")
@Data
@Entity
@NoArgsConstructor
public class BoardDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="boardBase_id")
	private BoardBase boardBase;

	@Column(name = "travel_date")
	private LocalDateTime travelDate;

	private String board_id;
	
	@Column(name = "created_Date_time", nullable=false)
	private LocalDateTime createdDatetime = LocalDateTime.now();

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "boardDetail", cascade=CascadeType.ALL)
	private List<BoardFile> boardFiles;
}
