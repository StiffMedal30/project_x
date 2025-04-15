package za.co.project_x.service;

import za.co.project_x.entities.Idea;

import java.util.List;
import java.util.Optional;

public interface IdeaService {

    void inviteCollaborator(Long id, String email);

    Idea createIdea(String title, String description, String user);

    List<Idea> getAllIdeas();

    Optional<Idea> findById(Long id);

    void updateIdea(Long id, String title, String description);

    void deleteIdea(Long id);
}
