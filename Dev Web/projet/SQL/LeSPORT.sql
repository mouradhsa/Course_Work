DROP DATABASE IF EXISTS `dev_web`;
CREATE DATABASE IF NOT EXISTS `dev_web` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `dev_web`;

-- --------------------------------------------------------

--
-- Structure de la table `products`
--

DROP TABLE IF EXISTS `products`;
CREATE TABLE IF NOT EXISTS `products` (
  `id2` int(11) NOT NULL AUTO_INCREMENT,
  `id` varchar(11) NOT NULL,
  `title` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_mysql500_ci NOT NULL,
  `description` text CHARACTER SET latin7 COLLATE latin7_general_cs NOT NULL,
  `price` int(3) NOT NULL,
  `Categorie` varchar(255) NOT NULL,
  `stock` int(11) NOT NULL,
  PRIMARY KEY (`id2`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
