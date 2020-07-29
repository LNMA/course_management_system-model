CREATE DATABASE  IF NOT EXISTS `course_management_system` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `course_management_system`;
-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: course_management_system
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course_feedback`
--

DROP TABLE IF EXISTS `course_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_feedback` (
  `feedback_id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `course_file_feedback_course_id_IX` (`course_id`),
  CONSTRAINT `fk_courses_course_id_course_file_feedback_course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_feedback`
--

LOCK TABLES `course_feedback` WRITE;
/*!40000 ALTER TABLE `course_feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_members`
--

DROP TABLE IF EXISTS `course_members`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_members` (
  `member_id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` varchar(200) NOT NULL,
  `course_id` bigint NOT NULL,
  `register_date` timestamp NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `course_members_course_id_IX` (`course_id`),
  KEY `course_members_student_id_IX` (`student_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_courses_course_id_course_members_course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_students_details_id_course_members_student_id` FOREIGN KEY (`student_id`) REFERENCES `students_details` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_members`
--

LOCK TABLES `course_members` WRITE;
/*!40000 ALTER TABLE `course_members` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_members` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` bigint NOT NULL AUTO_INCREMENT,
  `course_name` varchar(150) NOT NULL,
  `start_date` timestamp NOT NULL,
  `end_date` timestamp NOT NULL,
  `instructor_id` varchar(200) NOT NULL,
  PRIMARY KEY (`course_id`),
  KEY `courses_instructor_id_IX` (`instructor_id`),
  CONSTRAINT `fk_instructors_details_id_courses_instructor_id` FOREIGN KEY (`instructor_id`) REFERENCES `instructors_details` (`instructors_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses_materials`
--

DROP TABLE IF EXISTS `courses_materials`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses_materials` (
  `material_id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` bigint NOT NULL,
  PRIMARY KEY (`material_id`),
  KEY `courses_materials_course_id_IX` (`course_id`),
  CONSTRAINT `fk_courses_course_id_courses_materials_course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses_materials`
--

LOCK TABLES `courses_materials` WRITE;
/*!40000 ALTER TABLE `courses_materials` DISABLE KEYS */;
/*!40000 ALTER TABLE `courses_materials` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_comments`
--

DROP TABLE IF EXISTS `feedback_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_comments` (
  `comment_id` bigint NOT NULL AUTO_INCREMENT,
  `feedback_id` bigint NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `comment_message` varchar(200) NOT NULL,
  `comment_date` timestamp NOT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `feedback_comments_feedback_id_IX` (`feedback_id`) /*!80000 INVISIBLE */,
  KEY `feedback_comments_user_id_IX` (`user_id`),
  CONSTRAINT `fk_course_feedback_id_feedback_comments_id` FOREIGN KEY (`feedback_id`) REFERENCES `course_feedback` (`feedback_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_feedback_comments_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_comments`
--

LOCK TABLES `feedback_comments` WRITE;
/*!40000 ALTER TABLE `feedback_comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_file`
--

DROP TABLE IF EXISTS `feedback_file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_file` (
  `feedback_id` bigint NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `file` longblob NOT NULL,
  `file_extension` varchar(200) NOT NULL,
  `upload_date` timestamp NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `feedback_file_user_id_IX` (`user_id`),
  CONSTRAINT `fk_course_feedback_id_feedback_file_id` FOREIGN KEY (`feedback_id`) REFERENCES `course_feedback` (`feedback_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_feedback_file_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_file`
--

LOCK TABLES `feedback_file` WRITE;
/*!40000 ALTER TABLE `feedback_file` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback_message`
--

DROP TABLE IF EXISTS `feedback_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedback_message` (
  `feedback_id` bigint NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `post_message` varchar(2000) NOT NULL,
  `feedback_date` timestamp NOT NULL,
  PRIMARY KEY (`feedback_id`),
  KEY `feedback_message_user_id_IX` (`user_id`),
  CONSTRAINT `fk_course_feedback_id_feedback_message_id` FOREIGN KEY (`feedback_id`) REFERENCES `course_feedback` (`feedback_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_feedback_message_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback_message`
--

LOCK TABLES `feedback_message` WRITE;
/*!40000 ALTER TABLE `feedback_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `instructors_details`
--

DROP TABLE IF EXISTS `instructors_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `instructors_details` (
  `instructors_id` varchar(200) NOT NULL,
  `headline` varchar(300) DEFAULT NULL,
  `specialty` varchar(300) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `portfolio` varchar(300) DEFAULT NULL,
  `profile_visibility` enum('PUBLIC','PRIVATE') NOT NULL,
  PRIMARY KEY (`instructors_id`),
  CONSTRAINT `fk_users_email_instructors_details_instructors_id` FOREIGN KEY (`instructors_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `instructors_details`
--

LOCK TABLES `instructors_details` WRITE;
/*!40000 ALTER TABLE `instructors_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `instructors_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials_files`
--

DROP TABLE IF EXISTS `materials_files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials_files` (
  `material_id` bigint NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `material_name` varchar(200) NOT NULL,
  `material_file` longblob NOT NULL,
  `upload_date` timestamp NOT NULL,
  PRIMARY KEY (`material_id`),
  KEY `materials_files_user_id_IX` (`user_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_courses_materials_id_materials_files_id` FOREIGN KEY (`material_id`) REFERENCES `courses_materials` (`material_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_materials_files_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials_files`
--

LOCK TABLES `materials_files` WRITE;
/*!40000 ALTER TABLE `materials_files` DISABLE KEYS */;
/*!40000 ALTER TABLE `materials_files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `materials_links`
--

DROP TABLE IF EXISTS `materials_links`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `materials_links` (
  `material_id` bigint NOT NULL,
  `user_id` varchar(200) NOT NULL,
  `material_name` varchar(200) NOT NULL,
  `material_link` varchar(2000) NOT NULL,
  `upload_date` datetime NOT NULL,
  PRIMARY KEY (`material_id`),
  KEY `materials_links_user_id_IX` (`user_id`),
  CONSTRAINT `fk_courses_materials_id_materials_links_id` FOREIGN KEY (`material_id`) REFERENCES `courses_materials` (`material_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_materials_links_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materials_links`
--

LOCK TABLES `materials_links` WRITE;
/*!40000 ALTER TABLE `materials_links` DISABLE KEYS */;
/*!40000 ALTER TABLE `materials_links` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` enum('ADMIN','INSTRUCTOR','STUDENT') NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_attendances`
--

DROP TABLE IF EXISTS `students_attendances`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students_attendances` (
  `attendance_id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` varchar(200) NOT NULL,
  `course_id` bigint NOT NULL,
  `attendance_date` timestamp NOT NULL,
  PRIMARY KEY (`attendance_id`),
  KEY `students_attendances_student_id_IX` (`student_id`) /*!80000 INVISIBLE */,
  KEY `students_attendances_course_id_IX` (`course_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_courses_course_id_students_attendances_course_id` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_students_details_id_attendances_student_id` FOREIGN KEY (`student_id`) REFERENCES `students_details` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_attendances`
--

LOCK TABLES `students_attendances` WRITE;
/*!40000 ALTER TABLE `students_attendances` DISABLE KEYS */;
/*!40000 ALTER TABLE `students_attendances` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students_details`
--

DROP TABLE IF EXISTS `students_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students_details` (
  `student_id` varchar(200) NOT NULL,
  `headline` varchar(300) DEFAULT NULL,
  `interests` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`student_id`),
  CONSTRAINT `fk_users_email_students_details_student_id` FOREIGN KEY (`student_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students_details`
--

LOCK TABLES `students_details` WRITE;
/*!40000 ALTER TABLE `students_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `students_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `email` varchar(200) NOT NULL,
  `password` varchar(100) NOT NULL,
  `join_date` datetime NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_course_join`
--

DROP TABLE IF EXISTS `users_course_join`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_course_join` (
  `users_id` varchar(200) NOT NULL,
  `is_busy` tinyint(1) NOT NULL,
  `join_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`users_id`),
  CONSTRAINT `fk_users_email_users_course_join_user_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_course_join`
--

LOCK TABLES `users_course_join` WRITE;
/*!40000 ALTER TABLE `users_course_join` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_course_join` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_details`
--

DROP TABLE IF EXISTS `users_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_details` (
  `user_id` varchar(200) NOT NULL,
  `forename` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `phone` int DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_email_users_details_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_details`
--

LOCK TABLES `users_details` WRITE;
/*!40000 ALTER TABLE `users_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_profile_picture`
--

DROP TABLE IF EXISTS `users_profile_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_profile_picture` (
  `user_id` varchar(200) NOT NULL,
  `upload_date` timestamp NOT NULL,
  `picture` longblob NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_details_id_profile_picture_user_id` FOREIGN KEY (`user_id`) REFERENCES `users_details` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_profile_picture`
--

LOCK TABLES `users_profile_picture` WRITE;
/*!40000 ALTER TABLE `users_profile_picture` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_profile_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` varchar(200) NOT NULL,
  `role_id` bigint NOT NULL,
  UNIQUE KEY `users_roles_user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `users_roles_role_id_UNIQUE` (`role_id`),
  CONSTRAINT `fk_roles_role_id_users_roles_role_id` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_users_email_users_roles_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_sign_in_date`
--

DROP TABLE IF EXISTS `users_sign_in_date`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_sign_in_date` (
  `user_sign_in_id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` varchar(200) NOT NULL,
  `sign_in_date` datetime NOT NULL,
  PRIMARY KEY (`user_sign_in_id`),
  KEY `users_sign_in_date_user_id_IX` (`user_id`),
  CONSTRAINT `fk_users_email_users_sign_in_date_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_sign_in_date`
--

LOCK TABLES `users_sign_in_date` WRITE;
/*!40000 ALTER TABLE `users_sign_in_date` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_sign_in_date` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_status`
--

DROP TABLE IF EXISTS `users_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_status` (
  `user_id` varchar(200) NOT NULL,
  `is_online` tinyint(1) NOT NULL,
  `is_valid` tinyint(1) NOT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `fk_users_email_users_status_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_status`
--

LOCK TABLES `users_status` WRITE;
/*!40000 ALTER TABLE `users_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-28 14:00:18
