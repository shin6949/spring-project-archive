package org.zerock.dao;

import org.springframework.stereotype.Repository;
import org.zerock.domain.MemberRole;
import org.zerock.mapper.MemberRoleMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRoleDao {
    private final MemberRoleMapper memberRoleMapper;

    public MemberRoleDao(MemberRoleMapper memberRoleMapper) {
        this.memberRoleMapper = memberRoleMapper;
    }

    public List<MemberRole> selectMemberRoleByEmail(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("email", email);

        return memberRoleMapper.selectMemberRoleByEmail(map);
    }

    public int insertMemberRole(MemberRole memberRole) {
        return memberRoleMapper.insertMemberRole(memberRole);
    }
}
