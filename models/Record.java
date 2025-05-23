package models;

import java.time.LocalDateTime;

public abstract class Record {
    protected String id;
    protected LocalDateTime timestamp;

    public Record(String id, LocalDateTime timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
