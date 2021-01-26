package com.kairos.practica.controllers;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kairos.practica.models.Prices;
import com.kairos.practica.services.PricesService;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PricesController.class)
@ActiveProfiles("test")
public class PricesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private PricesService pricesService;

  private List<Prices> pricesList;

  @BeforeEach
  void setUp() {
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
    this.pricesList = List.of(prices1, prices2, prices3, prices4);
  }

  @Test
  @DisplayName("Sholud return all prices")
  void getAllPrices() throws Exception {
    when(pricesService.getAllPrices()).thenReturn(this.pricesList);

    this.mockMvc.perform(get("/api/prices")).andExpect(status().isOk()).andExpect(jsonPath("$.size()", is(pricesList.size())));
  }

  @Test
  @DisplayName("Show date by date, productId and brandId")
  void getData() throws Exception {
    final Integer productId = 35455;
    final Integer brandId = 1;
    final String date = "2020-08-15-16.00.00";
    when(pricesService.getData(brandId, productId, date)).thenReturn(List.of(this.pricesList.get(3)));

    this.mockMvc.perform(get("/api/prices/{productId}/{brandId}/{date}", productId, brandId, date)).andExpect(status().isOk()).andExpect(jsonPath("$"
            + ".size()",
        is(1)));
  }

  @Test
  @DisplayName("Send empty data")
  void getEmptyData() throws Exception {
    final Integer productId = 35455;
    final Integer brandId = 1;
    final String date = "2020-08-15-16.00.00";
    when(pricesService.getData(brandId, productId, date)).thenReturn(Collections.EMPTY_LIST);

    this.mockMvc.perform(get("/api/prices/{productId}/{brandId}/{date}", productId, brandId, date)).andExpect(status().isOk()).andExpect(jsonPath("$"
            + ".size()",
        is(0)));
  }

  @Test
  @DisplayName("Send bad date url return 404")
  void sendBadURL() throws Exception {
    final Integer productId = 35455;
    final Integer brandId = 1;
    final String date = "2020-08-15-16.00.j0";

    when(pricesService.getData(brandId, productId, date)).thenThrow(new RuntimeException(date));

    this.mockMvc.perform(get("/api/prices/{productId}/{brandId}/{date}", productId, brandId, date)).andExpect(status().isBadRequest()).andExpect(jsonPath("$"
            + ".size()",
        is(0)));
  }

}
