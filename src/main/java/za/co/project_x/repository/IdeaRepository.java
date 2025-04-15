package za.co.project_x.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.project_x.entities.Idea;

@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long> {
}
