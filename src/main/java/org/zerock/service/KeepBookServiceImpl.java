package org.zerock.service;

import org.springframework.stereotype.Service;
import org.zerock.domain.Criteria;
import org.zerock.domain.KeepBook;
import org.zerock.mapper.KeepBookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeepBookServiceImpl implements KeepBookService {
    private final KeepBookMapper keepBookMapper;

    public KeepBookServiceImpl(KeepBookMapper keepBookMapper) {
        this.keepBookMapper = keepBookMapper;
    }

    @Override
    public List<KeepBook> selectKeepBook() {
        return keepBookMapper.selectKeepBook();
    }

    @Override
    public KeepBook selectKeepBookById(Long id) {
        return keepBookMapper.selectKeepBookById(id);
    }

    @Override
    public List<KeepBook> selectKeepBookWithPaging(Criteria cri) {
        Map<String, Object> params = new HashMap<>();

        int from = (cri.getPageNum() - 1) * cri.getAmount();
        int to = cri.getPageNum() * cri.getAmount();

        params.put("from", from);
        params.put("to", to);

        return keepBookMapper.selectKeepBookWithPaging(params);
    }

    @Override
    public int getTotalCount() {
        return keepBookMapper.getTotalCount();
    }
}
