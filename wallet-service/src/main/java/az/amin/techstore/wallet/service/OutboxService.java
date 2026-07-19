package az.amin.techstore.wallet.service;

public interface OutboxService {

    void publishPendingEvents();

}
