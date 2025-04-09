package za.co.project_x.service;

import za.co.project_x.entities.AppUser;
import za.co.project_x.entities.Idea;

public interface PermissionService {

    boolean canEditIdea(AppUser user, Idea idea);

    boolean canDeleteIdea(AppUser user, Idea idea);

    boolean canInviteCollaborator(AppUser user, Idea idea);
}
