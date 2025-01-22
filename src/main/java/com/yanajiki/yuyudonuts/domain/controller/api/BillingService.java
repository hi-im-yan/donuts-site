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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BillingService {
    @Autowired
    private DonutRepository donutRepository;

    // Pre-billing this endpoint will show a simulation of the billing
    public CartValueResponse preBilling(@RequestBody List<PackageRequest> packageRequests) throws Exception {
        log.info("Calculating billing value for package: '{}'", packageRequests);
        List<PackageValue> packageValues = new ArrayList<>();
        for (var packageRequest : packageRequests) {
            if (!packageRequest.isCorrectDonutCount()) {
                throw new Exception("Invalid donut count");
            }

            List<Donut> donuts = getDonutsFullInfo(packageRequest.getDonuts());
            PackageValue packageValue = applyForDiscount(packageRequest);
            // setting a list of chosen donuts with their quantity just trust this crazy line... to lazy to refactor

            List<DonutsInPackageResponse> donutsResponse = donuts.stream().map(donut -> {
                for (var donutInPackage : packageRequest.getDonuts()) {
                    if (donutInPackage.getDonutId().equals(donut.getId())) {
                        return DonutsInPackageResponse.fromDonutsAndQuantity(donut, donutInPackage.getQuantity());
                    }
                }
                return null;
            }).collect(Collectors.toList());

            packageValue.setDonuts(donutsResponse);
            packageValues.add(packageValue);
        }
        log.info("Calculated cart value: '{}'", packageValues);
        return new CartValueResponse(packageValues);
    }

    private PackageValue applyForDiscount(PackageRequest packageRequest) throws Exception {
        int packageId = packageRequest.getPackageId().intValue();
        return switch (packageId) {
            case 1 -> applyForNonStuffedDiscountStrategy(packageRequest); // Not stuffed

            case 2 -> applyForStuffedDiscountStrategy(packageRequest);// Stuffed

            case 3 -> throw new Exception("Invalid package id");
            default -> throw new Exception("Invalid package id");
        };
    }

    private List<Donut> getDonutsFullInfo(List<DonutsInPackageRequest> donuts) {
        List<Donut> fullDonuts = new ArrayList<>();
        for (var donut : donuts) {
            fullDonuts.add(donutRepository.getDonutById(donut.getDonutId()));
        }
        return fullDonuts;
    }

    private PackageValue applyForStuffedDiscountStrategy(PackageRequest packageRequest) {
        //          ● Até 4 pacotes: R$ 8,00 por pacote.
        //          ● De 5 a 8 pacotes: R$ 7,75 por pacote.
        //          ● De 9 a 16 pacotes: R$ 7,50 por pacote.
        //          ● A partir de 17 pacotes: R$ 7,25 por pacote.

        if (packageRequest.getPackageAmount() <= 4) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 8.0);
        } else if (packageRequest.getPackageAmount() <= 8) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 7.75);
        } else if (packageRequest.getPackageAmount() <= 16) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 7.5);
        } else {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 7.25);
        }
    }

    private PackageValue applyForNonStuffedDiscountStrategy(PackageRequest packageRequest) {
        //          ● Até 4 pacotes: R$ 6,00 por pacote.
        //          ● De 5 a 8 pacotes: R$ 5,75 por pacote.
        //          ● De 9 a 16 pacotes: R$ 5,50 por pacote.
        //          ● A partir de 17 pacotes: R$ 5,25 por pacote.

        if (packageRequest.getPackageAmount() <= 4) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 6.0);
        } else if (packageRequest.getPackageAmount() <= 8) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 5.75);
        } else if (packageRequest.getPackageAmount() <= 16) {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 5.5);
        } else {
            return new PackageValue(packageRequest.getPackageId(), packageRequest.getPackageName(), packageRequest.getPackageAmount(), 5.25);
        }
    }
}
