package com.yanajiki.yuyudonuts.domain.repository;

import com.yanajiki.yuyudonuts.domain.model.Donut;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DonutRepository {

    public DonutRepository() {
    }

    public Donut getDonutById(Long id) {
        log.info("Getting donut by id: " + id);
        Donut foundDonut = getAllDonuts().stream().filter(donut -> donut.getId().equals(id)).findFirst().orElse(null);
        if (foundDonut != null) {
            log.info("Donut found for id '{}': '{}'", id, foundDonut);
            return foundDonut;
        }
        log.info("Donut not found for id '{}'", id);
        return null;
    }

    public List<Donut> getAllDonuts() {
        return List.of(
                new Donut(1L, "Confete", "confete", "Chocolate com confete", "/images/chocolate-confete.jpg", false, 1L),
                new Donut(2L, "Chocoball", "chocoball", "Chocolate branco com chocoball", "/images/chocoball.jpg", false, 1L),
                new Donut(3L, "Granulé", "granule", "Chocolate com granulé", "/images/granule.jpg", false, 1L),
                new Donut(4L, "O Homer", "homer", "Inspirado em Os Simpsons", "/images/homer.jpg", false, 1L),
                new Donut(5L, "Confete Recheado", "confete", "Chocolate com confete", "/images/chocolate-confete.jpg", false, 2L),
                new Donut(6L, "Chocoball Recheado", "chocoball", "Chocolate branco com chocoball", "/images/chocoball.jpg", false, 2L),
                new Donut(7L, "Granulé Recheado", "granule", "Chocolate com granulé", "/images/granule.jpg", false, 2L),
                new Donut(8L, "O Homer Recheado", "homer", "Inspirado em Os Simpsons", "/images/homer.jpg", false, 2L)
        );
    }
}
