package org.study.bimovie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "movieEntity")
@Table(name = "tbl_poster")
public class PosterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    // 포스터 순번
    private int idx;

    @ManyToOne(fetch = FetchType.LAZY)
    private MovieEntity movieEntity;

    public void setIdx(int idx) {
        this.idx = idx;
    }
    public void setMovieEntity(MovieEntity movieEntity) {
        this.movieEntity = movieEntity;
    }
}
