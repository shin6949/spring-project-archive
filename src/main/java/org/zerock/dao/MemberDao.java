package org.zerock.dao;

import org.springframework.stereotype.Repository;
import org.zerock.domain.Member;
import org.zerock.mapper.MemberMapper;

import java.util.HashMap;
import java.util.Map;

@Repository
public class MemberDao {
    private final MemberMapper memberMapper;

    public MemberDao(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public Member selectOneMemberByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return memberMapper.selectOneMemberByEmail(map);
    }

    public long insertMember(Member member) {
        return memberMapper.insertMember(member);
    }

    public Boolean checkExistEmail(String email) {
        // parameter로 주어진 이메일 주소가 존재하면 True, 존재하지 않으면 False
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        int count = memberMapper.selectUserCountByEmail(map);

        return count != 0;
    }
}