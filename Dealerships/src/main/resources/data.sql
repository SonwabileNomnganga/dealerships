DROP TABLE IF EXISTS Country;
CREATE TABLE Country(
   countryCode VARCHAR(3) NOT NULL,
   name VARCHAR(100) NOT NULL,
   PRIMARY KEY (countryCode)
);
DROP TABLE IF EXISTS Province;
CREATE TABLE Province (
    provinceCode VARCHAR(3) NOT NULL,
    countryCode  VARCHAR(3)  NOT NULL,
    name         VARCHAR(255) NOT NULL,
    PRIMARY KEY (provinceCode, countryCode),
    CONSTRAINT country_province FOREIGN KEY (countryCode) REFERENCES country (countryCode)
);
DROP TABLE IF EXISTS Dealership;
CREATE TABLE Dealership(
   dealershipId INT AUTO_INCREMENT,
   dealershipName VARCHAR(255) NOT NULL,
   line1 VARCHAR(255) NOT NULL,
   line2 VARCHAR(255) NOT NULL,
   suburb VARCHAR(255) NOT NULL,
   city VARCHAR(255) NOT NULL,
   postalCode VARCHAR(7) NOT NULL,
   province VARCHAR(255) NOT NULL,
   country VARCHAR(255) NOT NULL,
   PRIMARY KEY (dealershipId)
);

-- countries
insert into country values ('ZA', 'South Africa');
insert into country values ('MZ', 'Mozambique');
insert into country values ('NA', 'Namibia');
--provinces
insert into province values ('GP', 'ZA', 'Gauteng');
insert into province values ('FS', 'ZA', 'Free State');
insert into province values ('EC', 'ZA', 'Eastern Cape');
insert into province values ('WC', 'ZA', 'Western Cape');
insert into province values ('L', 'ZA', 'Limpopo');
insert into province values ('MP', 'ZA', 'Mpumalanga');
insert into province values ('NC', 'ZA', 'Northern Cape');
insert into province values ('NW', 'ZA', 'North West');
insert into province values ('KZN', 'ZA', 'KwaZulu Natal');

insert into province values ('MA', 'MZ', 'Maputo');
insert into province values ('NA', 'MZ', 'Nampula');
insert into province values ('IN', 'MZ', 'Inhambane');
insert into province values ('GA', 'MZ', 'Gaza');
insert into province values ('ZA', 'MZ', 'Zambezia');
insert into province values ('NI', 'MZ', 'Niassa');

insert into province values ('CA', 'NA', 'Caprivi');
insert into province values ('ER', 'NA', 'Erongo');
insert into province values ('HA', 'NA', 'Hardap');
insert into province values ('KA', 'NA', 'Karas');
insert into province values ('KW', 'NA', 'Kavango West');