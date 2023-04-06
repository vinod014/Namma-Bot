package com.example.workflow.config;

import java.util.HashMap;
import java.util.Map;

public enum BackendEvent {

    DRIVER_ARRIVED("DRIVER_ARRIVED"),
    RIDE_STARTED("RIDE_STARTED"),
    RIDE_ENDED("RIDE_ENDED"),
    RIDE_CANCELED_BY_DRIVER("RIDE_ENDED_BY_DRIVER");

    private final String eventType;

    BackendEvent(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return eventType;
    }

    // Default methods for enum reverse lookup

    private static final Map<String, BackendEvent> intToEnumMap = new HashMap<>();

    static {
        for (BackendEvent enumVal : BackendEvent.values()) {
            intToEnumMap.put(enumVal.getEventType(), enumVal);
        }
    }

    public static BackendEvent fromEventType(String eventType) {
        return intToEnumMap.get(eventType);
    }
}
