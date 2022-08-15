package com.study.devstudy.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@SpringBootTest
public class LombokTests {

    /**
     * @NoArgsConstructor
     * @RequiredArgsConstructor
     * Board : @NoArgsConstructor, @RequiredArgsConstructor, @Getter, @Setter
     * , @EqualsAndHashCode(of="bno"), @ToString, @NonNull -> title
     */
//    @Test
//    public void testNoArgsConstructor() {
//        Board board = new Board();
//        System.out.println(board);
//        // Board(bno=0, title=null, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testRequiredArgsConstructor() {
//        Board board = new Board("테스트 제목");
//        System.out.println(board);
//        // Board(bno=0, title=테스트 제목, contents=null, writer=null, regDate=null)
//    }

    /**
     * @Getter @Setter
     * Board : @Getter, @Setter
     */
//    @Test
//    public void testGetter() {
//        Board board = new Board();
//        System.out.println(board.getTitle()); // null
//    }
//    @Test
//    public void testSetter() {
//        Board board = new Board();
//        board.setTitle("게시판 제목");
//        System.out.println(board.getTitle()); //게시판 제목
//    }

    /**
     * @ToString
     * Board : @ToString
     * Member : @ToString(exclude = "name")
     */
//    @Test
//    public void testToString() {
//        Board board = new Board();
//        System.out.println(board);
//        // Board(bno=0, title=null, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testToStringExclude() {
//        Member member = new Member();
//        System.out.println(member);
//        // Member(uid=null, pwd=null)
//    }

    /**
     * @EqualsAndHashCode
     * Board : @Getter, @Setter, @ToString, @EqualsAndHashCode(of="bno")
     * Student : @Getter, @Setter, @ToString, @EqualsAndHashCode(of="sno")
     * UserItem : @Getter, @Setter, @ToString, @EqualsAndHashCode(of={"uno", "itemId"})
     */
//    @Test
//    public void testBoard() {
//        Board board1 = new Board();
//        board1.setBno(1);
//        board1.setTitle("title1");
//
//        Board board2 = new Board();
//        board1.setBno(2);
//        board1.setTitle("title2");
//
//        Board board3 = new Board();
//        board1.setBno(2);
//        board1.setTitle("title3");
//
//        Set<Board> boardSet = new HashSet<Board>();
//
//        boardSet.add(board1);
//        boardSet.add(board2);
//        boardSet.add(board3);
//
//        System.out.println("저장된 데이터 수 : " + boardSet.size());
//
//        Iterator<Board> it = boardSet.iterator();
//        while(it.hasNext()) System.out.println(it.next());
////        Board(bno=0, title=null, contents=null, writer=null, regDate=null)
////        Board(bno=2, title=title3, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testUserItem() {
//        UserItem userItem1 = new UserItem();
//        userItem1.setUno(1);
//        userItem1.setItemId(100);
//        userItem1.setDescription("userItem1");
//
//        UserItem userItem2 = new UserItem();
//        userItem2.setUno(1);
//        userItem2.setItemId(200);
//        userItem2.setDescription("userItem2");
//
//        UserItem userItem3 = new UserItem();
//        userItem3.setUno(1);
//        userItem3.setItemId(200);
//        userItem3.setDescription("userItem3");
//
//        Set<UserItem> userItemSet = new HashSet<UserItem>();
//
//        userItemSet.add(userItem1);
//        userItemSet.add(userItem2);
//        userItemSet.add(userItem3);
//
//        System.out.println("저장된 데이터 수 : " + userItemSet.size());
//
//        Iterator<UserItem> it = userItemSet.iterator();
//        while(it.hasNext()) System.out.println(it.next());
////        UserItem(uno=1, itemId=100, itemName=null, price=null, description=userItem1)
////        UserItem(uno=1, itemId=200, itemName=null, price=null, description=userItem2)
//    }
//    @Test
//    public void testStudent() {
//        Student student1 = new Student();
//        student1.setSno(1);
//        student1.setName("Han");
//
//        Student student2 = new Student();
//        student2.setSno(2);
//        student2.setName("Han");
//
//        Set<Student> studentSet = new HashSet<Student>();
//
//        studentSet.add(student1);
//        studentSet.add(student2);
//
//        System.out.println("저장된 데이터 수 : " + studentSet.size());
//
//        Iterator<Student> it = studentSet.iterator();
//        while(it.hasNext()) System.out.println(it.next());
////        Student(sno=1, name=Han)
////        Student(sno=2, name=Han)
//    }

