package com.yanajiki.yuyudonuts.domain;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PageController {
    private Logger log = LoggerFactory.getLogger(PageController.class);

    @Autowired
    private DonutRepository donutRepository;

    @Autowired
    private DonutPackageRepository donutPackageRepository;

    private final int DONUT_PER_PACKAGE = 4;

    @GetMapping("/")
    public String home(Model model) {
        List<DonutPackage> packages = donutPackageRepository.getAllDonutPackages();
        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");
        model.addAttribute("packages", packages);
        return "Home";
    }

    @GetMapping("/packageId/{packageId}")
    public String showDonutOptionsForPackage(Model model, @RequestParam String packageAmount, @PathVariable Long packageId) {
        DonutPackage donutPackage = getDonutPackageInfoById(packageId);

        if (donutPackage == null) {
            log.error("Donut package not found");
            return "redirect:/";
        }

        List<Donut> donutsOptions = getDonutsOptionsByPackageId(packageId);

        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");

        try {
            if (Strings.isBlank(packageAmount)) return "redirect:/";

            int parsedPackageAmount = Integer.parseInt(packageAmount);

            var pageVariables = new DonutSelectionPageVariables(packageId, parsedPackageAmount, donutPackage.getTitle(), parsedPackageAmount * DONUT_PER_PACKAGE, donutsOptions);

            model.addAttribute("pageVariables", pageVariables);

            return parsedPackageAmount > 0 ? "donut-selection" : "redirect:/";
        } catch (Exception e) {
            log.error("Not a valid packageAmunt");
            return "redirect:/";
        }
    }

    @GetMapping("/cart")
    public String showCart(Model model) {
        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");
        return "cart";
    }

    private List<Donut> getDonutsOptionsByPackageId(Long packageId) {
        List<Donut> donuts = donutRepository.getAllDonuts();

        return donuts.stream()
                .filter(donut -> donut.getPackageId().equals(packageId))
                .toList();
    }

    private DonutPackage getDonutPackageInfoById(Long packageId) {
        return donutPackageRepository.getAllDonutPackages().stream()
                .filter(donutPackage -> donutPackage.getId().equals(packageId))
                .findFirst().orElse(null);
    }

}
