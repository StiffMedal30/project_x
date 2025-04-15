package za.co.project_x.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ErrorControllerTest {

    private ErrorController errorController;
    private Model mockModel;

    @BeforeEach
    public void setup() {
        errorController = new ErrorController();
        mockModel = mock(Model.class);
    }

    @Test
    public void testHandleTemplateError() {
        TemplateInputException ex = new TemplateInputException("Missing template");
        String result = errorController.handleTemplateError(ex, mockModel);

        assertEquals("error", result);
        verify(mockModel).addAttribute(eq("error"), contains("Template not found: Missing template"));
    }

    @Test
    public void testHandle404() {
        NoHandlerFoundException ex = new NoHandlerFoundException("GET", "/missing", null);
        String result = errorController.handle404(ex, mockModel);

        assertEquals("error", result);
        verify(mockModel).addAttribute("error", "The page you are looking for does not exist.");
    }

    @Test
    public void testHandle403() {
        AccessDeniedException ex = new AccessDeniedException("Forbidden");
        String result = errorController.handle403(ex, mockModel);

        assertEquals("error", result);
        verify(mockModel).addAttribute("error", "You don't have permission to access this page.");
    }

    @Test
    public void testHandle500() {
        Exception ex = new RuntimeException("Something broke");
        String result = errorController.handle500(ex, mockModel);

        assertEquals("error", result);
        verify(mockModel).addAttribute(eq("error"), contains("An unexpected error occurred: Something broke"));
    }
}