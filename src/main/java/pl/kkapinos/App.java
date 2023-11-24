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
        productCatalog.assignImage(productId1, "https://images.unsplash.com/photo-1630398777700-afb6d24a502d?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId1, BigDecimal.TEN);
        productCatalog.publishProduct(productId1);

        String productId2 = productCatalog.addProduct("Sila stresu", "Kelly McGonigal");
        productCatalog.assignImage(productId2, "https://images.unsplash.com/photo-1619167316217-c1c8f8ac1dff?q=80&w=2835&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId2, BigDecimal.TEN);
        productCatalog.publishProduct(productId2);

        String productId3 = productCatalog.addProduct("Sila stresu", "Kelly McGonigal");
        productCatalog.assignImage(productId3, "https://images.unsplash.com/photo-1612817288765-6d2b644c762e?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId3, BigDecimal.TEN);
        productCatalog.publishProduct(productId3);

        String productId4 = productCatalog.addProduct("Serum", "Kelly McGonigal");
        productCatalog.assignImage(productId4, "https://images.unsplash.com/photo-1612817288765-6d2b644c762e?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId4, BigDecimal.TEN);
        productCatalog.publishProduct(productId4);

        String productId5 = productCatalog.addProduct("Sila stresu", "Kelly McGonigal");
        productCatalog.assignImage(productId5, "https://images.unsplash.com/photo-1619167316217-c1c8f8ac1dff?q=80&w=2835&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId5, BigDecimal.TEN);
        productCatalog.publishProduct(productId5);

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
