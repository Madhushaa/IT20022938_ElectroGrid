-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 14, 2022 at 06:10 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogrid_bill`
--

-- --------------------------------------------------------

--
-- Table structure for table `bills`
--

CREATE TABLE `bills` (
  `bill_id` double NOT NULL,
  `acc_number` varchar(8) NOT NULL,
  `name` varchar(100) NOT NULL,
  `month` varchar(20) NOT NULL,
  `power_consumption` double NOT NULL,
  `total_amount` double NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bills`
--

INSERT INTO `bills` (`bill_id`, `acc_number`, `name`, `month`, `power_consumption`, `total_amount`, `date`) VALUES
(21, 'EG1010', 'Senerath Abepala', 'February', 120, 1228, '2022-03-02'),
(22, 'EG1015', 'Menaka Rajapaksha', 'March', 100, 828, '2022-04-01'),
(115, 'EG1016', 'Amanda Senanayake', 'April', 99, 808, '2022-05-14'),
(116, 'EG1045', 'Madhusha Karunarathne', 'April', 138, 1588, '2022-05-14'),
(117, 'EG1625', 'Kaushalya Gunasekara', 'April', 125, 1328, '2022-05-13');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bills`
--
ALTER TABLE `bills`
  ADD PRIMARY KEY (`bill_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bills`
--
ALTER TABLE `bills`
  MODIFY `bill_id` double NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=118;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
