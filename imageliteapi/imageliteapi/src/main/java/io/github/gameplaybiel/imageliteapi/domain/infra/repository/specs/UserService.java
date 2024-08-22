package io.github.gameplaybiel.imageliteapi.domain.infra.repository.specs;

import io.github.gameplaybiel.imageliteapi.domain.AcessToken;
import io.github.gameplaybiel.imageliteapi.domain.entity.User;

public interface UserService {
    User getByEmail(String email);
    User save(User user);
    AcessToken autheticate(String email, String password);
}
