package com.travel.board.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
	@JoinColumn(name="board_base_id")
	private BoardBase boardBase;

	@Column(name = "travel_date")
	private LocalDateTime travelDate;

	@Column(name = "created_at", nullable=false)
	@CreationTimestamp
	private LocalDateTime createdDatetime;

	@OneToMany(fetch=FetchType.EAGER, mappedBy = "boardDetail", cascade=CascadeType.ALL)
	private List<BoardFile> boardFiles;
}
