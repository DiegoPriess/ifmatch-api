package com.ifmatch.ifmatchservice.services;

import com.ifmatch.ifmatchservice.enums.UserStatus;
import com.ifmatch.ifmatchservice.kafka.producer.NewUserProducer;
import com.ifmatch.ifmatchservice.models.User;
import com.ifmatch.ifmatchservice.repositories.UserRepository;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
        Assert.isTrue(this.getByEmail(user.getEmail()).isEmpty(), "Já existe um usuário cadastrado com o email informado.");
        final User userCreated = repository.save(user);
        newUserProducer.sendEvent();
        return userCreated;
    }

    public User update(@NotNull final User user) {
        Assert.notNull(user.getIdUser(), "Id deve ser informado");
        Assert.isTrue(this.getById(user.getIdUser()).isPresent(), "Usuário não encontrado");
        return repository.save(user);
    }

    public void changeStatus(final Long id, final UserStatus status) {
        final Optional<User> user = getById(id);
        Assert.isTrue(user.isPresent(), "Usuário não encontrado");
        user.get().setStatus(status);
        repository.save(user.get());
    }

    public Optional<User> getById(@NotNull final Long id) {
        return repository.findById(id);
    }

    public Optional<User> getByEmail(final String email) {
        return repository.findByEmail(email);
    }

    public List<User> list() {
        return repository.findAll();
    }

    public Page<User> listPage(final Integer page, final Integer size) {
        return repository.findAll(PageRequest.of(page, size));
    }
}
