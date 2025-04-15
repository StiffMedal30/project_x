package za.co.project_x.service;

import za.co.project_x.entities.Idea;

import java.util.List;

public interface IdeaService {

    String inviteCollaborator(Long id, String email);
    Idea createIdea(String title, String description, String creatorEmail);
    List<Idea> getAllIdeas();
}
