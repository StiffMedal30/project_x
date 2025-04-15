package za.co.project_x.service.impl;

import org.springframework.stereotype.Service;
import za.co.project_x.entities.AppUser;
import za.co.project_x.entities.Idea;
import za.co.project_x.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

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
        return idea.getAdmin().getId().equals(user.getId());
    }

    private boolean isCollaborator(AppUser user, Idea idea) {
        return idea.getCollaborators().stream()
                .anyMatch(collaborator -> collaborator.getId().equals(user.getId()));
    }
}
