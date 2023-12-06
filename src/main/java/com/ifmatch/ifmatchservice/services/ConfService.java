package com.ifmatch.ifmatchservice.services;

import com.ifmatch.ifmatchservice.config.SystemVariables;
import com.ifmatch.ifmatchservice.models.Conf;
import com.ifmatch.ifmatchservice.repositories.ConfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfService {

    @Autowired
    public ConfService(final ConfRepository repository) {
        this.repository = repository;
    }

    final ConfRepository repository;

    public void update(final Boolean flag) {
        repository.save(Conf.builder()
                            .idSistem(SystemVariables.SYSTEM_ID)
                            .needUpdateUsers(flag).build());
    }

    public Boolean needUpdateUsers() {
       final Optional<Conf> conf = repository.findById(SystemVariables.SYSTEM_ID);
       if(conf.isEmpty()) {
           return false;
       }
       return conf.get().getNeedUpdateUsers();
    }
}
