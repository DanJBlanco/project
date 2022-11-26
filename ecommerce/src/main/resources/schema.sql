DROP TABLE IF EXISTS PRICES;

CREATE TABLE PRICES(
  PRICE_LIST VARCHAR(250) NOT NULL,
  BRAND_ID VARCHAR(250) NOT NULL,
  PRODUCT_ID VARCHAR(250) NOT NULL,
  PRIORITY VARCHAR(2) NOT NULL,
  PRICE DOUBLE,
  CURR VARCHAR(250) NOT NULL,
  START_DATE TIMESTAMP,
  END_DATE TIMESTAMP

);

