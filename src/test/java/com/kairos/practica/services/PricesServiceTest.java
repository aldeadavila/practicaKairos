package com.kairos.practica.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.kairos.practica.models.Prices;
import com.kairos.practica.repositories.PricesRepository;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class PricesServiceTest {

  private PricesService pricesService;

  private PricesRepository pricesRepository;

  private List<Prices> prices;

  private Integer productId;

  @BeforeEach
  public void setUp() {
    this.pricesRepository = mock(PricesRepository.class);
    pricesService = new PricesService(this.pricesRepository);
    this.productId = 35455;
    Prices prices1 =
        Prices.builder().id(1L).brandId(1).starDate("2020-06-14-00.00.00").endDate("2020-12-31-23.59.59").priceList(1).productId(productId)
            .priority(0).price(35.50).currency("EUR").build();
    Prices prices2 =
        Prices.builder().id(2L).brandId(1).starDate("2020-06-14-15.00.00").endDate("2020-06-14-18.30.00").priceList(2).productId(productId)
            .priority(0).price(20.45).currency("EUR").build();
    Prices prices3 =
        Prices.builder().id(3L).brandId(1).starDate("2020-06-15-00.00.00").endDate("2020-06-15-11.00.00'").priceList(3).productId(productId)
            .priority(0).price(35.50).currency("EUR").build();
    Prices prices4 =
        Prices.builder().id(4L).brandId(1).starDate("2020-06-15-16.00.00").endDate("2020-12-31-23.59.59").priceList(4).productId(productId)
            .priority(0).price(38.55).currency("EUR").build();

    this.prices = List.of(prices1, prices2, prices3, prices4);
  }

  @Test
  @DisplayName("Retorna todos los productos")
  public void getAllPrices() {
    when(pricesRepository.findAll()).thenReturn(this.prices);

    final List<Prices> allPrices = pricesService.getAllPrices();

    assertThat(allPrices).isNotNull();
  }

  @Test
  @DisplayName("Retorna todos los productos correctamente por fechas")
  public void getData() throws ParseException {
    when(pricesRepository.findByProductId(35455)).thenReturn(this.prices);

    final List<Prices> allPrices = pricesService.getData(1, this.productId, "2020-08-15-00.00.00");

    assertThat(allPrices).isNotNull();
    assertThat(allPrices).hasSize(1);
  }

  @Test
  @DisplayName("Return empty array if there is not any results")
  public void getEmptyData() throws ParseException {
    when(pricesRepository.findByProductId(this.productId)).thenReturn(this.prices);

    final List<Prices> allPrices = pricesService.getData(1, this.productId, "2020-05-15-00.00.00");

    assertThat(allPrices).isNotNull();
    assertThat(allPrices).hasSize(0);
  }


}
