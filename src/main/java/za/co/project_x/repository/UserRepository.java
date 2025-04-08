package za.co.project_x.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.co.project_x.entities.AppUser;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

//    Optional<Object> findByEmail(String username);

    Optional<AppUser> findByUsername(String username);
}
