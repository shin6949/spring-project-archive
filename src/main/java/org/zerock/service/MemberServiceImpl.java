package org.zerock.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.dao.MemberDao;
import org.zerock.dao.MemberRoleDao;
import org.zerock.domain.Member;
import org.zerock.domain.MemberRole;
import org.zerock.service.security.UserEntity;
import org.zerock.service.security.UserRoleEntity;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    public MemberServiceImpl(MemberDao memberDao, MemberRoleDao memberRoleDao) {
        this.memberDao = memberDao;
        this.memberRoleDao = memberRoleDao;
    }

    @Override
    @Transactional
    public UserEntity getUser(String loginUserId) {
        Member member = memberDao.selectOneMemberByEmail(loginUserId);
        System.out.println(member);
        return new UserEntity(member.getId(), member.getName(), member.getEmail(), member.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String loginUserId) {
        List<MemberRole> memberRoles = memberRoleDao.selectMemberRoleByEmail(loginUserId);
        List<UserRoleEntity> userRoleEntityList = new ArrayList<>();

        for(MemberRole memberRole : memberRoles) {
            userRoleEntityList.add(new UserRoleEntity(loginUserId, memberRole.getRoleName()));
        }

        return userRoleEntityList;
    }

    @Override
    @Transactional
    public Boolean addMember(Member member, boolean admin) {
        if(memberDao.insertMember(member) <= 0) {
            return false;
        }

        long insertedMemberId = member.getId();

        MemberRole memberRole = new MemberRole();
        memberRole.setMemberId(insertedMemberId);

        if(admin) {
            memberRole.setRoleName("ROLE_ADMIN");
        } else {
            memberRole.setRoleName("ROLE_USER");
        }

        return memberRoleDao.insertMemberRole(memberRole) > 0;
    }

    @Override
    public Boolean checkEmail(String email) {
        return memberDao.checkExistEmail(email);
    }
}
