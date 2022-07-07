package com.study.auth.service;

import com.study.auth.dto.MemoDTO;
import com.study.auth.entity.MemoEntity;
import com.study.auth.repository.MemoRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
public class MemoServiceImpl implements MemoService {
    private final MemoRepository memoRepository;

    public MemoServiceImpl(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @Override
    public Long register(MemoDTO memoDTO) {

        MemoEntity memoEntity = dtoToEntity(memoDTO);

        log.info("==================================");
        log.info(memoEntity);

        memoRepository.save(memoEntity);

        return memoEntity.getMno();
    }

    @Override
    public MemoDTO get(Long mno) {
        Optional<MemoEntity> result = memoRepository.getWithWriter(mno);

        if(result.isPresent()) {
            return entityToDTO(result.get());
        }
        return null;
    }

    @Override
    public void modify(MemoDTO memoDTO) {
        Long mno = memoDTO.getMno();

        Optional<MemoEntity> result = memoRepository.findById(mno);

        if(result.isPresent()) {
            MemoEntity memoEntity = result.get();

            memoEntity.changeTitle(memoDTO.getTitle());
            memoEntity.changeContent(memoDTO.getContent());
            memoRepository.save(memoEntity);
        }
    }

    @Override
    public void remove(Long mno) {
        memoRepository.deleteById(mno);
    }

    @Override
    public List<MemoDTO> getAllWithWriter(String writerEmail) {
        List<MemoEntity> memoEntityList = memoRepository.getList(writerEmail);

        return memoEntityList.stream().map(memoEntity -> entityToDTO(memoEntity))
                .collect(Collectors.toList());
    }
}
