package org.study.board.repository.search;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.study.board.entity.Board;
import org.study.board.entity.QBoard;
import org.study.board.entity.QMember;
import org.study.board.entity.QReply;

import java.util.List;

@Log4j2
public class SearchBoardRepositoryImpl extends QuerydslRepositorySupport implements SearchBoardRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public SearchBoardRepositoryImpl() {
        super(Board.class);
    }

    @Override
    public Board search1() {
        log.info("search1 --------------");

        QBoard board = QBoard.board;
        QReply reply = QReply.reply;
        QMember member = QMember.member;

        JPQLQuery<Board> jpqlQuery = from(board);
        jpqlQuery.leftJoin(member).on(board.writer.eq(member));
        jpqlQuery.leftJoin(reply).on(reply.board.eq(board));

        // 정해진 엔티티 객체 단위가 아니라 각 데이터를 추출하는 경우에는 Tuple이라는 객체 이용
        JPQLQuery<Tuple> tuple = jpqlQuery.select(board, member.email, reply.count());
        tuple.groupBy(board);

        log.info("----------------------------------------");
        log.info(tuple);
        log.info("----------------------------------------");

        List<Board> result = jpqlQuery.fetch();
        log.info(result);

        return null;
    }
}
