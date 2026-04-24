-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 24, 2026 at 09:20 AM
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
-- Database: `thi_gk2`
--

-- --------------------------------------------------------

--
-- Table structure for table `nguoi_dung`
--

CREATE TABLE `nguoi_dung` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `nguoi_dung`
--

INSERT INTO `nguoi_dung` (`username`, `password`) VALUES
('admin', '123456');

-- --------------------------------------------------------

--
-- Table structure for table `san_pham`
--

CREATE TABLE `san_pham` (
  `id` int(11) NOT NULL,
  `gia` double DEFAULT NULL,
  `tensp` varchar(255) DEFAULT NULL,
  `id_the_loai` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `san_pham`
--

INSERT INTO `san_pham` (`id`, `gia`, `tensp`, `id_the_loai`) VALUES
(1, 25000000, 'iPhone 15', 1),
(2, 22000000, 'Samsung S24', 1),
(3, 28000000, 'Macbook Air M3', 2);

-- --------------------------------------------------------

--
-- Table structure for table `the_loai`
--

CREATE TABLE `the_loai` (
  `id` int(11) NOT NULL,
  `ten_the_loai` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `the_loai`
--

INSERT INTO `the_loai` (`id`, `ten_the_loai`) VALUES
(1, 'Điện thoại'),
(2, 'Laptop');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `nguoi_dung`
--
ALTER TABLE `nguoi_dung`
  ADD PRIMARY KEY (`username`);

--
-- Indexes for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9ry40v1761bj2v7sdtuydeqck` (`id_the_loai`);

--
-- Indexes for table `the_loai`
--
ALTER TABLE `the_loai`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `san_pham`
--
ALTER TABLE `san_pham`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `the_loai`
--
ALTER TABLE `the_loai`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `san_pham`
--
ALTER TABLE `san_pham`
  ADD CONSTRAINT `FK9ry40v1761bj2v7sdtuydeqck` FOREIGN KEY (`id_the_loai`) REFERENCES `the_loai` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
