package com.ifmatch.ifmatchservice.repositories;

import com.ifmatch.ifmatchservice.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAndPassword(@NotNull final String email, @NotNull final String password);
    Page<User> findAllByNameContaining(@NotNull String name, @NotNull Pageable pageable);
}
