package com.bootcamptwo.assignment;

import com.bootcamptwo.assignment.domain.ResultVO;
import org.junit.jupiter.api.Test;

public class LombokTest {

    @Test
    public void ResultTest() {
        ResultVO result = new ResultVO(0, "han");
        System.out.println(result);
    }
}
