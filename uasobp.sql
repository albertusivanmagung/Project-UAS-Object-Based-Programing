-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2024 at 08:47 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uasobp`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id_admin` bigint(20) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `id_customer` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`, `id_customer`) VALUES
(1, 'richard', 'ricat', 0),
(2, 'thirza', 'tilja', 0),
(3, 'albert', 'albet', 0),
(4, 'cynthia', 'cyn', 0);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_customer` bigint(20) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `no_telp` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_customer`, `username`, `password`, `email`, `no_telp`) VALUES
(9, 'Thirza', 'tilja', 'thirza@gmail.com', '0987654321'),
(12, 'Albert', 'albet', 'albert@gmail.com', '081291615370');

-- --------------------------------------------------------

--
-- Table structure for table `kurs_mata_uang`
--

CREATE TABLE `kurs_mata_uang` (
  `mata_uang` varchar(255) NOT NULL,
  `beli` decimal(38,2) NOT NULL,
  `jual` decimal(38,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `kurs_mata_uang`
--

INSERT INTO `kurs_mata_uang` (`mata_uang`, `beli`, `jual`) VALUES
('AUD', 10347.82, 10443.40),
('CNY', 2168.47, 2196.26),
('EUR', 17122.85, 17253.19),
('GBP', 20224.77, 20414.13),
('HKD', 2020.80, 2034.25),
('JPY', 102.57, 103.57),
('KRW', 11.22, 11.30),
('SGD', 12009.23, 12009.23),
('THB', 455.05, 463.48),
('USD', 15719.00, 15799.00);

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `id_transaksi` bigint(20) NOT NULL,
  `id_customer` bigint(20) NOT NULL,
  `tipe_transaksi` varchar(255) DEFAULT NULL,
  `mata_uang_asal` varchar(255) DEFAULT NULL,
  `kurs_tujuan` decimal(38,2) DEFAULT NULL,
  `nominal` decimal(38,2) DEFAULT NULL,
  `nominal_setelah_konversi` decimal(38,2) DEFAULT NULL,
  `jenis_transaksi` varchar(255) DEFAULT NULL,
  `nomor_rekening` varchar(255) DEFAULT NULL,
  `mata_uang_tujuan` varchar(255) DEFAULT NULL,
  `nominal_konversi` decimal(38,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`id_transaksi`, `id_customer`, `tipe_transaksi`, `mata_uang_asal`, `kurs_tujuan`, `nominal`, `nominal_setelah_konversi`, `jenis_transaksi`, `nomor_rekening`, `mata_uang_tujuan`, `nominal_konversi`) VALUES
(48, 9, 'beli', 'USD', 10443.40, 100.00, NULL, 'ewallet', '1790211258', 'AUD', 151.28),
(49, 9, 'beli', 'USD', 10443.40, 100.00, NULL, 'cash', '', 'AUD', 151.28),
(50, 9, 'jual', 'USD', 10347.82, 100.00, NULL, 'cash', '', 'AUD', 150.52),
(51, 9, 'jual', 'USD', 10347.82, 100.00, NULL, 'ewallet', '1790211258', 'AUD', 150.52),
(57, 12, 'beli', 'USD', 10443.40, 100.00, NULL, 'cash', '', 'AUD', 151.28),
(58, 12, 'beli', 'USD', 10443.40, 100.00, NULL, 'ewallet', '1790211258', 'AUD', 151.28),
(59, 12, 'jual', 'USD', 10347.82, 100.00, NULL, 'cash', '', 'AUD', 150.52),
(60, 12, 'jual', 'USD', 10347.82, 100.00, NULL, 'ewallet', '1790211258', 'AUD', 150.52);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id_admin`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `kurs_mata_uang`
--
ALTER TABLE `kurs_mata_uang`
  ADD PRIMARY KEY (`mata_uang`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`id_transaksi`),
  ADD KEY `FK2aqd3mmk3k33ja6bn8ttxsn4h` (`id_customer`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id_admin` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_customer` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `transaksi`
--
ALTER TABLE `transaksi`
  MODIFY `id_transaksi` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `FK2aqd3mmk3k33ja6bn8ttxsn4h` FOREIGN KEY (`id_customer`) REFERENCES `customer` (`id_customer`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
