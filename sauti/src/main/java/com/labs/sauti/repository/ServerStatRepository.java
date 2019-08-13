package com.labs.sauti.repository;

import com.labs.sauti.model.ServerStat;
import org.springframework.data.repository.CrudRepository;

public interface ServerStatRepository extends CrudRepository<ServerStat, Long> {

    ServerStat findServerStatByName(String name);
}
