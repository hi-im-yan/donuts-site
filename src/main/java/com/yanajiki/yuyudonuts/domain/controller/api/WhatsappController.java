package com.yanajiki.yuyudonuts.domain.controller.api;

import com.yanajiki.yuyudonuts.domain.controller.api.request.PackageRequest;
import com.yanajiki.yuyudonuts.domain.controller.api.response.CartValueResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/whatsapp")
@Slf4j
public class WhatsappController {

    @Value("${whatsapp.number}")
    private String whatsappNumber;
    @Autowired
    private BillingService billingService;

    @PostMapping
    public Map<String, String> redirectToWhatsApp(@RequestBody List<PackageRequest> packageRequests) throws Exception {

        log.info("Building whatsapp message for package: '{}'", packageRequests);
        CartValueResponse cartValueResponse = billingService.preBilling(packageRequests);
        StringBuilder whatsappMessage = new StringBuilder();

        whatsappMessage.append("Olá! Gostaria de fazer um pedido.%0A%0A");
        cartValueResponse.getCartPackageValues().forEach(packageValue -> {
            // Package Name and Quantity
            whatsappMessage.append("*Nome do pacote:* ").append(packageValue.getPackageName()).append(" (")
                    .append(packageValue.getQuantity()).append(")%0A");

            // Price of the Package
            whatsappMessage.append("*Preço por pacote:* R$ ").append(String.format("%.2f", packageValue.getPricePerPackage())).append("%0A");

            // Donuts details
            whatsappMessage.append("*Número de donuts:*%0A");
            packageValue.getDonuts().forEach(donut -> {
                whatsappMessage.append("    - ").append(donut.getTitle()).append(": ")
                        .append(donut.getQuantity()).append("%0A");
            });

            // Total price of the package
            whatsappMessage.append("*Total dos pacotes:* R$ ").append(String.format("%.2f", packageValue.getTotalPrice())).append("%0A").append("%0A");
        });

        whatsappMessage.append("%0A").append("*Total do Pedido:* R$ ").append(String.format("%.2f", cartValueResponse.getTotalCartValue())).append("%0A");


        // Final WhatsApp message
        String finalMessage = whatsappMessage.toString();
        log.info("Generated WhatsApp Message:\n" + finalMessage);

        String whatsappUrl = "https://api.whatsapp.com/send?phone=" + whatsappNumber + "&text=" + finalMessage;

        Map<String, String> response = Map.of("redirectUrl", whatsappUrl);
        // Redirect to the WhatsApp API
        return response;
    }
}