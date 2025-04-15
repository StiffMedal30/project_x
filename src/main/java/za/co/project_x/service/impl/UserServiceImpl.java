package za.co.project_x.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import za.co.project_x.repository.UserRepository;
import za.co.project_x.service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
