package com.spring.learn;

import com.spring.learn.impl.UserServiceImpl;
import com.spring.learn.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringLearnApplicationTests {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private UserServiceImpl userService;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    @Test
    //数据回滚注释
    @Transactional
    public void contextLoads() {
        User user = userService.findUser("1");
        Assert.assertEquals("是否是yupaopao", "yupaopao",user.getUsername());
    }

}
