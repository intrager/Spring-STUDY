package org.study.ex2.entity;

import lombok.*;

import javax.persistence.*;

@Entity // 해당 클래스가 entity를 위한 클래스이며, 해당 클래스의 인스턴스들이 JPA로 관리되는 엔티티 객체라는 것을 의미
@Table(name = "tbl_memo") // DB상에서 entity 클래스를 어떠한 테이블로 생성할 것인지에 대한 정보를 담기 위함
@ToString
@Getter // Getter 메서드 생성
@Builder // 객체를 생성할 수 있게 처리
@AllArgsConstructor // <=> @NoArgsConstructor
@NoArgsConstructor // <=> @AllArgsConstructor
public class Memo {
    @Id // PK에 해당하는 특정 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk를 자동으로 생성하고자 할 때 사용, oracle에서는 별도의 테이블 생성, mysql/mariaDB에서는 auto increment
    private Long mno;

    @Column(length = 200, nullable = false) // 추가적인 필드를 다양한 속성을 이용해 지정 가능/ 칼럼으로 생성되지 않는 필드의 경우 -> @Transient
    private String memoText;
}
