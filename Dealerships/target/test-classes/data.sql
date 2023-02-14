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
    PRIMARY KEY (provinceCode, countryCode)
);
DROP TABLE IF EXISTS Dealership;
CREATE TABLE Dealership(
   dealershipId INT AUTO_INCREMENT,
   dealershipName VARCHAR(255) NOT NULL,
   line1 VARCHAR(255) NOT NULL,
   line2 VARCHAR(255) NOT NULL,
   suburb VARCHAR(255) NOT NULL,
   postalCode VARCHAR(7) NOT NULL,
   province VARCHAR(255) NOT NULL,
   country VARCHAR(255) NOT NULL,
   PRIMARY KEY (dealershipId)
);