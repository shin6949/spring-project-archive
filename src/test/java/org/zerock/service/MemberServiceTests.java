package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Member;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class MemberServiceTests {
    @Autowired
    private MemberService memberService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Test
//    public void checkNotValidEmailTest() {
//        assertTrue(memberService.checkEmail("shin6949@outlook.kr"));
//    }

    @Test
    public void checkValidEmailTest() {
        assertFalse(memberService.checkEmail(generateNotRegisteredId()));
    }

    @Test
    public void addValidMemberTest() {
        Member member = new Member();

        member.setEmail(generateNotRegisteredId());
        member.setName("테스트");
        member.setPassword("test1234");
        member.setPassword(passwordEncoder.encode(member.getPassword()));

        assertTrue(memberService.addMember(member, false));
    }

    // 없는 아이디를 생성하여, 테스트가 예외 없이 성공적으로 될 수 있도록 함.
    private String generateNotRegisteredId() {
        String generatedString = RandomStringUtils.randomAlphanumeric(10) + "@test.com";

        if(memberService.checkEmail(generatedString)) {
            generatedString = generateNotRegisteredId();
        }

        return generatedString;
    }
}
