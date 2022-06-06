package org.study.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)  // LAZY 로딩하게끔 명시적으로 지정
    private Member writer;  // 연관관계 지정

    public void changeTitle(String title) {
        this.title = title;
    }
    public void changeContent(String content) {
        this.content = content;
    }
}
/**
 * Eager Loading - 특정한 엔티티를 조회할 때 연관관계를 가진 모든 엔티티를 같이 로딩. 즉시 로딩
 * 한 번에 연관관계가 있는 모든 엔티티를 가져온다는 장점도 있지만,
 * 여러 연관관계를 맺고 있거나 연관관계가 복잡할수록 조인으로 인한 성능 저하가 야기됨.
 * <----> 이에 반대되는 로딩은 LAZY Loading. 지연 로딩
 */
