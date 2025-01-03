package com.yanajiki.yuyudonuts.domain.controller.api;

import com.yanajiki.yuyudonuts.domain.model.Donut;
import com.yanajiki.yuyudonuts.domain.repository.DonutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/api/donuts")
public class DonutController {

    Logger log = Logger.getLogger(DonutController.class.getName());

    @Autowired
    private DonutRepository donutRepository;

    @GetMapping("/{id}")
    public Donut getDonutById(@PathVariable Long id) {
        var donut = donutRepository.getDonutById(id);
        log.info("Found donut with " + id + ": " + donut.toString());
        return donut;
    }
}
