-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2019 at 05:22 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `asrama`
--

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas`
--

CREATE TABLE `fasilitas` (
  `fasilitas_id` int(8) NOT NULL,
  `fasilitas_nama` varchar(25) NOT NULL,
  `fasilitas_harga` int(8) NOT NULL,
  `fasilitas_kuantitas` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fasilitas`
--

INSERT INTO `fasilitas` (`fasilitas_id`, `fasilitas_nama`, `fasilitas_harga`, `fasilitas_kuantitas`) VALUES
(20001, 'LED 21 Inch Samsung', 1200000, 1),
(20002, 'SpringBed Airland King Sz', 1300000, 1),
(20004, 'Lemari 2 Pintu', 700000, 8),
(20005, 'SpringBed Caisar 2 in 1', 1500000, 8);

-- --------------------------------------------------------

--
-- Table structure for table `fasilitas_ruangan`
--

CREATE TABLE `fasilitas_ruangan` (
  `kamar_no` int(8) NOT NULL,
  `fasilitas_id` int(8) NOT NULL,
  `fr_kuantitas` int(3) NOT NULL,
  `fr_kondisi` enum('rusak','bagus') NOT NULL,
  `fasilitas_ruangan_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `fasilitas_ruangan`
--

INSERT INTO `fasilitas_ruangan` (`kamar_no`, `fasilitas_id`, `fr_kuantitas`, `fr_kondisi`, `fasilitas_ruangan_id`) VALUES
(101, 20005, 2, 'bagus', 1230001),
(101, 20004, 2, 'bagus', 1230002),
(104, 20004, 2, 'bagus', 1230003),
(104, 20005, 2, 'bagus', 1230004),
(106, 20004, 2, 'bagus', 1230005),
(106, 20005, 2, 'bagus', 1230006),
(102, 20004, 2, 'bagus', 1230007),
(102, 20005, 2, 'bagus', 1230008);

-- --------------------------------------------------------

--
-- Stand-in structure for view `getjumahkamar`
-- (See below for the actual view)
--
CREATE TABLE `getjumahkamar` (
`ruangan_no` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `getjumlahkamar`
-- (See below for the actual view)
--
CREATE TABLE `getjumlahkamar` (
`ruangan_no` bigint(21)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `gettipekamar`
-- (See below for the actual view)
--
CREATE TABLE `gettipekamar` (
`type_nama` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `lihatruangankosong`
-- (See below for the actual view)
--
CREATE TABLE `lihatruangankosong` (
`ruangan_no` int(8)
,`type_nama` varchar(20)
,`type_kapasitas` int(1)
,`type_sewa` int(10)
);

-- --------------------------------------------------------

--
-- Table structure for table `penghuni`
--

CREATE TABLE `penghuni` (
  `penghuni_id` int(8) NOT NULL,
  `penghuni_nama_depan` varchar(25) NOT NULL,
  `penghuni_nama_belakang` varchar(25) NOT NULL,
  `penghuni_tgl_lahir` date NOT NULL,
  `penghuni_nomor_kamar` int(8) NOT NULL,
  `penghuni_NIK` bigint(20) UNSIGNED NOT NULL,
  `penghuni_status` enum('tinggal','keluar') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `ruangan_no` int(8) NOT NULL,
  `type_id` int(10) NOT NULL,
  `ruangan_status` enum('kosong','ditempati') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`ruangan_no`, `type_id`, `ruangan_status`) VALUES
(101, 1101, 'ditempati'),
(102, 1101, 'kosong'),
(104, 1101, 'ditempati'),
(105, 1102, 'kosong'),
(106, 1102, 'ditempati'),
(107, 1104, 'kosong'),
(108, 1101, 'kosong');

-- --------------------------------------------------------

--
-- Table structure for table `type_ruangan`
--

CREATE TABLE `type_ruangan` (
  `type_id` int(10) NOT NULL,
  `type_nama` varchar(20) NOT NULL,
  `type_kapasitas` int(1) NOT NULL,
  `type_sewa` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `type_ruangan`
--

INSERT INTO `type_ruangan` (`type_id`, `type_nama`, `type_kapasitas`, `type_sewa`) VALUES
(1101, 'reguler', 4, 3600000),
(1102, 'spesial', 2, 2000000),
(1104, 'vip', 1, 1200000);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(8) NOT NULL,
  `user_username` varchar(25) NOT NULL,
  `user_password` longtext NOT NULL,
  `user_level` enum('admin','penghuni') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_username`, `user_password`, `user_level`) VALUES
