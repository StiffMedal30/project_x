package za.co.project_x.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import za.co.project_x.entities.AppUser;
import za.co.project_x.entities.Idea;
import za.co.project_x.repository.IdeaRepository;
import za.co.project_x.repository.UserRepository;
import za.co.project_x.service.IdeaService;

import java.util.List;
import java.util.Optional;

@Service
public class IdeaServiceImpl implements IdeaService {

    private final IdeaRepository ideaRepository;
    private final UserRepository userRepository;

    @Autowired
    public IdeaServiceImpl(IdeaRepository ideaRepository, UserRepository userRepository) {
        this.ideaRepository = ideaRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String inviteCollaborator(Long id, String email) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea not found"));

        AppUser collaborator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        idea.getCollaborators().add(collaborator);
        ideaRepository.save(idea);
        return HttpStatus.OK.toString();
    }

    public Idea createIdea(String title, String description, String creatorEmail) {
        Optional<AppUser> creatorOpt = userRepository.findByEmail(creatorEmail);
        if (creatorOpt.isEmpty()) {
            throw new RuntimeException("User not found: " + creatorEmail);
        }
        AppUser creator = creatorOpt.get();

        Idea idea = new Idea(title, description, creator);
        return ideaRepository.save(idea);
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }
}
