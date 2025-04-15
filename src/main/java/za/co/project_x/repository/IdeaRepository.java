package za.co.project_x.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.project_x.entities.Idea;

public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
