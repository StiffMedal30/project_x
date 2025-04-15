package za.co.project_x.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void inviteCollaborator(Long id, String email) {
        Idea idea = ideaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Idea not found"));

        AppUser collaborator = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        idea.getCollaborators().add(collaborator);
        ideaRepository.save(idea);
    }

    public Idea createIdea(String title, String description, String user) {
        Optional<AppUser> appUser = userRepository.findByUsername(user);
        if (appUser.isEmpty()) {
            throw new RuntimeException("User not found: " + user);
        }

        Idea idea = new Idea();
        idea.setTitle(title);
        idea.setDescription(description);
        idea.setAdmin(appUser.get());
        return ideaRepository.save(idea);
    }

    public List<Idea> getAllIdeas() {
        return ideaRepository.findAll();
    }

    @Override
    public Optional<Idea> findById(Long id) {
        return ideaRepository.findById(id);
    }

    @Override
    public void updateIdea(Long id, String title, String description) {
        Optional<Idea> optionalIdea = ideaRepository.findById(id);
        Idea idea = optionalIdea.orElse(new Idea());
        ideaRepository.save(idea);
    }

    @Override
    public void deleteIdea(Long id) {
        ideaRepository.deleteById(id);
    }
}
