package dev.pedrogomes.authentication.services;

import dev.pedrogomes.authentication.entity.User;
import dev.pedrogomes.authentication.entity.UserSalty;
import dev.pedrogomes.authentication.repository.UserSaltyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaltyService {

    @Autowired
    UserSaltyRepository repository;

    public UserSalty saltyUser(User user, String salty) {
        UserSalty userSalty = new UserSalty();

        userSalty.setUser(user);
        userSalty.setSalty(salty);

        repository.save(userSalty);

        return userSalty;
    }

    public String getUserSalty(User user) {
        if (user == null)
            return "";
        return repository.findUserSaltyByUser(user).getSalty();
    }

    @Bean
    public String generateSalty() {
        return UUID.randomUUID().toString();
    }
}
