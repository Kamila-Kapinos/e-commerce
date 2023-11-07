package pl.kkapinos.sales;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import pl.kkapinos.sales.cart.CartStorage;
import pl.kkapinos.sales.offering.OfferCalculator;
import pl.kkapinos.sales.payment.SpyPaymentGateway;
import pl.kkapinos.sales.productdetails.InMemoryProductDetailsProvider;
import pl.kkapinos.sales.productdetails.ProductDetails;
import pl.kkapinos.sales.reservation.InMemoryReservationStorage;
import pl.kkapinos.sales.reservation.OfferAcceptanceRequest;
import pl.kkapinos.sales.reservation.Reservation;
import pl.kkapinos.sales.reservation.ReservationDetails;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OfferAcceptanceHTTPTest {

    private CartStorage cartStorage;
    private InMemoryProductDetailsProvider productDetails;
    private SpyPaymentGateway paymentGateway;
    private InMemoryReservationStorage reservationStorage;

    @BeforeEach
    void setUp() {
        this.cartStorage = new CartStorage();
        this.productDetails = new InMemoryProductDetailsProvider();
        this.paymentGateway = new SpyPaymentGateway();
        this.reservationStorage = new InMemoryReservationStorage();
    }

    @Test
    void itAllowsToAcceptOffer() {
        Sales sales = thereIsSalesModule();
        String product = thereIsProduct("Lego set", BigDecimal.valueOf(10.10));
        String customerId = thereIsCustomer("Kamila");

        sales.addToCart(customerId, product);
        sales.addToCart(customerId, product);

        OfferAcceptanceRequest request = new OfferAcceptanceRequest();
        request
                .setFirstname("Kamila")
                .setLastname("Kapinos")
                .setEmail("kamila@example.com");

        ReservationDetails details = sales.acceptOffer(customerId, request);

        assertNotNull(details.getReservationId());
        assertNotNull(details.getPaymentUrl());
        assertPaymentGatewayWasInvoked();
        assertThereIsReservation(details.getReservationId());
        assertReservationIsPending(details.getReservationId());
        assertThereIsReservationForCustomer(details.getReservationId(), "kamila@example.com");
        assertThereIsReservationForCustomerForTotal(details.getReservationId(), BigDecimal.valueOf(20.20));

    }

    private void assertReservationIsPending(String reservationId) {
        Reservation reservation = reservationStorage.load(reservationId).get();

        assert reservation.isPending();
    }

    private void assertPaymentGatewayWasInvoked() {
        assertEquals(1, this.paymentGateway.getRequestsCount());
    }

    private void assertThereIsReservation(String reservationId) {
        Optional<Reservation> reservation = reservationStorage.load(reservationId);

        assert reservation.isPresent();
    }

    private void assertThereIsReservationForCustomerForTotal(String reservationId, BigDecimal total) {
        Reservation reservation = reservationStorage.load(reservationId).get();

        assert total.compareTo(reservation.getTotal()) == 0;
    }

    private void assertThereIsReservationForCustomer(String reservationId, String email) {
        Reservation reservation = reservationStorage.load(reservationId).get();

        assertEquals(email, reservation.getClientData().getEmail());
    }

    private String thereIsCustomer(String customerId) {
        return customerId;
    }

    private String thereIsProduct(String name, BigDecimal price) {
        String id = UUID.randomUUID().toString();
        productDetails.add(new ProductDetails(id, name, price));

        return id;
    }

    private Sales thereIsSalesModule() {
        return new Sales(
                cartStorage,
                productDetails,
                new OfferCalculator(productDetails),
                paymentGateway,
                reservationStorage
        );
    }
}
