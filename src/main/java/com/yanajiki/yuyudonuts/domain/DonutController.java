package com.yanajiki.yuyudonuts.domain;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DonutController {
    private Logger log = LoggerFactory.getLogger(DonutController.class);

    private final int DONUT_PER_PACKAGE = 4;

    @GetMapping("/")
    public String home(Model model) {
        List<DonutPackage> packages = List.of(
                new DonutPackage(1L, "Sem recheio", "● Até 4 pacotes: R$ 6,00 por pacote. \n● De 5 a 8 pacotes: R$ 5,75 por pacote.\n● De 9 a 16 pacotes: R$ 5,50 por pacote.\n● A partir de 17 pacotes: R$ 5,25 por pacote.", "/images/4-donuts.jpg", 4),
                new DonutPackage(2L, "Com recheio", "● Até 4 pacotes: R$ 8,00 por pacote. \n● De 5 a 8 pacotes: R$ 7,75 por pacote.\n● De 9 a 16 pacotes: R$ 7,50 por pacote.\n● A partir de 17 pacotes: R$ 7,25 por pacote.", "/images/8-donuts.jpg", 8),
                new DonutPackage(3L, "Personalizado", "● Até 4 pacotes: R$ 10,00 por pacote. \n● De 5 a 8 pacotes: R$ 9,50 por pacote.\n● De 9 a 16 pacotes: R$ 9,00 por pacote.\n● A partir de 17 pacotes: R$ 8,50 por pacote.", "/images/16-donuts.jpg", 16),
                new DonutPackage(4L, "Especial de natal", "● Até 4 pacotes: R$ 9,00 por pacote. \n● De 5 a 8 pacotes: R$ 8,75 por pacote.\n● De 9 a 16 pacotes: R$ 8,50 por pacote.\n● A partir de 17 pacotes: R$ 8,25 por pacote.", "/images/20-donuts.jpg", 20)
        );
        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");
        model.addAttribute("packages", packages);
        return "Home";
    }

    @GetMapping("/packageId/{packageId}")
    public String nonStuffed(Model model, @RequestParam String packageAmount, @PathVariable Long packageId) {
        List<Donut> donuts = List.of(
                new Donut(1L, "Confete", "confete", "Chocolate com confete", "/images/chocolate-confete.jpg", false),
                new Donut(2L, "Chocoball", "chocoball", "Chocolate branco com chocoball", "/images/chocoball.jpg", false),
                new Donut(3L, "Granulé", "granule", "Chocolate com granulé", "/images/granule.jpg", false),
                new Donut(4L, "O Homer", "homer","Inspirado em Os Simpsons", "/images/homer.jpg", false)

        );

        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");

        try {
            if (Strings.isBlank(packageAmount)) return "redirect:/";

            int parsedPackageAmount = Integer.parseInt(packageAmount);

            var pageVariables = new DonutSelectionPageVariables(1L, parsedPackageAmount, "Sem Recheio", parsedPackageAmount * DONUT_PER_PACKAGE, donuts);

            model.addAttribute("pageVariables", pageVariables);

            return parsedPackageAmount > 0 ? "donut-selection" : "redirect:/";
        } catch (Exception e) {
            log.error("Not a valid packageAmunt");
            return "redirect:/";
        }
    }
}
