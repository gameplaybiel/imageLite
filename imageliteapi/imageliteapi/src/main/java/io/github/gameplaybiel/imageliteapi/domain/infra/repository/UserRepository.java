package io.github.gameplaybiel.imageliteapi.domain.infra.repository;

import io.github.gameplaybiel.imageliteapi.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmail(String email);
}
