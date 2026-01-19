package com.ankitmahala07.virtualthreadredis.model;

import java.util.List;

public class EventStateResponse {

    private List<ZSetEntry> scheduled;
    private List<ZSetEntry> processing;

    public EventStateResponse(
            List<ZSetEntry> scheduled,
            List<ZSetEntry> processing
    ) {
        this.scheduled = scheduled;
        this.processing = processing;
    }

    public List<ZSetEntry> getScheduled() {
        return scheduled;
    }

    public List<ZSetEntry> getProcessing() {
        return processing;
    }
}
