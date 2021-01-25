DROP TABLE IF EXISTS productos;

CREATE TABLE productos (
   id INT AUTO_INCREMENT  PRIMARY KEY,
   brand_id INTEGER(9),
   star_date VARCHAR(19) NOT NULL,
   end_date VARCHAR(19) NOT NULL,
   price_list INTEGER (2),
   product_id INTEGER(9),
   priority INTEGER(2),
   price DOUBLE NOT NULL,
   curr VARCHAR(6) NOT NULL
);
