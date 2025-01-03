package com.yanajiki.yuyudonuts.domain.repository;

import com.yanajiki.yuyudonuts.domain.model.DonutPackage;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonutPackageRepository {

    public DonutPackageRepository() {
    }

    public List<DonutPackage> getAllDonutPackages() {
        List<DonutPackage> packages = List.of(
                new DonutPackage(1L, "Sem recheio", "● Até 4 pacotes: R$ 6,00 por pacote. \n● De 5 a 8 pacotes: R$ 5,75 por pacote.\n● De 9 a 16 pacotes: R$ 5,50 por pacote.\n● A partir de 17 pacotes: R$ 5,25 por pacote.", "/images/4-donuts.jpg", 4),
                new DonutPackage(2L, "Com recheio", "● Até 4 pacotes: R$ 8,00 por pacote. \n● De 5 a 8 pacotes: R$ 7,75 por pacote.\n● De 9 a 16 pacotes: R$ 7,50 por pacote.\n● A partir de 17 pacotes: R$ 7,25 por pacote.", "/images/8-donuts.jpg", 8),
                new DonutPackage(3L, "Personalizado", "● Até 4 pacotes: R$ 10,00 por pacote. \n● De 5 a 8 pacotes: R$ 9,50 por pacote.\n● De 9 a 16 pacotes: R$ 9,00 por pacote.\n● A partir de 17 pacotes: R$ 8,50 por pacote.", "/images/16-donuts.jpg", 16),
                new DonutPackage(4L, "Especial de natal", "● Até 4 pacotes: R$ 9,00 por pacote. \n● De 5 a 8 pacotes: R$ 8,75 por pacote.\n● De 9 a 16 pacotes: R$ 8,50 por pacote.\n● A partir de 17 pacotes: R$ 8,25 por pacote.", "/images/20-donuts.jpg", 20)
        );

        return packages;
    }
}
