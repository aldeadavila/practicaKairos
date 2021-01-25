package com.kairos.practica.models;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Data
@Entity
@Table(name="productos")
public class Prices implements Serializable {

  @Id
  @Column(name="id")
  private Long id;

  @Column(name="brand_id")
  private Integer brandId;

  @Column(name="star_date")
  private String starDate;

  @Column(name="end_date")
  private String endDate;

  @Column(name="price_list")
  private Integer priceList;

  @Column(name="product_id")
  private Integer productId;

  @Column(name="priority")
  private Integer priority;

  @Column(name="price")
  private Double price;

  @Column(name="curr")
  private String currency;

}
