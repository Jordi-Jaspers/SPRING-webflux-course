package com.jordijaspers.webfluxapiannotations.model;

import java.util.Objects;

/**
 * To retrieve data from the database, we usually make a request to the server.
 * With server events, we can listen to the server and retrieve data from the serve and push new data to the client at any time.
 */
public class ProductEvent {

    /**
     * The type of event.
     */
    private Long eventId;

    /**
     * The type of event.
     */
    private String eventType;

    /**
     * the default constructor.
     *
     * @param eventId   the event id.
     * @param eventType the event type.
     */
    public ProductEvent(final Long eventId, final String eventType) {
        this.eventId = eventId;
        this.eventType = eventType;
    }

    /**
     * Empty constructor.
     * @param eventType the event type.
     */
    public ProductEvent(final String eventType) {
    }

    /**
     * Getter for eventId.
     *
     * @return eventId.
     */
    public Long getEventId() {
        return eventId;
    }

    /**
     * Getter for eventType.
     *
     * @return eventType.
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final ProductEvent that = (ProductEvent) o;
        return Objects.equals(eventId, that.eventId) && Objects.equals(eventType, that.eventType);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventType);
    }

    /**
     * {@inheritDoc}.
     */
    @Override
    public String toString() {
        return "ProductEvent{" +
                "eventId=" + eventId +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
