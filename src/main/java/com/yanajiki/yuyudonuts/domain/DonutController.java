package com.yanajiki.yuyudonuts.domain;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DonutController {
    @GetMapping("/")
    public String home(Model model) {
        List<Donut> donuts = List.of(
                new Donut("Glazed Donut", "A classic favorite", "/images/donuts1.jpg"),
                new Donut("Chocolate Donut", "Rich chocolate glaze", "/images/donuts2.jpg"),
                new Donut("Sprinkle Donut", "Colorful sprinkles", "/images/donuts3.jpg"),
                new Donut("Donut 4", "Furei com a pica", "/images/donuts3.jpg")

        );

        List<DonutPackage> packages = List.of(
                new DonutPackage("4 unidades", "R$ 6,00 Sem Recheio \n R$ 9,00 Com Recheio", "/images/4-donuts.jpg", 4),
                new DonutPackage("8 unidades", "R$ 8,00 Sem Recheio \n R$13,00 Com Recheio", "/images/8-donuts.jpg", 8),
                new DonutPackage("16 unidades", "R$12,00 Sem Recheio \n R$15,00 Com Recheio", "/images/16-donuts.jpg", 16),
                new DonutPackage("20 unidades", "R$22,00 Sem Recheio \n R$25,00 Com Recheio", "/images/20-donuts.jpg", 20)
        );
        model.addAttribute("donuts", donuts);
        model.addAttribute("logoImage", "/images/logo.jpg");
        model.addAttribute("mascotImage", "/images/mascot.jpg");
        model.addAttribute("packages", packages);
        return "home";
    }
}
