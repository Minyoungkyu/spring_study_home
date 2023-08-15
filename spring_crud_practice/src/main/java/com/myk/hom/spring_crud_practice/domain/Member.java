package com.myk.hom.spring_crud_practice.domain;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @NonNull
    private String loginId;

    @NonNull
    private String loginPw;

    private int authLevel = 3;

    @NonNull
    private String name;

    @NonNull
    private String nickname;

    @NonNull
    private String cellphoneNo;

    @NonNull
    private String email;

    private int delStatus;

}
