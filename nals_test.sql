-- --------------------------------------------------------
-- Host:                         45.77.251.72
-- Server version:               5.7.37-0ubuntu0.18.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for nals_test
CREATE DATABASE IF NOT EXISTS `nals_test` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `nals_test`;

-- Dumping structure for table nals_test.tb_work
CREATE TABLE IF NOT EXISTS `tb_work` (
  `work_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL DEFAULT '0',
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'work status (1: Planning, 2: Doing, 3: Complete)',
  `created_at` datetime NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`work_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4;

-- Dumping data for table nals_test.tb_work: ~18 rows (approximately)
/*!40000 ALTER TABLE `tb_work` DISABLE KEYS */;
INSERT INTO `tb_work` (`work_id`, `name`, `start_date`, `end_date`, `status`, `created_at`, `updated_at`) VALUES
	(3, 'test abc1', '2022-10-22', '2022-10-22', 1, '2022-02-27 16:13:44', '2022-02-27 16:13:44'),
	(5, 'test abc3', '2022-10-22', '2022-10-22', 1, '2022-02-27 16:24:01', '2022-02-27 16:24:01'),
	(6, 'test abc2', '2022-10-22', '2022-10-22', 1, '2022-02-27 16:28:21', '2022-02-27 16:28:21'),
	(7, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:10', '2022-02-27 17:25:10'),
	(8, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:18', '2022-02-27 17:25:18'),
	(9, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:21', '2022-02-27 17:25:21'),
	(10, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:25', '2022-02-27 17:25:25'),
	(11, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:28', '2022-02-27 17:25:28'),
	(12, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:33', '2022-02-27 17:25:33'),
	(13, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:36', '2022-02-27 17:25:36'),
	(14, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:39', '2022-02-27 17:25:39'),
	(15, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:42', '2022-02-27 17:25:42'),
	(16, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:45', '2022-02-27 17:25:45'),
	(17, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:49', '2022-02-27 17:25:49'),
	(18, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:52', '2022-02-27 17:25:52'),
	(19, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:55', '2022-02-27 17:25:55'),
	(20, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 17:25:59', '2022-02-27 17:25:59'),
	(21, 'work name', '2022-02-28', '2022-03-15', 1, '2022-02-27 19:19:37', '2022-02-27 19:19:37');
/*!40000 ALTER TABLE `tb_work` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
