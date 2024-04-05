DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER
(
    CUSTOMER_ID BIGINT AUTO_INCREMENT,
    EMAIL        VARCHAR(255) NOT NULL,
    PASSWORD     VARCHAR(255) NOT NULL,
    NAME         VARCHAR(20) NOT NULL,
    PHONE_NUMBER VARCHAR(20) NOT NULL,
    CREATED_DATE DATETIME NOT NULL,
    UPDATED_DATE DATETIME NOT NULL,
    PRIMARY KEY (CUSTOMER_ID)
);