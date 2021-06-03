package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Isbn;

@Mapper
public interface IsbnMapper {
    int insertIsbn(Isbn isbn);
}
