-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 01, 2024 at 12:43 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mfticketingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `password` varchar(256) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`customer_id`, `customer_name`, `email`, `password`) VALUES
(1000, 'Roshan Kokil', 'roshansrk@yahoo.com', 'c@t$1234'),
(1001, 'Anne Sarah Sadien', 'anne.sadien@gmail.com', 'p@ssword1'),
(1003, 'Ashfaaq Eathally', 'a.eathally@gmail.com', '#ello123'),
(1004, 'Zohra Magho', 'zohramagho@gmail.com', 'm@ng0888');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(255) NOT NULL,
  `event_date` date NOT NULL,
  `event_time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `event_date`, `event_time`) VALUES
(1, 'Sabrina Carpenter Performance', '2024-09-15', '12:00:00'),
(2, 'The Weeknd Mini Concert', '2024-09-15', '13:00:00'),
(3, 'BTS Special Stage', '2024-09-15', '14:30:00'),
(4, 'Imagine Dragons Live Set', '2024-09-15', '15:30:00'),
(5, 'Taylor Swift Acoustic Session', '2024-09-15', '16:45:00'),
(6, 'Reggae Relaxation', '2024-09-19', '15:00:00'),
(7, 'Folk Tales', '2024-09-19', '18:30:00');

-- --------------------------------------------------------

--
-- Table structure for table `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int(11) NOT NULL,
  `event_id` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `reservation_date` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservations`
--

INSERT INTO `reservations` (`reservation_id`, `event_id`, `ticket_id`, `customer_id`, `reservation_date`) VALUES
(2, 3, 12, 1001, '2024-09-02 11:36:56'),
(3, 4, 14, 1003, '2024-09-02 11:56:10'),
(4, 2, 5, 1001, '2024-09-02 12:15:07'),
(5, 2, 5, 1001, '2024-09-02 12:18:37'),
(6, 5, 18, 1001, '2024-09-02 15:04:49'),
(7, 1, 1, 1001, '2024-09-02 15:06:34'),
(8, 5, 17, 1000, '2024-09-28 16:59:42'),
(9, 1, 4, 1004, '2024-09-30 13:51:13');

-- --------------------------------------------------------

--
-- Table structure for table `tickets`
--

CREATE TABLE `tickets` (
  `ticket_id` int(11) NOT NULL,
  `event_id` int(11) DEFAULT NULL,
  `ticket_type` varchar(50) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `availability` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tickets`
--

INSERT INTO `tickets` (`ticket_id`, `event_id`, `ticket_type`, `price`, `availability`) VALUES
(1, 1, 'Platinum', 250, 50),
(2, 1, 'Gold', 200, 100),
(3, 1, 'Silver', 150, 150),
(4, 1, 'Standard', 100, 199),
(5, 2, 'Platinum', 300, 40),
(6, 2, 'Gold', 250, 80),
(7, 2, 'Silver', 180, 120),
(8, 2, 'Standard', 120, 180),
(9, 3, 'Platinum', 350, 60),
(10, 3, 'Gold', 280, 90),
(11, 3, 'Silver', 200, 140),
(12, 3, 'Standard', 150, 200),
(13, 4, 'Platinum', 270, 50),
(14, 4, 'Gold', 220, 90),
(15, 4, 'Silver', 160, 130),
(16, 4, 'Standard', 110, 180),
(17, 5, 'Platinum', 320, 44),
(18, 5, 'Gold', 270, 85),
(19, 5, 'Silver', 190, 140),
(20, 5, 'Standard', 140, 190),
(21, 6, 'Platinum', 200, 50),
(22, 6, 'Gold', 170, 90),
(23, 6, 'Silver', 130, 130),
(24, 6, 'Standard', 90, 180),
(25, 7, 'Platinum', 180, 60),
(26, 7, 'Gold', 150, 100),
(27, 7, 'Silver', 120, 140),
(28, 7, 'Standard', 80, 200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`);

--
-- Indexes for table `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `event_id` (`event_id`),
  ADD KEY `ticket_id` (`ticket_id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- Indexes for table `tickets`
--
ALTER TABLE `tickets`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `event_id` (`event_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1005;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservations`
--
ALTER TABLE `reservations`
  ADD CONSTRAINT `reservations_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`),
  ADD CONSTRAINT `reservations_ibfk_2` FOREIGN KEY (`ticket_id`) REFERENCES `tickets` (`ticket_id`),
  ADD CONSTRAINT `reservations_ibfk_3` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Constraints for table `tickets`
--
ALTER TABLE `tickets`
  ADD CONSTRAINT `tickets_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
