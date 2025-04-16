package za.co.project_x.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.project_x.entities.AppUser;
import za.co.project_x.entities.Idea;
import za.co.project_x.repository.IdeaRepository;
import za.co.project_x.repository.UserRepository;
import za.co.project_x.service.IdeaService;
import za.co.project_x.util.OptionalUtils;

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
        Idea idea = OptionalUtils.unwrapOptionalOrThrow(ideaRepository.findById(id));
        AppUser collaborator = OptionalUtils.unwrapOptionalOrThrow(userRepository.findByEmail(email));

        idea.getCollaborators().add(collaborator);
        ideaRepository.save(idea);
    }

    public Idea createIdea(String title, String description, String user) {
        AppUser appUser = OptionalUtils.unwrapOptionalOrThrow(userRepository.findByUsername(user));

        Idea idea = new Idea();
        idea.setTitle(title);
        idea.setDescription(description);
        idea.setAdmin(appUser);
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
        Idea idea = OptionalUtils.unwrapOptionalOrThrow(ideaRepository.findById(id));
        idea.setTitle(title);
        idea.setDescription(description);
        ideaRepository.save(idea);
    }

    @Override
    public void deleteIdea(Long id) {
        ideaRepository.deleteById(id);
    }
}
