package za.co.project_x.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import za.co.project_x.entities.base.BaseEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "ideas")
@Getter
@Setter
public class Idea extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;
    @Lob
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AppUser admin;

    @ManyToMany
    @JoinTable(
            name = "idea_collaborators",
            joinColumns = @JoinColumn(name = "idea_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private final Set<AppUser> collaborators = new HashSet<>();


    public Idea() {
    }

    public Idea(String title, String description, AppUser creator) {
        super();
    }


}

