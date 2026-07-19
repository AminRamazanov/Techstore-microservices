package az.amin.techstore.payment.enums;

public enum Status {
    CREATED,            // order yaradıldı
    PENDING,    // payment gözlənilir
    SUCCESS,  // payment uğurlu oldu
    FAILED,
    CANCELLED,// payment uğursuz oldu
    COMPLETED// bütün proses bitdi
}