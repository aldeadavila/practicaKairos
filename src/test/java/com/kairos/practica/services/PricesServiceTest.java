package com.kairos.practica.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kairos.practica.models.Prices;
import com.kairos.practica.repositories.PricesRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PricesServiceTest {

  public PricesService pricesService;

  public PricesRepository pricesRepository;

  @BeforeEach
  public void setUp() {
    this.pricesRepository = mock(PricesRepository.class);
    pricesService = new PricesService(this.pricesRepository);
  }

  @Test
  @DisplayName("Retorna todos los productos")
  public void getAllPrices() {
    Prices prices1 =
        Prices.builder().id(1L).brandId(1).starDate("2020-06-14-00.00.00").endDate("2020-12-31-23.59.59").priceList(1).productId(35455)
            .priority(0).price(35.50).currency("EUR").build();
    Prices prices2 =
        Prices.builder().id(2L).brandId(1).starDate("2020-06-14-15.00.00").endDate("2020-06-14-18.30.00").priceList(2).productId(35455)
            .priority(0).price(20.45).currency("EUR").build();
    Prices prices3 =
        Prices.builder().id(3L).brandId(1).starDate("2020-06-15-00.00.00").endDate("2020-06-15-11.00.00'").priceList(3).productId(35455)
            .priority(0).price(35.50).currency("EUR").build();
    Prices prices4 =
        Prices.builder().id(4L).brandId(1).starDate("2020-06-15-16.00.00").endDate("2020-12-31-23.59.59").priceList(4).productId(35455)
            .priority(0).price(38.55).currency("EUR").build();

    List<Prices> precios = List.of(prices1, prices2, prices3, prices4);

    when(pricesRepository.findAll()).thenReturn(precios);

    final List<Prices> allPrices = pricesService.getAllPrices();

    assertThat(allPrices).isNotNull();
  }


}
