package za.co.project_x.controller;

import org.slf4j.LoggerFactory;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(TemplateInputException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleTemplateError(TemplateInputException ex, Model model) {
        model.addAttribute("error", "Template not found: " + ex.getMessage());
        ex.printStackTrace(); // or better
        LoggerFactory.getLogger(getClass()).error("Unhandled exception", ex);
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("error", "The page you are looking for does not exist.");
        ex.printStackTrace(); // or better
        LoggerFactory.getLogger(getClass()).error("Unhandled exception", ex);
        return "error";
    }

    // Catch all access-denied (403 errors)
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handle403(AccessDeniedException ex, Model model) {
        model.addAttribute("error", "You don't have permission to access this page.");
        ex.printStackTrace(); // or better
        LoggerFactory.getLogger(getClass()).error("Unhandled exception", ex);
        return "error";
    }

    // Catch all other errors (like 500 or any unexpected exception)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handle500(Exception ex, Model model) {
        model.addAttribute("error", "An unexpected error occurred: " + ex.getMessage());
        ex.printStackTrace(); // or better
        LoggerFactory.getLogger(getClass()).error("Unhandled exception", ex);
        return "error";
    }
}
