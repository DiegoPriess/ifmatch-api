package com.ifmatch.ifmatchservice.services;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import com.ifmatch.ifmatchservice.models.User;
import com.ifmatch.ifmatchservice.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    final UserRepository repository;

    public User create(@NotNull final User user) {
        return repository.save(User.builder()
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .status(UserStatus.FORA_CLINICA)
                            .profileImg(user.getProfileImg())
                            .build());
    }

    public User update(@NotNull final User user) {
        Assert.notNull(user.getIdUser(), "Id deve ser informado");
        return repository.save(User.builder()
                            .idUser(user.getIdUser())
                            .email(user.getEmail())
                            .password(user.getPassword())
                            .status(user.getStatus())
                            .profileImg(user.getProfileImg())
                            .build());
    }

    public void chageStatus(final Long id, final UserStatus status) {
        Assert.notNull(getById(id), "Usuário não encontrado");
        repository.save(User.builder()
                            .idUser(id)
                            .status(status)
                            .build());
    }

    public Optional<User> getById(@NotNull final Long id) {
        return repository.findById(id);
    }

    public User authenticate(@NotNull final String email, @NotNull final String password) {
        User userFound = repository.findByEmailAndPassword(email, password);
        Assert.notNull(userFound, "Usuário ou senha incorreta");
        return userFound;
    }

    public Page<User> list(@NotNull Pageable pageable, String name) {
        return name == null ? repository.findAll(pageable) : repository.findAllByNameContaining(name, pageable);
    }
}
