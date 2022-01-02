package ru.job4j.forum.control;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.MatcherAssert;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@WebAppConfiguration
class RegControlTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void regSaveTest() throws Exception {
        mockMvc.perform(post("/reg")
                .param("username", "Ben"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(postService).reg(userCaptor.capture());
        MatcherAssert.assertThat(userCaptor.getValue().getUsername(),
                is("Ben"));
    }
}