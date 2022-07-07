package com.study.auth.service;

import com.study.auth.dto.MemoDTO;
import com.study.auth.entity.AuthMemberEntity;
import com.study.auth.entity.MemoEntity;

import java.util.List;

public interface MemoService {
    Long register(MemoDTO memoDTO);
    MemoDTO get(Long mno);
    void modify(MemoDTO memoDTO);
    void remove(Long mno);
    List<MemoDTO> getAllWithWriter(String writerEmail);

    default MemoEntity dtoToEntity(MemoDTO memoDTO) {
        MemoEntity memoEntity = MemoEntity.builder()
                .mno(memoDTO.getMno())
                .title(memoDTO.getTitle())
                .content(memoDTO.getContent())
                .writer(AuthMemberEntity.builder().email(memoDTO.getWriterEmail()).build())
                .build();
        return memoEntity;
    }

    default MemoDTO entityToDTO(MemoEntity memoEntity) {
        MemoDTO memoDTO = MemoDTO.builder()
                .mno(memoEntity.getMno())
                .title(memoEntity.getTitle())
                .content(memoEntity.getContent())
                .writerEmail(memoEntity.getWriter().getEmail())
                .regDate(memoEntity.getRegDate())
                .modDate(memoEntity.getModDate())
                .build();
        return memoDTO;
    }
}
