-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 15, 2023 at 06:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uiuhostel`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointments`
--

CREATE TABLE `appointments` (
  `appointmentID` varchar(250) NOT NULL,
  `studentID` varchar(250) NOT NULL,
  `studentName` varchar(250) NOT NULL,
  `gName` varchar(250) NOT NULL,
  `gRelation` varchar(250) NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `billsconfig`
--

CREATE TABLE `billsconfig` (
  `billConfigID` int(11) NOT NULL,
  `breakfast` int(11) NOT NULL,
  `dinner` int(11) NOT NULL,
  `lunch` int(11) NOT NULL,
  `others` int(11) NOT NULL,
  `singleAC` int(11) NOT NULL,
  `singleNonAC` int(11) NOT NULL,
  `sharedAC` int(11) NOT NULL,
  `sharedNonAC` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `billsconfig`
--

INSERT INTO `billsconfig` (`billConfigID`, `breakfast`, `dinner`, `lunch`, `others`, `singleAC`, `singleNonAC`, `sharedAC`, `sharedNonAC`) VALUES
(1, 30, 50, 60, 1000, 6000, 3000, 3500, 2500);

-- --------------------------------------------------------

--
-- Table structure for table `halls`
--

CREATE TABLE `halls` (
  `id` varchar(250) NOT NULL,
  `hallName` varchar(250) NOT NULL,
  `maleOrFemale` varchar(250) NOT NULL,
  `singleAC` int(11) NOT NULL,
  `singleNonAC` int(11) NOT NULL,
  `sharedAC` int(11) NOT NULL,
  `sharedNonAC` int(11) NOT NULL,
  `address` varchar(250) NOT NULL,
  `number` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `halls`
--

INSERT INTO `halls` (`id`, `hallName`, `maleOrFemale`, `singleAC`, `singleNonAC`, `sharedAC`, `sharedNonAC`, `address`, `number`) VALUES
('Female_Hall_521c', 'XYZ', 'female', 50, 150, 80, 200, 'Fdsf', '535454'),
('Female_Hall_6f3e', 'uiu girls', 'female', 50, 50, 25, 25, 'Vatara, Notun Bazar', '01707796592'),
('Female_Hall_d77d', 'New Girls Hall', 'female', 40, 40, 20, 20, 'Notun Bazar', '01724847632'),
('Male_Hall_15d6', 'ABCD', 'male', 100, 123, 34, 43, 'Vatara', '01773473847'),
('Male_Hall_1ab2', 'uiu new hall', 'male', 50, 50, 25, 25, 'vatara', '017353653984'),
('Male_Hall_71b9', 'New Boys', 'male', 50, 50, 25, 25, 'vatara', '017355682399'),
('Male_Hall_8e0b', 'uiu boys 2', 'male', 50, 50, 25, 25, 'vatara', '01445453543'),
('Male_Hall_a031', 'Ehosan Ahmed Hall', 'male', 50, 50, 25, 25, 'Notun Bazar', '0173453284'),
('Male_Hall_c019', 'uiu boys', 'male', 50, 100, 50, 100, 'vatara', '01842324242');

-- --------------------------------------------------------

--
-- Table structure for table `meals`
--

CREATE TABLE `meals` (
  `mealID` varchar(250) NOT NULL,
  `studentID` varchar(250) NOT NULL,
  `gender` varchar(250) NOT NULL,
  `breakfast` int(11) NOT NULL,
  `lunch` int(11) NOT NULL,
  `dinner` int(11) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `meals`
--

INSERT INTO `meals` (`mealID`, `studentID`, `gender`, `breakfast`, `lunch`, `dinner`, `date`) VALUES
('Meal_2058', '011221503', 'Male', 0, 1, 0, '2023-12-15'),
('Meal_8695', '011221503', 'Male', 1, 1, 1, '2023-12-15'),
('Meal_d321', '011221503', 'Male', 1, 1, 0, '2023-12-15');

-- --------------------------------------------------------

--
-- Table structure for table `notices`
--

CREATE TABLE `notices` (
  `noticeID` varchar(250) NOT NULL,
  `date` date NOT NULL,
  `subject` varchar(250) NOT NULL,
  `message` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `notices`
--

INSERT INTO `notices` (`noticeID`, `date`, `subject`, `message`) VALUES
('Notice_4254', '2023-12-15', 'test', 'test message');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `roomID` varchar(250) NOT NULL,
  `roomType` varchar(250) NOT NULL,
  `acOrNon` varchar(250) NOT NULL,
  `hall_id` varchar(250) NOT NULL,
  `bookedBy` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`roomID`, `roomType`, `acOrNon`, `hall_id`, `bookedBy`) VALUES
('1', 'Single Room', 'AC', 'Female_Hall_521c', '011221235'),
('2', 'Shared Room', 'Non-AC', 'Male_Hall_15d6', '011221503'),
('3', 'Single Room', 'AC', 'Male_Hall_15d6', '011221233');

-- --------------------------------------------------------

--
-- Table structure for table `students`
--

CREATE TABLE `students` (
  `id` varchar(250) NOT NULL,
  `name` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `mobile` varchar(250) NOT NULL,
  `gender` varchar(250) NOT NULL,
  `roomType` varchar(250) NOT NULL,
  `acOrNon` varchar(250) NOT NULL,
  `address` varchar(250) NOT NULL,
  `gNumber` varchar(250) NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `students`
--

INSERT INTO `students` (`id`, `name`, `email`, `mobile`, `gender`, `roomType`, `acOrNon`, `address`, `gNumber`, `image`) VALUES
('011221233', 'sunny', 'abrarsunny38@gmail.com', '0111252155', 'Male', 'Single Room', 'AC', '', '', ''),
('0112212442', 'abrar sunny', 'abrarsunny38@gmail.com', '01981592238', 'Male', 'Single Room', 'AC', '', '', '');
INSERT INTO `students` (`id`, `name`, `email`, `mobile`, `gender`, `roomType`, `acOrNon`, `address`, `gNumber`, `image`) VALUES
('011221503', 'j sinthi', 'jsinthia@gmail.com', '0164422326', 'Male', 'Single Room', 'AC', 'ecb', '01786262595', '');
INSERT INTO `students` (`id`, `name`, `email`, `mobile`, `gender`, `roomType`, `acOrNon`, `address`, `gNumber`, `image`) VALUES
('124565623442342', 'Ifaz', 'ifazmasiulabdullah@gmail.com', '6969696969696', 'Male', 'Single Room', 'AC', '', '', ''),
('3969', 'Rakib', 'rakib@gmail.com', '025708360853', 'Male', 'Single Room', 'AC', '', '', ''),
('965545673', 'sunny', 'rakibin@gmail.com', '78643877698794', 'Male', 'Single Room', 'AC', '', '', ''),
('9875296', '256953', 'ahmed@gmail.com', '25w6y9s', 'Male', 'Single Room', 'Non-AC', 's8256969', 's85298', '');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `password` varchar(250) NOT NULL,
  `role` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `email`, `password`, `role`) VALUES
('0112212442', 'abrarsunny38@gmail.com', 'ace83b17', 'student'),
('011221503', 'jsinthia@gmail.com', '1234', 'student'),
('124565623442342', 'ifazmasiulabdullah@gmail.com', '12345', 'student'),
('965545673', 'student', 'student', 'student'),
('9875296', 'ahmed@gmail.com', '2de13332', 'student'),
('ADMIN', 'admin', 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`appointmentID`);

--
-- Indexes for table `billsconfig`
--
ALTER TABLE `billsconfig`
  ADD PRIMARY KEY (`billConfigID`);

--
-- Indexes for table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`mealID`);

--
-- Indexes for table `notices`
--
ALTER TABLE `notices`
  ADD PRIMARY KEY (`noticeID`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`roomID`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
