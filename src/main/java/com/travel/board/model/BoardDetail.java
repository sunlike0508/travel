package com.travel.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name="board_detail")
@Getter
@Setter
@ToString(exclude = "boardFiles")
@Entity
public class BoardDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@ManyToOne(targetEntity = BoardBase.class, optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="board_base_id")
	private BoardBase boardBase;

	@Column(name = "travel_date")
	private LocalDateTime travelDate;

	@Column(name = "create_at", nullable=false)
	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@OneToMany(fetch=FetchType.LAZY, mappedBy = "boardDetail", cascade=CascadeType.ALL)
	private List<BoardFile> boardFiles;
}
