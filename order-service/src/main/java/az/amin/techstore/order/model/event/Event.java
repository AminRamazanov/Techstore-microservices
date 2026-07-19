package az.amin.techstore.order.model.event;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {
    UUID getEventId();
}