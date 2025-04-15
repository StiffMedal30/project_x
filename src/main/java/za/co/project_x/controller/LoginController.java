package za.co.project_x.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Principal: " + authentication.getPrincipal());  // User details
        System.out.println("Authorities: " + authentication.getAuthorities());

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
}
