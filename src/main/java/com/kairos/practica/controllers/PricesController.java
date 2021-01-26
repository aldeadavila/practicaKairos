package com.kairos.practica.controllers;

import com.kairos.practica.models.Prices;
import com.kairos.practica.services.PricesService;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  @GetMapping("/prices/{productId}/{brandId}/{date}")
  public ResponseEntity<List<Prices>> getPricesById(@PathVariable String productId, @PathVariable String brandId, @PathVariable String date) {
    List<Prices> allPrices = null;
    try {
      allPrices = pricesService.getData(Integer.valueOf(brandId), Integer.valueOf(productId), date);
    } catch (RuntimeException e) {
      HttpHeaders headers = new HttpHeaders();
      headers.add("Error", "Bad request date parser: yyyy-MM-dd-HH.mm.ss");
      return new ResponseEntity<List<Prices>>(Collections.emptyList(), headers, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<List<Prices>>(allPrices, new HttpHeaders(), HttpStatus.OK);

  }
}
