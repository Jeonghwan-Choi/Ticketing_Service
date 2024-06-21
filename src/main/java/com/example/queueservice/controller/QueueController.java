package com.example.queueservice.controller;

import com.example.queueservice.service.QueueService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/queue")
public class QueueController {

    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/enter")
    public Mono<String> enterQueue(@RequestParam String userId) {
        return queueService.enterQueue(userId);
    }

    @PostMapping("/exit")
    public Mono<String> exitQueue(@RequestParam String userId) {
        return queueService.exitQueue(userId);
    }

    @GetMapping("/position")
    public Mono<Long> getQueuePosition(@RequestParam String userId) {
        return queueService.getQueuePosition(userId);
    }

    @GetMapping("/size")
    public Mono<Long> getQueueSize() {
        return queueService.getQueueSize();
    }
}
