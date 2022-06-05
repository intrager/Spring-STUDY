package org.study.guestbook.service;

import org.study.guestbook.dto.GuestbookDTO;
import org.study.guestbook.dto.PageRequestDTO;
import org.study.guestbook.dto.PageResultDTO;
import org.study.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);

    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    GuestbookDTO read(Long gno);

    void remove(Long gno);
    void modify(GuestbookDTO dto);
    /**
     * Java 8 버전부터는 인터페이스의 실제 내용을 가지는 코드를 default 라는 키워드로 생성할 수 있음.
     * 이를 이용하면 기존에 추상 클래스를 통해서 전달해야 하는 실제 코드를 인터페이스에 선언할 수 있음.
     * 이를 통해서 '인터페이스 -> 추상 클래스 -> 구현 클래스'의 형태로 구현되던 방식에서 추상 클래슬르 생략하는 것이 가능해짐.
     */
    default Guestbook dtoToEntity(GuestbookDTO dto) {
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();
        return entity;
    }

    default GuestbookDTO entityToDTO(Guestbook entity) {
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .regDate(entity.getRegDate())
                .modDate(entity.getModDate())
                .build();
        return dto;
    }
}
