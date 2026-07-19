package az.amin.techstore.payment.model.events;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {
    UUID getEventId();
}