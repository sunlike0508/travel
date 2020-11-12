package com.member.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="member_base")
@Data
@Entity
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "member_id", unique = true, updatable = false)
    private String memberId;

    @Column(name = "password")
    private String password;

    @Column(name = "create_date")
    private LocalDateTime createdDatetime = LocalDateTime.now();

}
