package org.study.board.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String text;
    private String replier;

    @ManyToOne
    private Board board;    // 연관관계 지정
}
/**
 * @ToString은 해당 클래스의 모든 멤버 변수를 출력시킴. 이때 board 객체 역시 출력해야 하고,
 * Board를 출력하기 위해서는 Board 객체의 toString()가 호출되어야 한다. 그때 데이터베이스의 연결이 요구된다.
 * exclude 속성은 해당 값으로 지정된 변수를 toString()에서 제외시키기 때문에, LAZY Loading을 할 때에는 지정해줘야 한다.
 */