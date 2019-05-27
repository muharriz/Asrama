-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 27 Mei 2019 pada 16.19
-- Versi Server: 10.1.26-MariaDB
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
-- Struktur dari tabel `fasilitas`
--

CREATE TABLE `fasilitas` (
  `fasilitas_id` int(8) NOT NULL,
  `fasilitas_nama` varchar(25) NOT NULL,
  `fasilitas_harga` int(8) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `fasilitas`
--

INSERT INTO `fasilitas` (`fasilitas_id`, `fasilitas_nama`, `fasilitas_harga`) VALUES
(20001, 'Televisi', 1200000),
(20002, 'Spring Bed Deluxe Size', 1300000),
(20004, 'Lemari 2 Pintu', 700000),
(20005, 'Spring Bed 2 in 1', 1500000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `fasilitas_ruangan`
--

CREATE TABLE `fasilitas_ruangan` (
  `kamar_no` int(8) NOT NULL,
  `fasilitas_id` int(8) NOT NULL,
  `fr_kuantitas` int(3) NOT NULL,
  `fr_kondisi` enum('rusak','bagus') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `fasilitas_ruangan`
--

INSERT INTO `fasilitas_ruangan` (`kamar_no`, `fasilitas_id`, `fr_kuantitas`, `fr_kondisi`) VALUES
(101, 20005, 2, 'bagus'),
(101, 20004, 2, 'bagus'),
(104, 20004, 2, 'bagus'),
(104, 20005, 2, 'bagus'),
(106, 20004, 2, 'bagus'),
(106, 20005, 2, 'bagus'),
(102, 20004, 2, 'bagus'),
(102, 20005, 2, 'bagus');

-- --------------------------------------------------------

--
-- Stand-in structure for view `gettipekamar`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `gettipekamar` (
`type_nama` varchar(20)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `lihatruangankosong`
-- (Lihat di bawah untuk tampilan aktual)
--
CREATE TABLE `lihatruangankosong` (
`ruangan_no` int(8)
,`type_nama` varchar(20)
,`type_kapasitas` int(1)
,`type_sewa` int(10)
);

-- --------------------------------------------------------

--
-- Struktur dari tabel `ruangan`
--

CREATE TABLE `ruangan` (
  `ruangan_no` int(8) NOT NULL,
  `type_id` int(10) NOT NULL,
  `ruangan_status` enum('kosong','ditempati') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `ruangan`
--

INSERT INTO `ruangan` (`ruangan_no`, `type_id`, `ruangan_status`) VALUES
(101, 1101, 'ditempati'),
(102, 1101, 'kosong'),
(104, 1101, 'ditempati'),
(105, 1102, 'kosong'),
(106, 1102, 'ditempati'),
(107, 1104, 'kosong');

-- --------------------------------------------------------

--
-- Struktur dari tabel `type_ruangan`
--

CREATE TABLE `type_ruangan` (
  `type_id` int(10) NOT NULL,
  `type_nama` varchar(20) NOT NULL,
  `type_kapasitas` int(1) NOT NULL,
  `type_sewa` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `type_ruangan`
--

INSERT INTO `type_ruangan` (`type_id`, `type_nama`, `type_kapasitas`, `type_sewa`) VALUES
(1101, 'reguler', 4, 3600000),
(1102, 'spesial', 2, 2000000),
(1104, 'vip', 1, 1200000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `user_id` int(8) NOT NULL,
  `user_username` varchar(25) NOT NULL,
  `user_password` longtext NOT NULL,
  `user_level` enum('admin','penghuni') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`user_id`, `user_username`, `user_password`, `user_level`) VALUES
(20101, 'root', 'root', 'admin'),
(20102, 'jakcie', '1234', 'penghuni');

-- --------------------------------------------------------

--
-- Struktur untuk view `gettipekamar`
--
DROP TABLE IF EXISTS `gettipekamar`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `gettipekamar`  AS  select `type_ruangan`.`type_nama` AS `type_nama` from `type_ruangan` ;

-- --------------------------------------------------------

--
-- Struktur untuk view `lihatruangankosong`
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
  ADD KEY `kamar_no` (`kamar_no`,`fasilitas_id`),
  ADD KEY `constraint1` (`fasilitas_id`);

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
-- AUTO_INCREMENT for table `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `ruangan_no` int(8) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=108;

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
-- Ketidakleluasaan untuk tabel pelimpahan (Dumped Tables)
--

--
-- Ketidakleluasaan untuk tabel `ruangan`
--
ALTER TABLE `ruangan`
  ADD CONSTRAINT `constraint_1` FOREIGN KEY (`type_id`) REFERENCES `type_ruangan` (`type_id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
