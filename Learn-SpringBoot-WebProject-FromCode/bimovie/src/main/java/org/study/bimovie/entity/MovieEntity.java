package org.study.bimovie.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "posterList")
@Table(name = "tbl_movie")
public class MovieEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mno;

    private String title;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "movieEntity",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PosterEntity> posterList = new ArrayList<>();

    public void addPoster(PosterEntity posterEntity) {
        posterEntity.setIdx(this.posterList.size());
        posterEntity.setMovieEntity(this);
        posterList.add(posterEntity);
    }

    public void removePoster(Long pno) {
        Optional<PosterEntity> result = posterList.stream().filter(p -> p.getPno() == pno).findFirst();

        result.ifPresent(p -> {
            p.setMovieEntity(null);
            posterList.remove(p);
        });

        // 포스터 번호 변경
        changeIdx();
    }

    private void changeIdx() {
        for(int i = 0; i < posterList.size(); i++) {
            posterList.get(i).setIdx(i);
        }
    }
}
