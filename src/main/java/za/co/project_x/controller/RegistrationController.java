package za.co.project_x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.project_x.entities.AppUser;
import za.co.project_x.service.UserService;

@Controller
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") AppUser appUser, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register"; // Return to the registration page if there are errors
        }

        // Save the user (you can also add logic to check if the email already exists, etc.)
        userService.registerUser(appUser);

        return "redirect:/login"; // Or wherever you want to redirect
    }
}
