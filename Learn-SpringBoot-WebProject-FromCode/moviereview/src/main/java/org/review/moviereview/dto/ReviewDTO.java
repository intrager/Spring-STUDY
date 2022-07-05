package org.review.moviereview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    // review rno
    private Long rno;

    private int grade;
    private String text;

    // Movie no
    private Long mno;
    // Member
    private Long mid;
    private String nickname;
    private String email;

    private LocalDateTime regDate, modDate;
}
