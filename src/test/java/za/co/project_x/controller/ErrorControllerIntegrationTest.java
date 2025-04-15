package za.co.project_x.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import za.co.project_x.repository.IdeaRepository;
import za.co.project_x.repository.UserRepository;
import za.co.project_x.service.CustomUserService;
import za.co.project_x.service.IdeaService;
import za.co.project_x.service.UserService;
import za.co.project_x.service.impl.CustomUserDetailsServiceImpl;
import za.co.project_x.service.impl.IdeaServiceImpl;
import za.co.project_x.service.impl.UserServiceImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest
@Import(ErrorController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ErrorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServiceImpl userService;
    @MockBean
    private IdeaServiceImpl ideaService;
    @MockBean
    private CustomUserDetailsServiceImpl customUserService;

//    @Test
    public void testNotFoundErrorHandling() throws Exception {
        mockMvc.perform(get("/non-existent-page").with(user("testUser").roles("ADMIN")))
                .andExpect(status().isNotFound())
                .andExpect(view().name("error"))
                .andExpect(model().attributeExists("error"))
                .andExpect(model().attribute("error", "The page you are looking for does not exist."));
    }
}
