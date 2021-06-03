package org.zerock.service;

import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.Criteria;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class KeepBookServiceTests {
    @Autowired
    private KeepBookService keepBookService;

    @Test
    public void selectKeepBookTest() {
        Criteria criteria = new Criteria();
        assertNotNull(keepBookService.selectKeepBookWithPaging(criteria));
    }
}
