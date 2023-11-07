package pl.kkapinos;

import jakarta.servlet.http.HttpSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import pl.kkapinos.payu.PayU;
import pl.kkapinos.payu.PayUApiCredentials;
import pl.kkapinos.productcatalog.HashMapProductStorage;
import pl.kkapinos.productcatalog.ProductCatalog;
import pl.kkapinos.sales.Sales;
import pl.kkapinos.sales.cart.CartStorage;
import pl.kkapinos.sales.offering.OfferCalculator;
import pl.kkapinos.sales.payment.PaymentGateway;
import pl.kkapinos.sales.payment.PayuPaymentGateway;
import pl.kkapinos.sales.productdetails.ProductCatalogProductDetailsProvider;
import pl.kkapinos.sales.productdetails.ProductDetailsProvider;
import pl.kkapinos.sales.reservation.InMemoryReservationStorage;
import pl.kkapinos.web.SessionCurrentCustomerContext;


import java.math.BigDecimal;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    ProductCatalog createNewProductCatalog() {
        ProductCatalog productCatalog = new ProductCatalog(new HashMapProductStorage());

        String productId1 = productCatalog.addProduct("Sila stresu", "Kelly McGonigal");
        productCatalog.assignImage(productId1, "/image/book_1.jpg");
        productCatalog.changePrice(productId1, BigDecimal.TEN);
        productCatalog.publishProduct(productId1);

        return productCatalog;
    }

    @Bean
    PaymentGateway createPaymentGateway() {
        return new PayuPaymentGateway(new PayU(PayUApiCredentials.sandbox(), new RestTemplate()));
    }

    @Bean
    Sales createSales(ProductDetailsProvider productDetailsProvider, PaymentGateway paymentGateway) {
        return new Sales(
                new CartStorage(),
                productDetailsProvider,
                new OfferCalculator(productDetailsProvider),
                paymentGateway,
                new InMemoryReservationStorage()
        );
    }

    @Bean
    SessionCurrentCustomerContext currentCustomerContext(HttpSession httpSession) {
        return new SessionCurrentCustomerContext(httpSession);
    }

    @Bean
    ProductDetailsProvider createProductDetailsProvider(ProductCatalog catalog) {
        return new ProductCatalogProductDetailsProvider(catalog);
    }
}
