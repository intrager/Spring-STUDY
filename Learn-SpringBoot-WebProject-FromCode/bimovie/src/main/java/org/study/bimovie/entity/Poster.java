package org.study.bimovie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movie")
@Table(name = "tbl_poster")
public class Poster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    // 포스터 순번
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
