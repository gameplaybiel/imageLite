package io.github.gameplaybiel.imageliteapi.application.users;

import io.github.gameplaybiel.imageliteapi.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDTO dto){
        return User.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(dto.getPassword())
                .build();
    }
}
