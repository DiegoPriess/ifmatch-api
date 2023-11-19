package com.ifmatch.ifmatchservice.services;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import com.ifmatch.ifmatchservice.models.User;
import com.ifmatch.ifmatchservice.kafka.producer.NewUserProducer;
import com.ifmatch.ifmatchservice.repositories.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserService(final UserRepository repository,
                       final NewUserProducer newUserProducer) {
        this.repository = repository;
        this.newUserProducer = newUserProducer;
    }

    final UserRepository repository;
    final NewUserProducer newUserProducer;

    public User create(@NotNull final User user) {
        final User userCreated = repository.save(user);
        newUserProducer.sendEvent();
        return userCreated;
    }

    public User update(@NotNull final User user) {
        Assert.notNull(user.getIdUser(), "Id deve ser informado");
        return repository.save(user);
    }

    public void chageStatus(final Long id, final UserStatus status) {
        Optional<User> user = getById(id);
        Assert.isTrue(user.isPresent(), "Usuário não encontrado");
        user.get().setStatus(status);
        repository.save(user.get());
    }

    public Optional<User> getById(@NotNull final Long id) {
        return repository.findById(id);
    }

    public User authenticate(@NotNull final String email, @NotNull final String password) {
        User userFound = repository.findByEmailAndPassword(email, password);
        Assert.notNull(userFound, "Usuário ou senha incorreta");
        return userFound;
    }

    public List<User> list() {
        return repository.findAll();
    }
}
