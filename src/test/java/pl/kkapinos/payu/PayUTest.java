package pl.kkapinos.payu;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PayUTest {
    @Test
    void creatingNewOrder() {
        PayU payu = thereIsPayU();
        OrderCreateRequest request = thereIsExampleOrderCreateRequest();

        OrderCreateResponse response = payu.handle(request);

        assertNotNull(response.getRedirectUri());
        assertNotNull(response.getOrderId());
    }

    private OrderCreateRequest thereIsExampleOrderCreateRequest() {
        OrderCreateRequest request = new OrderCreateRequest();
        request
                .setNotifyUrl("https://your.eshop.com/notify")
                .setCustomerIp("127.0.0.1")
                .setMerchantPosId("300746")
                .setDescription("RTV market")
                .setCurrencyCode("PLN")
                .setTotalAmount(15000)
                .setBuyer(new Buyer()
                        .setEmail("jakub.guzik@example.com")
                        .setPhone("654111654")
                        .setFirstName("Jakub")
                        .setLastName("Guzik")
                        .setLanguage("pl")
                )
                .setProducts(Arrays.asList(
                        new Product()
                                .setName("Nice service")
                                .setUnitPrice(15000)
                                .setQuantity(1)
                ));

        return request;
    }

    private PayU thereIsPayU() {
        return new PayU(PayUApiCredentials.sandbox(), new RestTemplate());
    }
}

