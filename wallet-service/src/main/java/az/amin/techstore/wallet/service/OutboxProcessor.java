package az.amin.techstore.wallet.service;

import az.amin.techstore.wallet.dao.OutboxEvent;

public interface OutboxProcessor {

    void processSingleEvent(OutboxEvent event);

}