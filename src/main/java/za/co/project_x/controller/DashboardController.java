package za.co.project_x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.project_x.entities.Idea;
import za.co.project_x.service.IdeaService;

import java.util.Optional;

@Controller
public class DashboardController {

    private final IdeaService ideaService;

    @Autowired
    public DashboardController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("ideas", ideaService.getAllIdeas());
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String createIdeaForm(Model model) {
        model.addAttribute("idea", new Idea());
        return "idea_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/collaborator/{id}/invite")
    public String inviteCollaborator(@PathVariable Long id, @RequestParam String email) {
        ideaService.inviteCollaborator(id, email);
        return "redirect:/ideas";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit/{id}")
    public String editIdeaForm(@PathVariable Long id, Model model) {
        Optional<Idea> optionalIdea = ideaService.findById(id);
        Idea idea = optionalIdea.orElse(new Idea());
        model.addAttribute("idea", idea);
        return "edit_idea_form";
    }

    @PostMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }
}
