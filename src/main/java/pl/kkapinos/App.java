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

        String productId1 = productCatalog.addProduct("Oil", "Kelly McGonigal", "oil");
        productCatalog.assignImage(productId1, "https://images.unsplash.com/photo-1630398777700-afb6d24a502d?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId1, BigDecimal.TEN);
        productCatalog.publishProduct(productId1);

        String productId2 = productCatalog.addProduct("Oil", "Kelly McGonigal", "oil");
        productCatalog.assignImage(productId2, "https://images.unsplash.com/photo-1608571423539-e951b9b3871e?q=80&w=2560&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId2, BigDecimal.TEN);
        productCatalog.publishProduct(productId2);

        String productId3 = productCatalog.addProduct("Blue Soy Candle", "lalala", "candle");
        productCatalog.assignImage(productId3, "https://images.unsplash.com/photo-1605718699793-075babe700f3?q=80&w=2574&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId3, BigDecimal.TEN);
        productCatalog.publishProduct(productId3);

        String productId4 = productCatalog.addProduct("Soaps", "Kelly McGonigal", "soap");
        productCatalog.assignImage(productId4, "https://images.unsplash.com/photo-1600857544200-b2f666a9a2ec?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId4, BigDecimal.TEN);
        productCatalog.publishProduct(productId4);

        String productId5 = productCatalog.addProduct("Tea Soap", "Kelly McGonigal", "soap");
        productCatalog.assignImage(productId5, "https://images.unsplash.com/photo-1517999586990-2126f2b0c97d?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId5, BigDecimal.TEN);
        productCatalog.publishProduct(productId5);

        String productId6 = productCatalog.addProduct("Blue Soap", "Kelly McGonigal", "soap");
        productCatalog.assignImage(productId6, "https://images.unsplash.com/photo-1584305574647-0cc949a2bb9f?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId6, BigDecimal.TEN);
        productCatalog.publishProduct(productId6);

        String productId7 = productCatalog.addProduct("Oil2", "Kelly McGonigal", "oil");
        productCatalog.assignImage(productId7, "https://images.unsplash.com/photo-1619167316217-c1c8f8ac1dff?q=80&w=2835&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId7, BigDecimal.TEN);
        productCatalog.publishProduct(productId7);

        String productId8 = productCatalog.addProduct("Soy Candle Vanilla", "blabla", "candle");
        productCatalog.assignImage(productId8, "https://images.unsplash.com/photo-1602607202679-d4a5e8d2b524?q=80&w=2612&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId8, BigDecimal.TEN);
        productCatalog.publishProduct(productId8);

        String productId9 = productCatalog.addProduct("Serum", "Kelly McGonigal", "oil");
        productCatalog.assignImage(productId9, "https://images.unsplash.com/photo-1612817288765-6d2b644c762e?q=80&w=2940&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId9, BigDecimal.TEN);
        productCatalog.publishProduct(productId9);

        String productId10 = productCatalog.addProduct("Bergamot Soy Candle", "cos", "candle");
        productCatalog.assignImage(productId10, "https://images.unsplash.com/photo-1596370158003-0c4399e1fd95?q=80&w=2564&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId10, BigDecimal.TEN);
        productCatalog.publishProduct(productId10);

        String productId11 = productCatalog.addProduct("Three ships lip products", "Lip duo for better care", "set");
        productCatalog.assignImage(productId11, "https://images.unsplash.com/photo-1638404431939-3943201c8a86?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId11, BigDecimal.TEN);
        productCatalog.publishProduct(productId11);

        String productId12 = productCatalog.addProduct("Natural skincare products", "Cream, serum and eye cream", "set");
        productCatalog.assignImage(productId12, "https://images.unsplash.com/photo-1601049315503-07926a49f521?q=80&w=2073&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D");
        productCatalog.changePrice(productId12, BigDecimal.TEN);
        productCatalog.publishProduct(productId12);

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
