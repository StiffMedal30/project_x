package za.co.project_x.service;

import org.springframework.stereotype.Service;
import za.co.project_x.entities.AppUser;
import za.co.project_x.entities.Idea;

@Service
public class SimplePermissionService implements PermissionService {

    public boolean canEditIdea(AppUser user, Idea idea) {
        return isOwner(user, idea) || isCollaborator(user, idea);
    }

    public boolean canDeleteIdea(AppUser user, Idea idea) {
        return isOwner(user, idea);
    }

    public boolean canInviteCollaborator(AppUser user, Idea idea) {
        return isOwner(user, idea);
    }

    private boolean isOwner(AppUser user, Idea idea) {
        return idea.getOwner().getId().equals(user.getId());
    }

    private boolean isCollaborator(AppUser user, Idea idea) {
        return idea.getCollaborators().stream()
                .anyMatch(collaborator -> collaborator.getId().equals(user.getId()));
    }
}
