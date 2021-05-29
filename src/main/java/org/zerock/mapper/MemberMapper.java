package org.zerock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.zerock.domain.Member;

import java.util.Map;

@Mapper
public interface MemberMapper {
    Member selectOneMemberByEmail(Map<String, Object> params);
    int selectUserCountByEmail(Map<String, Object> params);
    int insertMember(Member member);
}
