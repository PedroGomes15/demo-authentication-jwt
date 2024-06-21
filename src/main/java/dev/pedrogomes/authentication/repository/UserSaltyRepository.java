package dev.pedrogomes.authentication.repository;

import dev.pedrogomes.authentication.entity.User;
import dev.pedrogomes.authentication.entity.UserSalty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSaltyRepository extends JpaRepository<UserSalty, UUID> {

    UserSalty findUserSaltyByUser(User user);
}
