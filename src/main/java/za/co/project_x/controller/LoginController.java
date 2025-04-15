package za.co.project_x.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.exceptions.TemplateInputException;
import za.co.project_x.entities.AppUser;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }

        if (logout != null) {
            model.addAttribute("logout", "You have been logged out successfully");
        }

        return "login"; // This will return the login.html page
    }

    // Redirect to the homepage after successful login
    @GetMapping("/home")
    public String home() {
        return "index";  // This will return the home.html page
    }

    // A simple handler for logout success
    @RequestMapping("/logoutSuccess")
    public String logoutSuccess() {
        return "login?logout=true";  // Redirect to login page with logout message
    }

    @GetMapping("/test-error")
    public String testError() {
        throw new TemplateInputException("This is a simulated error");
    }

    @GetMapping("/register-user")
    public String register(Model model) {
        model.addAttribute("user", new AppUser());
        return "register";
    }
}