    /**
     * @NoArgsConstructor
     * @AllArgsConstructor
     * Board : @ToString
     * Member : @NoArgsConstructor, @AllArgsConstructor, @ToString
     */
//    @Test
//    public void testNoArgsConstructor() {
//        Board board = new Board();
//        System.out.println(board);
//        // Board(bno=0, title=null, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testNoArgsConstructor2() {
//        Member member = new Member();
//        System.out.println(member);
//        // Member(uid=null, pwd=null, name=null)
//    }

    /**
     * @RequiredArgsConstructor
     * Board : @RequiredArgsConstructor, @ToString, @NonNull -> Integer bno
     * Member : @RequiredArgsConstructor, @ToString, final -> uid, pwd
     */
//    @Test
//    public void testRequiredArgsConstructor() {
//        Board board = new Board(1);
//        System.out.println(board);
//        // Board(bno=1, title=null, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testRequiredArgsConstructor2() {
//        Member member = new Member("uid1", "pwd1");
//        System.out.println(member);
//        // Member(uid=uid1, pwd=pwd1, name=null)
//    }

    /**
     * @AllArgsConstructor
     * Board : @AllArgsConstructor, @ToString
     * Member : @AllArgsConstructor, @ToString
     */
//    @Test
//    public void testAllArgsConstructor() {
//        Board board = new Board(1, "title1", "contents1", "writer1",
//                LocalDateTime.now());
//        System.out.println(board);
//        // Board(bno=1, title=title1, contents=contents1, writer=writer1, regDate=2022-08-15T18:51:27.346829200)
//    }
//    @Test
//    public void testAllArgsConstructor2() {
//        Member member = new Member("uid1", "pwd1", "name1");
//        System.out.println(member);
//        // Member(uid=uid1, pwd=pwd1, name=name1)
//    }

//    /**
//     * @Data == @ToString + @Getter + @Setter
//     *  + @EqualsAndHashCode + @RequiredArgsConstructor
//     *  Board : @Data, final int -> bno
//     */
//    @Test
//    public void testRequiredArgsConstructor() {
//        Board board = new Board(1);
//        System.out.println(board);
//        // Board(bno=1, title=null, contents=null, writer=null, regDate=null)
//    }
//    @Test
//    public void testGetterSetter() {
//        Board board = new Board(1);
//        board.setTitle("게시판 제목");
//        System.out.println(board.getTitle()); // 게시판 제목
//    }
//    @Test
//    public void testToString() {
//        Board board = new Board(1);
//        System.out.println(board);
//        // Board(bno=1, title=null, contents=null, writer=null, regDate=null)
//    }

    /**
     * @Builder
     * Board : @ToString, @Builder
     * Member : @ToString,
     *   => @Builder : public Member(String uid, String pwd) {}
     */
//    @Test
//    public void testBoardBuilder() {
//        Board board = Board.builder()
//                .bno(1)
//                .title("title1")
//                .contents("contents1")
//                .writer("writer1")
//                .regDate(LocalDateTime.now())
//                .build();
//        System.out.println(board);
//        // Board(bno=1, title=title1, contents=contents1, writer=writer1,
//        // regDate=2022-08-15T19:10:33.729216700)
//    }
//    @Test
//    public void testMemberBuilder() {
//        Member member = Member.builder()
//                .uid("uid1")
//                .pwd("pwd1")
//                .build();
//        System.out.println(member);
//        // Member(uid=uid1, pwd=pwd1, name=null)
//    }

    /**
     * @Slf4j
     */
}
