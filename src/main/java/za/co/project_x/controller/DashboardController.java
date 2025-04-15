package za.co.project_x.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import za.co.project_x.entities.Idea;
import za.co.project_x.service.IdeaService;

@Controller
public class DashboardController {

    private final IdeaService ideaService;

    @Autowired
    public DashboardController(IdeaService ideaService) {
        this.ideaService = ideaService;
    }

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/ideas/create")
    public String createIdeaForm(Model model) {
        model.addAttribute("idea", new Idea());
        return "idea_form";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ideas/{id}/invite")
    public String inviteCollaborator(@PathVariable Long id, @RequestParam String email) {
        ideaService.inviteCollaborator(id, email);
        return "redirect:/ideas";
    }
}
