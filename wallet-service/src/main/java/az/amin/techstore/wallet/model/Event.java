package az.amin.techstore.wallet.model;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {
    UUID getEventId();
}