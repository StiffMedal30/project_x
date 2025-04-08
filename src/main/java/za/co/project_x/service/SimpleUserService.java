package za.co.project_x.service;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.project_x.repository.UserRepository;

public class SimpleUserService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
