package com.ankitmahala07.virtualthreadredis.controller;

import com.ankitmahala07.virtualthreadredis.model.CreateEventRequest;
import com.ankitmahala07.virtualthreadredis.model.EventStateResponse;
import com.ankitmahala07.virtualthreadredis.service.EventClaimService;
import com.ankitmahala07.virtualthreadredis.service.EventCompletionService;
import com.ankitmahala07.virtualthreadredis.service.EventCreationService;
import com.ankitmahala07.virtualthreadredis.service.EventInspectionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventCreationService creator;
    private final EventClaimService claimer;
    private final EventCompletionService completer;
    private final EventInspectionService inspection;

    public EventController(
            EventCreationService creator,
            EventClaimService claimer,
            EventCompletionService completer,
            EventInspectionService inspection
    ) {
        this.creator = creator;
        this.claimer = claimer;
        this.completer = completer;
        this.inspection = inspection;
    }

    @PostMapping("/create")
    public void create(@Valid @RequestBody CreateEventRequest req) {
        System.out.println(Thread.currentThread() + " /create");
        creator.create(req.id, req.payload, req.executeAtMillis);
    }

    @PostMapping("/claim")
    public List<String> claim(
            @RequestParam int limit,
            @RequestParam long visibilityMs
    ) {
        System.out.println(Thread.currentThread() + " /claim");
        return claimer.claim(limit, visibilityMs);
    }

    @PostMapping("/complete/{id}")
    public void complete(@PathVariable String id) {
        System.out.println(Thread.currentThread() + " /complete/" + id);
        completer.complete(id);
    }

    @GetMapping("/state")
    public EventStateResponse state() {
        System.out.println(Thread.currentThread() + " /state");
        return new EventStateResponse(
                inspection.getScheduledEvents(),
                inspection.getProcessingEvents()
        );
    }
}
