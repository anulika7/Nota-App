/*
SQLyog Enterprise - MySQL GUI v7.15 
MySQL - 5.6.20 : Database - jbossfaktur
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`jbossfaktur` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `jbossfaktur`;

/*Table structure for table `client` */

DROP TABLE IF EXISTS `client`;

CREATE TABLE `client` (
  `idklien` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(80) NOT NULL,
  `telepon` varchar(12) NOT NULL,
  `celuler` varchar(12) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  PRIMARY KEY (`idklien`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `client` */

insert  into `client`(`idklien`,`nama`,`telepon`,`celuler`,`alamat`) values (1,'alex','022 123456','081289891212','bandung'),(2,'santi','021 432121','081333441122','jakarta'),(3,'joni','021 43211234','082234343422','jakarta'),(4,'janah','021 647474','01414558845','semarang'),(5,'jonasyah','023 7857689','02167457145','surabaya');

/*Table structure for table `detilfaktur` */

DROP TABLE IF EXISTS `detilfaktur`;

CREATE TABLE `detilfaktur` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idfaktur` int(11) NOT NULL,
  `idproduk` int(11) NOT NULL,
  `kodeproduk` varchar(40) NOT NULL,
  `namaproduk` varchar(80) NOT NULL,
  `jumlah` int(3) NOT NULL,
  `hargajual` decimal(10,2) NOT NULL,
  `totalharga` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_detilfaktur_produk` (`idproduk`),
  KEY `FK_detilfaktur_faktur` (`idfaktur`),
  CONSTRAINT `FK_detilfaktur_faktur` FOREIGN KEY (`idfaktur`) REFERENCES `faktur` (`idfaktur`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_detilfaktur_produk` FOREIGN KEY (`idproduk`) REFERENCES `produk` (`idproduk`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `detilfaktur` */

insert  into `detilfaktur`(`id`,`idfaktur`,`idproduk`,`kodeproduk`,`namaproduk`,`jumlah`,`hargajual`,`totalharga`) values (1,1,3,'1012','tepung gula',2,'7000.00','14000.00'),(2,2,4,'1001','beras ketan',3,'12000.00','36000.00'),(3,3,1,'1201','tepung beras',3,'12000.00','36000.00'),(4,3,4,'1001','beras ketan',4,'12000.00','48000.00'),(5,4,2,'1102','tepung ketan',10,'15000.00','150000.00'),(6,5,5,'1302','beras merah',21,'13000.00','273000.00'),(7,6,3,'1012','tepung gula',10,'7000.00','70000.00'),(8,7,4,'1001','beras ketan',8,'12000.00','96000.00'),(9,8,5,'1302','beras merah',2,'13000.00','26000.00'),(10,8,3,'1012','tepung gula',2,'7000.00','14000.00');

/*Table structure for table `faktur` */

DROP TABLE IF EXISTS `faktur`;

CREATE TABLE `faktur` (
  `idfaktur` int(11) NOT NULL AUTO_INCREMENT,
  `nofaktur` int(11) NOT NULL,
  `idklien` int(11) NOT NULL,
  `totalharga` decimal(10,2) NOT NULL,
  `register` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`idfaktur`),
  KEY `FK_faktur_klien` (`idklien`),
  CONSTRAINT `FK_faktur_klien` FOREIGN KEY (`idklien`) REFERENCES `client` (`idklien`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `faktur` */

insert  into `faktur`(`idfaktur`,`nofaktur`,`idklien`,`totalharga`,`register`) values (1,1,2,'14000.00','2016-05-12 17:29:41'),(2,2,3,'36000.00','2016-05-12 17:29:41'),(3,3,5,'84000.00','2016-05-12 17:29:41'),(4,4,2,'150000.00','2016-05-12 17:29:41'),(5,5,1,'273000.00','2016-05-12 17:29:41'),(6,6,3,'202000.00','2016-05-12 17:29:41'),(7,7,5,'96000.00','2016-05-12 17:29:41'),(8,8,5,'40000.00','2016-05-12 17:29:41');

/*Table structure for table `produk` */

DROP TABLE IF EXISTS `produk`;

CREATE TABLE `produk` (
  `idproduk` int(11) NOT NULL AUTO_INCREMENT,
  `namaproduk` varchar(80) NOT NULL,
  `hargajual` decimal(10,2) NOT NULL,
  `stokminimal` int(4) NOT NULL,
  `stokterkini` int(4) NOT NULL,
  `kodeproduk` varchar(40) NOT NULL,
  PRIMARY KEY (`idproduk`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `produk` */

insert  into `produk`(`idproduk`,`namaproduk`,`hargajual`,`stokminimal`,`stokterkini`,`kodeproduk`) values (1,'tepung beras','12000.00',25,120,'1201'),(2,'tepung ketan','15000.00',23,80,'1102'),(3,'tepung gula','7000.00',22,140,'1012'),(4,'beras ketan','12000.00',21,300,'1001'),(5,'beras merah','13000.00',26,125,'1302');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `iduser` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(20) NOT NULL,
  `pass` varchar(150) NOT NULL,
  `idvendor` int(11) NOT NULL,
  PRIMARY KEY (`iduser`),
  KEY `FK_users_idvendor` (`idvendor`),
  CONSTRAINT `FK_users_idvendor` FOREIGN KEY (`idvendor`) REFERENCES `vendor` (`idvendor`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`iduser`,`nama`,`pass`,`idvendor`) values (1,'donita','825e0ce0d553af5dc6c0051460911c748bd54a690ba6a01bf43d5c144623c335f502afa46484f0459587ba83cc0cadc7f05005bc271f0aadbd52633571ca3693',3),(2,'paramitha','2a22f6e683cb861d5c149ba7b107ab8380697d335f0d97c062b097c99c8867afaa6d042dbad34eed67652562bd1ba2d109935c2a4963f9faddceb7eabc82c5c5',2),(3,'bonita','f04df9f555178c6c6c64eaf1dc581a931595aaa9def810dc3171fe94ce5be306207504447e8ea7e4caeb1db9cfa7e1ed70992f200b0f678e3cd04c7f3f317620',1);

/*Table structure for table `vendor` */

DROP TABLE IF EXISTS `vendor`;

CREATE TABLE `vendor` (
  `idvendor` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(40) NOT NULL,
  `kota` varchar(40) NOT NULL,
  `celuler` varchar(12) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  PRIMARY KEY (`idvendor`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `vendor` */

insert  into `vendor`(`idvendor`,`nama`,`kota`,`celuler`,`alamat`) values (1,'bonita','jakarta','081221218282','jl rorotan 7'),(2,'paramitha','cirebon','081981020021','jl bende besar.4'),(3,'Donita','padang','081200220011','jl batu sangkar 3'),(4,'jantra','semarang','081606067654','jl sewu kutho 2');

/* Trigger structure for table `detilfaktur` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `stokaktualisasi` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `stokaktualisasi` BEFORE INSERT ON `detilfaktur` FOR EACH ROW BEGIN
SET @stokantiquo =(SELECT stokterkini FROM produk WHERE kodeproduk=new.kodeproduk); 
UPDATE produk
SET stokterkini=@stokantiquo-new.jumlah WHERE kodeproduk=new.kodeproduk;
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
