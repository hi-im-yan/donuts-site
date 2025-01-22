package com.yanajiki.yuyudonuts.domain.controller.api;

import com.yanajiki.yuyudonuts.domain.controller.api.request.DonutsInPackageRequest;
import com.yanajiki.yuyudonuts.domain.controller.api.request.PackageRequest;
import com.yanajiki.yuyudonuts.domain.controller.api.response.CartValueResponse;
import com.yanajiki.yuyudonuts.domain.controller.api.response.DonutsInPackageResponse;
import com.yanajiki.yuyudonuts.domain.controller.api.response.PackageValue;
import com.yanajiki.yuyudonuts.domain.model.Donut;
import com.yanajiki.yuyudonuts.domain.repository.DonutRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/billing")
@Slf4j
public class BillingController {

    @Autowired
    private BillingService billingService;

    // Pre-billing this endpoint will show a simulation of the billing
    @PostMapping("/calculate-cart-value")
    public ResponseEntity<CartValueResponse> preBilling(@RequestBody List<PackageRequest> packageRequests) throws Exception {
        log.info("Received request to calculate cart value: '{}'", packageRequests);

        return ResponseEntity.ok(billingService.preBilling(packageRequests));
    }
}
