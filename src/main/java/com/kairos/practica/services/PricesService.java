package com.kairos.practica.services;

import com.kairos.practica.models.Prices;
import com.kairos.practica.repositories.PricesRepository;
import java.util.ArrayList;
import java.util.List;
import javax.print.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricesService {

  private PricesRepository pricesRepository;

  public PricesService(PricesRepository pricesRepository) {
    this.pricesRepository = pricesRepository;
  }

  public List<Prices> getAllPrices()  {

    List<Prices> allPrices = pricesRepository.findAll();

    if(allPrices.size() > 0) {
      return allPrices;
    } else {
      return new ArrayList<Prices>();
    }
  }

}
