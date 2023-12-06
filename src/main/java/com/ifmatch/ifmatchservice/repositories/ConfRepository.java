package com.ifmatch.ifmatchservice.repositories;

import com.ifmatch.ifmatchservice.models.Conf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfRepository extends JpaRepository<Conf, Long> {}
