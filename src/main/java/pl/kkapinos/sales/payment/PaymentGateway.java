package pl.kkapinos.sales.payment;

public interface PaymentGateway {
    PaymentData register(RegisterPaymentRequest request);
}
