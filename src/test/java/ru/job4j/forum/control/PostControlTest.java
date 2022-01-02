package ru.job4j.forum.control;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.net.URI;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
@WebAppConfiguration
public class PostControlTest {

    @MockBean
    private PostService postService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    public void saveTest() throws Exception {
        this.mockMvc.perform(post("/save")
                .param("name", "Куплю ладу-грант. Дорого."))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> postCaptor = ArgumentCaptor.forClass(Post.class);
        verify(postService).save(postCaptor.capture());
        MatcherAssert.assertThat(postCaptor.getValue().getName(),
                is("Куплю ладу-грант. Дорого."));
    }

    @Test
    @WithMockUser
    public void addCommentTest2() throws Exception {
        Mockito.doReturn(Optional.of(new Post("Куплю. Дорого.")))
                .when(postService).findById(0);
        this.mockMvc.perform(post("/addComment")
                .param("id", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    @Test
    @WithMockUser
    public void editTest() throws Exception {
        Mockito.doReturn(true)
                .when(postService).isOwner(0);
        Mockito.doReturn(Optional.of(new Post("Куплю. Дорого.")))
                .when(postService).findById(0);
        mockMvc.perform(MockMvcRequestBuilders.get("/update")
                .param("id", "0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }
}