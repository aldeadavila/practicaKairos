package com.kairos.practica.controllers;

import com.kairos.practica.models.Prices;
import com.kairos.practica.services.PricesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PricesController {

  @Autowired
  final PricesService pricesService;

  public PricesController(PricesService pricesService) {
    this.pricesService = pricesService;
  }

  @GetMapping("/prices")
  public ResponseEntity<List<Prices>> getAllPrices() {
    List<Prices> allPrices = pricesService.getAllPrices();
    return new ResponseEntity<List<Prices>>(allPrices, new HttpHeaders(), HttpStatus.OK);

  }
}
