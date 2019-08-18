package com.labs.sauti.controller;

import com.labs.sauti.model.ServerStat;
import com.labs.sauti.service.ServerStatService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServerStatController {

    private ServerStatService serverStatService;

    public ServerStatController(ServerStatService serverStatService) {
        this.serverStatService = serverStatService;
    }

    @GetMapping("server-stats")
    public ResponseEntity<List<ServerStat>> getAll() {
        return new ResponseEntity<>(serverStatService.getServerStats(), HttpStatus.OK);
    }

}
