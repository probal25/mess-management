DROP TABLE IF EXISTS `expense`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `expense`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `date` date DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expense`
--

LOCK
TABLES `expense` WRITE;
/*!40000 ALTER TABLE `expense` DISABLE KEYS */;
INSERT INTO `expense` (`id`, `amount`, `date`)
VALUES (1, 5895, '2025-05-01'),
       (2, 1100, '2025-05-02'),
       (3, 840, '2025-05-03'),
       (4, 50, '2025-05-01'),
       (5, 120, '2025-05-04'),
       (6, 350, '2025-05-06'),
       (7, 150, '2025-05-06'),
       (8, 150, '2025-05-09'),
       (9, 170, '2025-05-08'),
       (10, 1920, '2025-05-10'),
       (11, 400, '2025-05-10');
/*!40000 ALTER TABLE `expense` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `meal_entry`
--

DROP TABLE IF EXISTS `meal_entry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `meal_entry`
(
    `id`        bigint NOT NULL AUTO_INCREMENT,
    `date`      date   DEFAULT NULL,
    `meal_count` double NOT NULL,
    `member_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY         `FK60clrvta91u6w6ei8gm4c52j9` (`member_id`),
    CONSTRAINT `FK60clrvta91u6w6ei8gm4c52j9` FOREIGN KEY (`member_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `meal_entry`
--

LOCK
TABLES `meal_entry` WRITE;
/*!40000 ALTER TABLE `meal_entry` DISABLE KEYS */;
INSERT INTO `meal_entry` (`id`, `date`, `meal_count`, `member_id`)
VALUES (1, '2025-05-01', 2, 1),
       (2, '2025-05-01', 2, 2),
       (3, '2025-05-01', 2, 3),
       (4, '2025-05-01', 2, 6),
       (6, '2025-05-02', 2, 1),
       (7, '2025-05-02', 2, 2),
       (8, '2025-05-02', 2, 3),
       (9, '2025-05-02', 2, 6),
       (10, '2025-05-03', 2, 6),
       (11, '2025-05-03', 2, 1),
       (12, '2025-05-03', 2, 2),
       (13, '2025-05-03', 1, 3),
       (14, '2025-05-04', 1, 3),
       (15, '2025-05-04', 1, 2),
       (16, '2025-05-04', 1, 2),
       (17, '2025-05-04', 1, 1),
       (18, '2025-05-04', 1, 4),
       (19, '2025-05-04', 3, 6),
       (20, '2025-05-05', 1, 1),
       (22, '2025-05-05', 2, 3),
       (23, '2025-05-05', 1, 4),
       (26, '2025-05-05', 2, 6),
       (29, '2025-05-05', 2.5, 2),
       (30, '2025-05-06', 0, 1),
       (31, '2025-05-06', 0, 2),
       (32, '2025-05-06', 0, 3),
       (33, '2025-05-06', 0, 4),
       (34, '2025-05-06', 0, 5),
       (35, '2025-05-06', 0, 6),
       (36, '2025-05-07', 1, 1),
       (37, '2025-05-07', 2, 2),
       (38, '2025-05-07', 1, 3),
       (39, '2025-05-07', 0, 4),
       (40, '2025-05-07', 2, 5),
       (41, '2025-05-07', 0, 6),
       (43, '2025-05-08', 2, 3),
       (44, '2025-05-08', 2, 2),
       (45, '2025-05-08', 0, 4),
       (46, '2025-05-08', 0, 6),
       (47, '2025-05-08', 1, 1),
       (48, '2025-05-09', 2.5, 1),
       (49, '2025-05-09', 2.5, 3),
       (50, '2025-05-09', 2.5, 5),
       (51, '2025-05-09', 2, 2),
       (52, '2025-05-08', 2, 5),
       (53, '2025-05-10', 2.5, 1),
       (54, '2025-05-10', 2.5, 2),
       (55, '2025-05-10', 2, 3),
       (56, '2025-05-10', 0, 4),
       (57, '2025-05-10', 2, 5),
       (58, '2025-05-10', 0, 6),
       (59, '2025-05-11', 0, 6),
       (60, '2025-05-11', 2, 5),
       (61, '2025-05-11', 0, 4),
       (62, '2025-05-11', 2, 3),
       (63, '2025-05-11', 2.5, 2),
       (64, '2025-05-11', 1.5, 1);
/*!40000 ALTER TABLE `meal_entry` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member`
(
    `id`   bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK
TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` (`id`, `name`)
VALUES (1, 'Probal'),
       (2, 'Biplob'),
       (3, 'Anik'),
       (4, 'Imran'),
       (5, 'Utsha'),
       (6, 'Alif');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK
TABLES;

DROP TABLE IF EXISTS `member_vault`;

CREATE TABLE `member_vault`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `amount` double NOT NULL,
    `date`       date   DEFAULT NULL,
    `paid_by_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY          `FKkw6eyo5t7sbvkrcpwtcsi6m7l` (`paid_by_id`),
    CONSTRAINT `FKkw6eyo5t7sbvkrcpwtcsi6m7l` FOREIGN KEY (`paid_by_id`) REFERENCES `member` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK
TABLES `member_vault` WRITE;
INSERT INTO `member_vault` (`id`, `amount`, `date`, `paid_by_id`)
VALUES (1, 3000, '2025-05-01', 3),
       (2, 3895, '2025-05-01', 1),
       (3, 100, '2025-05-02', 1),
       (4, 840, '2025-05-02', 6),
       (5, 50, '2025-05-01', 2),
       (6, 120, '2025-05-04', 2),
       (7, 150, '2025-05-06', 2),
       (8, 350, '2025-05-06', 1),
       (9, 1240, '2025-05-06', 4),
       (10, 150, '2025-05-09', 1),
       (11, 170, '2025-05-08', 2),
       (12, 1920, '2025-05-10', 5),
       (13, 400, '2025-05-10', 1);
UNLOCK
TABLES;