(20101, 'root', 'root', 'admin'),
(20102, 'jakcie', '1234', 'penghuni');

-- --------------------------------------------------------

--
-- Structure for view `getjumahkamar`
--
DROP TABLE IF EXISTS `getjumahkamar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `getjumahkamar`  AS  select count(`ruangan`.`ruangan_no`) AS `ruangan_no` from `ruangan` ;

-- --------------------------------------------------------

--
-- Structure for view `getjumlahkamar`
--
DROP TABLE IF EXISTS `getjumlahkamar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `getjumlahkamar`  AS  select count(`ruangan`.`ruangan_no`) AS `ruangan_no` from `ruangan` ;

-- --------------------------------------------------------

--
-- Structure for view `gettipekamar`
--
DROP TABLE IF EXISTS `gettipekamar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gettipekamar`  AS  select `type_ruangan`.`type_nama` AS `type_nama` from `type_ruangan` ;

-- --------------------------------------------------------

--
-- Structure for view `lihatruangankosong`
--
DROP TABLE IF EXISTS `lihatruangankosong`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `lihatruangankosong`  AS  select `ruangan`.`ruangan_no` AS `ruangan_no`,`type_ruangan`.`type_nama` AS `type_nama`,`type_ruangan`.`type_kapasitas` AS `type_kapasitas`,`type_ruangan`.`type_sewa` AS `type_sewa` from (`ruangan` join `type_ruangan` on((`ruangan`.`type_id` = `type_ruangan`.`type_id`))) where (`ruangan`.`ruangan_status` = 'kosong') ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `fasilitas`
--
ALTER TABLE `fasilitas`
  ADD PRIMARY KEY (`fasilitas_id`);

--
-- Indexes for table `fasilitas_ruangan`
--
ALTER TABLE `fasilitas_ruangan`
  ADD PRIMARY KEY (`fasilitas_ruangan_id`),
  ADD KEY `kamar_no` (`kamar_no`,`fasilitas_id`),
  ADD KEY `constraint1` (`fasilitas_id`);

--
-- Indexes for table `penghuni`
--
ALTER TABLE `penghuni`
  ADD PRIMARY KEY (`penghuni_id`),
  ADD KEY `constraint_penghuni_2` (`penghuni_nomor_kamar`);

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`ruangan_no`),
  ADD KEY `type_id` (`type_id`);

--
-- Indexes for table `type_ruangan`
--
ALTER TABLE `type_ruangan`
  ADD PRIMARY KEY (`type_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `fasilitas`
--
ALTER TABLE `fasilitas`
  MODIFY `fasilitas_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20006;

--
-- AUTO_INCREMENT for table `fasilitas_ruangan`
--
ALTER TABLE `fasilitas_ruangan`
  MODIFY `fasilitas_ruangan_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1230009;

--
-- AUTO_INCREMENT for table `type_ruangan`
--
ALTER TABLE `type_ruangan`
  MODIFY `type_id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1105;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20103;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `fasilitas_ruangan`
--
ALTER TABLE `fasilitas_ruangan`
  ADD CONSTRAINT `constraint_fasilitas_ruangan_1` FOREIGN KEY (`fasilitas_id`) REFERENCES `fasilitas` (`fasilitas_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `constraint_fasilitas_ruangan_2` FOREIGN KEY (`kamar_no`) REFERENCES `ruangan` (`ruangan_no`) ON UPDATE CASCADE;

--
-- Constraints for table `penghuni`
--
ALTER TABLE `penghuni`
  ADD CONSTRAINT `constraint_penghuni_1` FOREIGN KEY (`penghuni_id`) REFERENCES `user` (`user_id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `constraint_penghuni_2` FOREIGN KEY (`penghuni_nomor_kamar`) REFERENCES `ruangan` (`ruangan_no`) ON UPDATE CASCADE;

--
-- Constraints for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD CONSTRAINT `constraint_1` FOREIGN KEY (`type_id`) REFERENCES `type_ruangan` (`type_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
