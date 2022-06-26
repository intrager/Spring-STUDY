package org.review.moviereview.repository;

import org.junit.jupiter.api.Test;
import org.review.moviereview.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("r" + i + "@review.org")
                    .pw("qwer")
                    .nickname("reviewer" + i).build();
            memberRepository.save(member);
        });
    }
}
