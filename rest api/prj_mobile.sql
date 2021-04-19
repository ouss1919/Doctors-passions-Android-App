-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mer. 19 fév. 2020 à 15:18
-- Version du serveur :  10.4.8-MariaDB
-- Version de PHP :  7.1.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `prj_mobile`
--

-- --------------------------------------------------------

--
-- Structure de la table `agenda`
--

CREATE TABLE `agenda` (
  `nssd` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `appointment`
--

CREATE TABLE `appointment` (
  `id_appointment` varchar(40) NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `nssd` varchar(40) NOT NULL,
  `nssp` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `doctor`
--

CREATE TABLE `doctor` (
  `nssd` varchar(40) NOT NULL,
  `id_office` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `adress` varchar(100) NOT NULL,
  `tel` varchar(15) NOT NULL,
  `password` varchar(40) NOT NULL,
  `connect` tinyint(1) NOT NULL,
  `Specialty` varchar(40) NOT NULL,
  `town` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `medication`
--

CREATE TABLE `medication` (
  `id_med` varchar(40) NOT NULL,
  `name` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `office`
--

CREATE TABLE `office` (
  `id_office` varchar(40) NOT NULL,
  `adress` varchar(40) NOT NULL,
  `openning_closing_time` varchar(40) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `nssp` varchar(40) NOT NULL,
  `first_name` varchar(40) NOT NULL,
  `last_name` varchar(40) NOT NULL,
  `adress` varchar(40) NOT NULL,
  `phone` varchar(15) NOT NULL,
  `password` varchar(40) NOT NULL,
  `connect` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `traitement_medication`
--

CREATE TABLE `traitement_medication` (
  `id` varchar(40) NOT NULL,
  `id_treatment` varchar(40) NOT NULL,
  `id_med` varchar(40) NOT NULL,
  `notif__time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `treating doctore`
--

CREATE TABLE `treating doctore` (
  `nssd` varchar(40) NOT NULL,
  `nssp` varchar(40) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Structure de la table `treatment`
--

CREATE TABLE `treatment` (
  `id_treatment` varchar(40) NOT NULL,
  `nssd` varchar(40) NOT NULL,
  `nssp` varchar(40) NOT NULL,
  `duration` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `agenda`
--
ALTER TABLE `agenda`
  ADD PRIMARY KEY (`nssd`);

--
-- Index pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`id_appointment`),
  ADD KEY `appointment_ibfk_1` (`nssd`),
  ADD KEY `appointment_ibfk_2` (`nssp`);

--
-- Index pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`nssd`),
  ADD KEY `id_office` (`id_office`);

--
-- Index pour la table `medication`
--
ALTER TABLE `medication`
  ADD PRIMARY KEY (`id_med`);

--
-- Index pour la table `office`
--
ALTER TABLE `office`
  ADD PRIMARY KEY (`id_office`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`nssp`);

--
-- Index pour la table `traitement_medication`
--
ALTER TABLE `traitement_medication`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_med` (`id_med`),
  ADD KEY `id_treatment` (`id_treatment`);

--
-- Index pour la table `treating doctore`
--
ALTER TABLE `treating doctore`
  ADD PRIMARY KEY (`nssd`,`nssp`),
  ADD KEY `nssp` (`nssp`);

--
-- Index pour la table `treatment`
--
ALTER TABLE `treatment`
  ADD PRIMARY KEY (`id_treatment`),
  ADD KEY `nssd` (`nssd`),
  ADD KEY `nssp` (`nssp`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `agenda`
--
ALTER TABLE `agenda`
  ADD CONSTRAINT `agenda_ibfk_1` FOREIGN KEY (`nssd`) REFERENCES `doctor` (`nssd`) ON DELETE CASCADE;

--
-- Contraintes pour la table `appointment`
--
ALTER TABLE `appointment`
  ADD CONSTRAINT `appointment_ibfk_1` FOREIGN KEY (`nssd`) REFERENCES `treating doctore` (`nssd`) ON DELETE CASCADE,
  ADD CONSTRAINT `appointment_ibfk_2` FOREIGN KEY (`nssp`) REFERENCES `treating doctore` (`nssp`) ON DELETE CASCADE;

--
-- Contraintes pour la table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `doctor_ibfk_1` FOREIGN KEY (`id_office`) REFERENCES `office` (`id_office`) ON DELETE CASCADE;

--
-- Contraintes pour la table `traitement_medication`
--
ALTER TABLE `traitement_medication`
  ADD CONSTRAINT `traitement_medication_ibfk_1` FOREIGN KEY (`id_med`) REFERENCES `medication` (`id_med`) ON DELETE CASCADE,
  ADD CONSTRAINT `traitement_medication_ibfk_2` FOREIGN KEY (`id_treatment`) REFERENCES `treatment` (`id_treatment`) ON DELETE CASCADE;

--
-- Contraintes pour la table `treating doctore`
--
ALTER TABLE `treating doctore`
  ADD CONSTRAINT `treating doctore_ibfk_1` FOREIGN KEY (`nssd`) REFERENCES `doctor` (`nssd`) ON DELETE CASCADE,
  ADD CONSTRAINT `treating doctore_ibfk_2` FOREIGN KEY (`nssp`) REFERENCES `patient` (`nssp`) ON DELETE CASCADE;

--
-- Contraintes pour la table `treatment`
--
ALTER TABLE `treatment`
  ADD CONSTRAINT `treatment_ibfk_1` FOREIGN KEY (`nssd`) REFERENCES `treating doctore` (`nssd`) ON DELETE CASCADE,
  ADD CONSTRAINT `treatment_ibfk_2` FOREIGN KEY (`nssp`) REFERENCES `treating doctore` (`nssp`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
