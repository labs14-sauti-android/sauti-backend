package com.labs.sauti.service;

import com.labs.sauti.model.ServerStat;
import com.labs.sauti.repository.ServerStatRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("serverStatServiceImpl")
public class ServerStatServiceImpl implements ServerStatService {

    private ServerStatRepository serverStatRepository;

    public ServerStatServiceImpl(ServerStatRepository serverStatRepository) {
        this.serverStatRepository = serverStatRepository;
    }

    @Override
    @Transactional
    public void addValueOrCreateServerStat(ServerStat serverStat) {
        ServerStat foundServerStat = serverStatRepository.findServerStatByName(serverStat.getName());
        if (foundServerStat == null) {
            serverStatRepository.save(serverStat);
        } else {
            foundServerStat.setValue(foundServerStat.getValue() + serverStat.getValue());
            serverStatRepository.save(foundServerStat);
        }
    }

    @Override
    @Nullable
    public ServerStat getServerStatByName(String name) {
        return serverStatRepository.findServerStatByName(name);
    }

    @Override
    public List<ServerStat> getServerStats() {
        ArrayList<ServerStat> serverStats = new ArrayList<>();
        serverStatRepository.findAll().iterator().forEachRemaining(serverStats::add);
        return serverStats;
    }
}
