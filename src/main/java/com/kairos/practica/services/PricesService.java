package com.kairos.practica.services;

import com.kairos.practica.models.Prices;
import com.kairos.practica.repositories.PricesRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;

@Service
public class PricesService {

  private PricesRepository pricesRepository;

  public PricesService(PricesRepository pricesRepository) {
    this.pricesRepository = pricesRepository;
  }

  public List<Prices> getAllPrices() {

    List<Prices> allPrices = pricesRepository.findAll();

    if (allPrices.size() > 0) {
      return allPrices;
    } else {
      return new ArrayList<Prices>();
    }
  }

  public List<Prices> getData(Integer brandId, Integer productId, String date) throws RuntimeException {
    final List<Prices> byProductId = pricesRepository.findByProductId(productId);
    final List<Prices> priceToReturn = new ArrayList<>();

    byProductId.stream().forEach(price -> {
          SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss", Locale.ENGLISH);
          try {
            Date firstDate = formatter.parse(price.getStarDate());
            Date secondDate = formatter.parse(price.getEndDate());
            Date targetDate = formatter.parse(date);


            if (convertToLocalDateViaInstant(targetDate).isBefore(convertToLocalDateViaInstant(secondDate)) && convertToLocalDateViaInstant(
                targetDate).isAfter(convertToLocalDateViaInstant(firstDate))) {
                priceToReturn.add(price);
            }
          } catch (ParseException e) {
            throw new RuntimeException(date);
          }
        }

    );

    if (priceToReturn.isEmpty()) {
      return priceToReturn;
    }

    return List.of(priceToReturn.stream().sorted(Comparator.comparingInt(Prices::getPriority).reversed()).findFirst().get());
  }

  public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }
}

