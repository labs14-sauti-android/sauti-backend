package com.labs.sauti.service;

import com.labs.sauti.model.ServerStat;

import java.util.List;

public interface ServerStatService {

    void addValueOrCreateServerStat(ServerStat serverStat);
    ServerStat getServerStatByName(String name);
    List<ServerStat> getServerStats();

}
