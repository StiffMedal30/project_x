package za.co.project_x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import za.co.project_x.entities.Idea;
import za.co.project_x.service.IdeaService;

import java.util.Optional;

@Controller
public class IdeaController {
    private final IdeaService ideaService;

    @Autowired
    public IdeaController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    // Display the form to create a new idea
    @GetMapping("/idea/create")
    public String createIdeaForm(Model model) {
        model.addAttribute("idea", new Idea());  // Initialize a new Idea object
        return "idea_form";  // Return the view name (the template to render)
    }

    // Handle form submission to create a new idea
    @PostMapping("/add-idea")
    public String createIdea(@ModelAttribute Idea idea) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Call the service to create the idea
        ideaService.createIdea(idea.getTitle(), idea.getDescription(), authentication.getName());

        // After creation, redirect to the list of ideas (or another page as needed)
        return "redirect:/";  // Redirect to a page where all ideas are listed, for example
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String deleteIdea(@PathVariable Long id) {
        Optional<Idea> byId = ideaService.findById(id);
        ideaService.deleteIdea(id);
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/{id}")
    public String updateIdea(@PathVariable Long id, @ModelAttribute Idea ideaForm) {
        System.out.println(id);
        System.out.println(ideaForm.getTitle());
        System.out.println(ideaForm.getDescription());
        ideaService.updateIdea(id, ideaForm.getTitle(), ideaForm.getDescription());

        return "redirect:/";
    }
}
