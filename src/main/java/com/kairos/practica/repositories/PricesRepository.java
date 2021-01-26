package com.kairos.practica.repositories;

import com.kairos.practica.models.Prices;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PricesRepository extends JpaRepository<Prices, Long> {

  List<Prices>  findByProductId(Integer productId);
}

