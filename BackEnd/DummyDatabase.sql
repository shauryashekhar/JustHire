-- MySQL dump 10.13  Distrib 5.5.60, for Win64 (AMD64)
--
-- Host: localhost    Database: justhire
-- ------------------------------------------------------
-- Server version	5.5.60

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `candidate`
--

DROP TABLE IF EXISTS `candidate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `candidate` (
  `candidate_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `score` float(4,2) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `resume` varchar(55) DEFAULT NULL,
  `experience` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,'Komal','Naik','komal@email.com',0.00,'pending',NULL,'5+'),(2,'Akash','Kadel','akash@email.com',0.00,'pending',NULL,'5+'),(3,'Chetan','Kalode','chetan@email.com',0.00,'pending',NULL,'5+'),(4,'Rushabh','Kapadia','rushabh@email.com',0.00,'ongoing',NULL,'5+'),(5,'Komal','Naik','komal@email.com',0.00,'pending',NULL,'0');
/*!40000 ALTER TABLE `candidate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interview`
--

DROP TABLE IF EXISTS `interview`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interview` (
  `interview_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `candidate_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`interview_id`),
  KEY `user_id` (`user_id`),
  KEY `candidate_id` (`candidate_id`),
  CONSTRAINT `interview_ibfk_2` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
  CONSTRAINT `interview_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interview`
--

LOCK TABLES `interview` WRITE;
/*!40000 ALTER TABLE `interview` DISABLE KEYS */;
/*!40000 ALTER TABLE `interview` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `login` (
  `user_id` int(11) NOT NULL,
  `email` varchar(55) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES (1,'shaurya@email.com','admin'),(2,'hashmeet@email.com','user'),(3,'rupesh@email.com','user');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `process_status`
--

DROP TABLE IF EXISTS `process_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `process_status` (
  `candidate_id` int(11) NOT NULL,
  `round_id` int(11) NOT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`),
  CONSTRAINT `process_status_ibfk_1` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `process_status`
--

LOCK TABLES `process_status` WRITE;
/*!40000 ALTER TABLE `process_status` DISABLE KEYS */;
INSERT INTO `process_status` VALUES (1,1,'pending'),(2,1,'pending'),(3,1,'pending'),(4,1,'pending');
/*!40000 ALTER TABLE `process_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(900) DEFAULT NULL,
  `difficulty` varchar(25) DEFAULT NULL,
  `experience` varchar(10) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `round_number` int(11) DEFAULT NULL,
  `is_approved` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `round_number` (`round_number`),
  CONSTRAINT `questions_ibfk_1` FOREIGN KEY (`round_number`) REFERENCES `rounds` (`round_number`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (47,'What do you mean by Object?','Easy','0','runtime entity',1,0),(48,'Define class?','Easy','0','runtime entity and its state',1,0),(49,'What is Singleton class?','Easy','0','control object creation',1,0),(50,'What is a static variable?','Easy','0','Class variables',1,0),(51,'What is the default value of float and double datatype in Java?','Easy','0','0.0f and 0.0d',1,0),(52,'What do you mean by Access Modifier?','Medium','0','set access levels',1,0),(53,'Why is String class considered immutable?','Medium','0','object cannot be changed',1,0),(54,'Why is StringBuffer called mutable?','Medium','0','modifications to Strings of characters then StringBuffer',1,0),(55,'What is the difference between StringBuffer and StringBuilder class?','Medium','0','StringBuilder is faster than StringBuffer',1,0),(56,'Which package is used for pattern matching with regular expressions?','Medium','0','sample comment',1,0),(57,'What do you mean by Multithreaded program?','Hard','0','run concurrently',1,0),(58,'Explain garbage collection in Java?','Hard','0','free the memory',1,0),(59,'Explain the usage of this() with constructors?','Hard','0','call constructer of same class',1,0),(60,'Difference between throw and throws?','Hard','0','Throw is used to trigger an exception where as throws is used in declaration of exception',1,0),(61,'Can a constructor be made final?','Hard','0','No',1,0),(62,'What is static block?','Easy','0','used to initialize the static data member',2,0),(63,'Define composition?','Easy','0','Holding the reference of the other class',2,0),(64,'What is function overloading?','Easy','0','multiple functions by same name but different parameters',2,0),(65,'What is function overriding?','Easy','0','subclass provides a specific implementation of a method',2,0),(66,'Difference between Overloading and Overriding?','Easy','0','2 to 3 differences',2,0),(67,'What is NullPointerException?','Medium','0','instance method of a null object',2,0),(68,'What are the ways in which a thread can enter the waiting state?','Medium','0',' sleep() and wait()',2,0),(69,'How does multi-threading take place on a computer with a single CPU?','Medium','0','operating system\'s task scheduler',2,0),(70,'What invokes a thread\'s run() method?','Medium','0','when the thread is initially executed',2,0),(71,'Does it matter in what order catch statements for FileNotFoundException and IOException are written?','Medium','0','Yes it does',2,0),(72,'What is the difference between yielding and sleeping?','Hard','0','ready and waiting state',2,0),(73,'What are Wrapper classes?','Hard','0','allow primitive types to be accessed as objects',2,0),(74,'Which package has light weight components?','Hard','0','javax.Swing package',2,0),(75,'What is the difference between the paint() and repaint() methods?','Hard','0','Graphics object',2,0),(76,'What is the purpose of File class?','Hard','0','provide access to the files and directories',2,0),(77,'What is Serialization and deserialization?','Easy','0','writing the state of an object',3,0),(78,'What are use cases?','Easy','0','describes a situation that a program might encounter',3,0),(79,'What is the difference between Swing and AWT components?','Easy','0','AWT components are heavy-weight whereas Swing components are lightweight.',3,0),(80,'Is there any limitation of using Inheritance?','Easy','0','makes the subclass too clustering and sometimes error-prone',3,0),(81,'When is the ArrayStoreException thrown?','Easy','0','When copying elements between different arrays',3,0),(82,'Can you call one constructor from another if a class has multiple constructors?','Medium','0','Yes use this() syntax.',3,0),(83,'What is a transient variable?','Medium','0','not be serialized during Serialization',3,0),(84,'What is synchronization?','Medium','0','capability to control the access of multiple threads',3,0),(85,'Does garbage collection guarantee that a program will not run out of memory?','Medium','0','No',3,0),(86,'What is the purpose of the System class?','Medium','0','provide access to system resources',3,0),(87,'Under what conditions is an object\'s finalize() method invoked by the garbage collector?','Hard','0','when it detects that the object has become unreachable',3,0),(88,'How can a dead thread be restarted?','Hard','0','not possible',3,0),(89,'What are ClassLoaders?','Hard','0','object that is responsible for loading classes',3,0),(90,'Why do we need wrapper classes?','Hard','0','provides utility methods',3,0),(91,'What is the difference between error and an exception?','Hard','0','error is an irrecoverable',3,0),(92,'What are the advantages of ArrayList over arrays?','Easy','1 to 5 ','involves only updating the next pointer',1,0),(93,'How do you decide when to use ArrayList and LinkedList?','Easy','1 to 5 ','2 to 3 differences',1,0),(94,'What is a Values Collection View ?','Easy','1 to 5 ','What is a Values Collection View ?',1,0),(95,'Where and how can you use a private constructor?','Easy','1 to 5 ','do not want other classes to instantiate',1,0),(96,'Describe life cycle of thread?','Easy','1 to 5 ','5 states',1,0),(97,'What is the difference between the >> and >>> operators?','Medium','1 to 5 ','zero-fills bits',1,0),(98,'Which method of the Component class is used to set the position and size of a component?','Medium','1 to 5 ','setBounds()',1,0),(99,'What is the range of the short type?','Medium','1 to 5 ','(2^15) to 2^15 - 1',1,0),(100,'What is the immediate superclass of Menu?','Medium','1 to 5 ','MenuItem class',1,0),(101,'Does Java allow Default Arguments?','Medium','1 to 5 ','No',1,0),(102,'Which number is denoted by leading zero in java?','Hard','1 to 5 ','Octal Numbers',1,0),(103,'Explain suspend() method under Thread class>','Hard','1 to 5 ','to pause or temporarily stop the execution',1,0),(104,'Explain main thread under Thread class execution?','Hard','1 to 5 ','created automatically and it begins to execute immediately when a program starts',1,0),(105,'Define code as an attribute of Applet?','Hard','1 to 5 ','specify the name of the applet class',1,0),(106,'Define Network Programming?','Hard','1 to 5 ','writing programs that execute across multiple devices',1,0),(107,'What is a Socket?','Easy','1 to 5 ','communication mechanism between two computers using TCP',2,0),(108,'Advantages of Java Sockets?','Easy','1 to 5 ','flexible and sufficient',2,0),(109,'Which class is used by server applications to obtain a port and listen for client requests?','Easy','1 to 5 ','java.net.ServerSocket',2,0),(110,'Why Generics are used in Java?','Easy','1 to 5 ','provide compile-time type safety',2,0),(111,'What is Nested top-level class?','Easy','1 to 5 ','a class is declared within a class',2,0),(112,'If System.exit (0); is written at the end of the try block  will the finally block still execute?','Medium','1 to 5 ','No',2,0),(113,'What is daemon thread?','Medium','1 to 5 ','low priority thread',2,0),(114,'Which method is used to create the daemon thread?','Medium','1 to 5 ','setDaemon method',2,0),(115,'What is the SimpleTimeZone class?','Medium','1 to 5 ','provides support for a Gregorian calendar',2,0),(116,'Can a vector contain heterogenous objects?','Medium','1 to 5 ','Yes a Vector can contain heterogenous objects',2,0),(117,'What is difference between Path and Classpath?','Hard','1 to 5 ','operating system level environment variales',2,0),(118,'Can a class declared as private be accessed outside it\'s package?','Hard','1 to 5 ','No',2,0),(119,'Can an Interface extend another Interface?','Hard','1 to 5 ','yes',2,0),(120,'What is an object\'s lock and which object\'s have locks?','Hard','1 to 5 ','used by multiple threads to obtain synchronized access to the object',2,0),(121,'If a method is declared as protected  where may the method be accessed?','Hard','1 to 5 ','only be accessed by classes or interfaces of the same package',2,0),(122,'What is an Iterator?','Easy','1 to 5 ','provide traversal',3,0),(123,'What if the main method is declared as private?','Easy','1 to 5 ','compiles properly but at runtime it will give  Main method not public.  message',3,0),(124,'What if I do not provide the String array as the argument to the method','Easy','1 to 5 ','runtime error  NoSuchMethodError ',3,0),(125,'What is the first argument of the String array in main method','Easy','1 to 5 ','The String array is empty',3,0),(126,'If I do not provide any arguments on the command line  then the String array of Main method will be empty or null?','Easy','1 to 5 ','It is empty. But not null.',3,0),(127,'Can an application have multiple classes having main method?','Medium','1 to 5 ','Yes it is possible.',3,0),(128,'What are Checked and UnChecked Exception?','Medium','1 to 5 ','Exception',3,0),(129,'What are different types of inner classes?','Medium','1 to 5 ','3 to 4 types',3,0),(130,'Does importing a package imports the subpackages as well?','Medium','1 to 5 ','No you will have to import the subpackages explicitly',3,0),(131,'Which methods of Serializable interface should I implement?','Medium','1 to 5 ','The serializable interface is an empty interface',3,0),(132,'What one should take care of while serializing the object?','Hard','1 to 5 ','One should make sure that all the included objects are also serializable',3,0),(133,'What happens to the static fields of a class during serialization?','Hard','1 to 5 ','Serialization ignores static fields',3,0),(134,'How are Observer and Observable used?','Hard','1 to 5 ','Objects that subclass the Observable class maintain a list of observers',3,0),(135,'How does Java handle integer overflows and underflows?','Hard','1 to 5 ','It uses those low order bytes of the result that can fit into the size of the type allowed by the operation',3,0),(136,'If I write return at the end of the try block  will the finally block still execute?','Hard','1 to 5 ','Yes',3,0),(137,'What are J2EE Patterns?','Easy','5+','These patterns are identified by Sun Java Center',1,0),(138,'What is Factory pattern?','Easy','5+','e create object without exposing the creation logic to the client',1,0),(139,'How can you create Singleton class in java?','Easy','5+','two step process',1,0),(140,'What are the difference between a static class and a singleton class?','Easy','5+','2 to 3 differences',1,0),(141,'Can we create a clone of a singleton object?','Easy','5+','yes',1,0),(142,'How to prevent cloning of a singleton object?','Medium','5+','Throw exception within the body of clone()',1,0),(143,'What is Adapter pattern?','Medium','5+','works as a bridge between two incompatible interfaces',1,0),(144,'What are the entities of Service Locator pattern?','Medium','5+','example with explanation',1,0),(145,'Name the actor classes used in Memento pattern.','Medium','5+','three actor classes',1,0),(146,'What is Strategy pattern?','Medium','5+','class behavior or its algorithm can be changed at run time',1,0),(147,'What is MVC pattern?','Hard','5+','pattern is used to separate application\'s concerns',1,0),(148,'What is Data Access Object Pattern(DAO) pattern?','Hard','5+','used to separate low level data accessing API or operations from high level business services',1,0),(149,'What is Visitor pattern?','Hard','5+','we use a visitor class which changes the executing algorithm of an element class.',1,0),(150,'Give an example where Interpreter pattern is used?','Hard','5+','This pattern is used in SQL parsing',1,0),(151,'What is Proxy pattern?','Hard','5+','a class represents functionality of another class',1,0),(152,'What is Spring?','Easy','5+','open source development framework for enterprise Java',2,0),(153,'What is Spring configuration file?','Easy','5+','contains the classes information and describes how these classes are configured and introduced to each other',2,0),(154,'What is Dependency Injection?','Easy','5+','Inversion of Control (IoC)',2,0),(155,'What are the different types of IoC (dependency injection)?','Easy','5+','2 types',2,0),(156,'What is AOP?','Easy','5+','to modularize crosscutting concerns',2,0),(157,'Give an example of BeanFactory implementation.','Medium','5+','XmlBeanFactoryclass',2,0),(158,'What is the difference between Bean Factory and ApplicationContext?','Medium','5+','2 to 3 differences',2,0),(159,'What does a bean definition contain?','Medium','5+','configuration metadata',2,0),(160,'How do add a bean in spring application?','Medium','5+','code or annotation',2,0),(161,'What bean scopes does Spring support? Explain them.','Medium','5+','five scopes',2,0),(162,'Are Singleton beans thread safe in Spring Framework?','Hard','5+','no',2,0),(163,'What is bean auto wiring?','Hard','5+','able to autowire relationships between collaborating beans',2,0),(164,'How is event handling done in Spring?','Hard','5+','ApplicationContext',2,0),(165,'How JdbcTemplate can be used?','Hard','5+','provides many convenience',2,0),(166,'Explain the@Controllerannotation.','Hard','5+','indicates that a particular class serves the role of a controller.',2,0),(167,'What is the difference between SQL and MySQL or SQL Server?','Easy','5+','proper difference with expanation',3,0),(168,'What are various DDL commands in SQL? Give brief description of their purposes.','Easy','5+','various DDL or Data Definition Language commands',3,0),(169,'Is a NULL value same as zero or a blank space? If not then what is the difference?','Easy','5+','A NULL value is not same as zero or a blank space',3,0),(170,'How can you eliminate duplicate rows from a query result?','Easy','5+','DISTINCT keyword',3,0),(171,'What are the specific uses of SQL functions?','Easy','5+','4 to 5 uses',3,0),(172,'What is the use of the NULLIF function?','Medium','5+','compares two expressions',3,0),(173,'Discuss the syntax and use of the COALESCE function?','Medium','5+','returns the first non-null expression given in the parameter list',3,0),(174,'What do you understand by a subquery? When is it used?','Medium','5+','a SELECT statement embedded in a clause of another SELECT statement',3,0),(175,'How do you copy rows from one table to another?','Medium','5+','a subquery is used in the place of the VALUES clause.',3,0),(176,'Can you modify the rows in a table based on values from another table?','Medium','5+','Yes.',3,0),(177,'What happens if you omit the WHERE clause in a delete statement?','Hard','5+','All the rows in the table are deleted.',3,0),(178,'All the rows in the table are deleted.','Hard','5+','subqueries can be used',3,0),(179,'What is the difference between VARCHAR2 AND CHAR datatypes?','Hard','5+','length character data',3,0),(180,'What is a view? Why should you use a view?','Hard','5+','view is a logical snapshot based on a table or another view.',3,0),(181,'Say True or False. Give explanation if False.All group functions ignore null values.','Hard','5+','True.',3,0),(182,'What do you mean by Object?','Easy','0','runtime entity',1,0),(183,'Define class?','Easy','0','runtime entity and its state',1,0),(184,'What is Singleton class?','Easy','0','control object creation',1,0),(185,'What is a static variable?','Easy','0','Class variables',1,0),(186,'What is the default value of float and double datatype in Java?','Easy','0','0.0f and 0.0d',1,0),(187,'What do you mean by Access Modifier?','Medium','0','set access levels',1,0),(188,'Why is String class considered immutable?','Medium','0','object cannot be changed',1,0),(189,'Why is StringBuffer called mutable?','Medium','0','modifications to Strings of characters then StringBuffer',1,0),(190,'What is the difference between StringBuffer and StringBuilder class?','Medium','0','StringBuilder is faster than StringBuffer',1,0),(191,'Which package is used for pattern matching with regular expressions?','Medium','0','sample comment',1,0),(192,'What do you mean by Multithreaded program?','Hard','0','run concurrently',1,0),(193,'Explain garbage collection in Java?','Hard','0','free the memory',1,0),(194,'Explain the usage of this() with constructors?','Hard','0','call constructer of same class',1,0),(195,'Difference between throw and throws?','Hard','0','Throw is used to trigger an exception where as throws is used in declaration of exception',1,0),(196,'Can a constructor be made final?','Hard','0','No',1,0),(197,'What is static block?','Easy','0','used to initialize the static data member',2,0),(198,'Define composition?','Easy','0','Holding the reference of the other class',2,0),(199,'What is function overloading?','Easy','0','multiple functions by same name but different parameters',2,0),(200,'What is function overriding?','Easy','0','subclass provides a specific implementation of a method',2,0),(201,'Difference between Overloading and Overriding?','Easy','0','2 to 3 differences',2,0),(202,'What is NullPointerException?','Medium','0','instance method of a null object',2,0),(203,'What are the ways in which a thread can enter the waiting state?','Medium','0',' sleep() and wait()',2,0),(204,'How does multi-threading take place on a computer with a single CPU?','Medium','0','operating system\'s task scheduler',2,0),(205,'What invokes a thread\'s run() method?','Medium','0','when the thread is initially executed',2,0),(206,'Does it matter in what order catch statements for FileNotFoundException and IOException are written?','Medium','0','Yes it does',2,0),(207,'What is the difference between yielding and sleeping?','Hard','0','ready and waiting state',2,0),(208,'What are Wrapper classes?','Hard','0','allow primitive types to be accessed as objects',2,0),(209,'Which package has light weight components?','Hard','0','javax.Swing package',2,0),(210,'What is the difference between the paint() and repaint() methods?','Hard','0','Graphics object',2,0),(211,'What is the purpose of File class?','Hard','0','provide access to the files and directories',2,0),(212,'What is Serialization and deserialization?','Easy','0','writing the state of an object',3,0),(213,'What are use cases?','Easy','0','describes a situation that a program might encounter',3,0),(214,'What is the difference between Swing and AWT components?','Easy','0','AWT components are heavy-weight whereas Swing components are lightweight.',3,0),(215,'Is there any limitation of using Inheritance?','Easy','0','makes the subclass too clustering and sometimes error-prone',3,0),(216,'When is the ArrayStoreException thrown?','Easy','0','When copying elements between different arrays',3,0),(217,'Can you call one constructor from another if a class has multiple constructors?','Medium','0','Yes use this() syntax.',3,0),(218,'What is a transient variable?','Medium','0','not be serialized during Serialization',3,0),(219,'What is synchronization?','Medium','0','capability to control the access of multiple threads',3,0),(220,'Does garbage collection guarantee that a program will not run out of memory?','Medium','0','No',3,0),(221,'What is the purpose of the System class?','Medium','0','provide access to system resources',3,0),(222,'Under what conditions is an object\'s finalize() method invoked by the garbage collector?','Hard','0','when it detects that the object has become unreachable',3,0),(223,'How can a dead thread be restarted?','Hard','0','not possible',3,0),(224,'What are ClassLoaders?','Hard','0','object that is responsible for loading classes',3,0),(225,'Why do we need wrapper classes?','Hard','0','provides utility methods',3,0),(226,'What is the difference between error and an exception?','Hard','0','error is an irrecoverable',3,0),(227,'What are the advantages of ArrayList over arrays?','Easy','1 to 5 ','involves only updating the next pointer',1,0),(228,'How do you decide when to use ArrayList and LinkedList?','Easy','1 to 5 ','2 to 3 differences',1,0),(229,'What is a Values Collection View ?','Easy','1 to 5 ','What is a Values Collection View ?',1,0),(230,'Where and how can you use a private constructor?','Easy','1 to 5 ','do not want other classes to instantiate',1,0),(231,'Describe life cycle of thread?','Easy','1 to 5 ','5 states',1,0),(232,'What is the difference between the >> and >>> operators?','Medium','1 to 5 ','zero-fills bits',1,0),(233,'Which method of the Component class is used to set the position and size of a component?','Medium','1 to 5 ','setBounds()',1,0),(234,'What is the range of the short type?','Medium','1 to 5 ','(2^15) to 2^15 - 1',1,0),(235,'What is the immediate superclass of Menu?','Medium','1 to 5 ','MenuItem class',1,0),(236,'Does Java allow Default Arguments?','Medium','1 to 5 ','No',1,0),(237,'Which number is denoted by leading zero in java?','Hard','1 to 5 ','Octal Numbers',1,0),(238,'Explain suspend() method under Thread class>','Hard','1 to 5 ','to pause or temporarily stop the execution',1,0),(239,'Explain main thread under Thread class execution?','Hard','1 to 5 ','created automatically and it begins to execute immediately when a program starts',1,0),(240,'Define code as an attribute of Applet?','Hard','1 to 5 ','specify the name of the applet class',1,0),(241,'Define Network Programming?','Hard','1 to 5 ','writing programs that execute across multiple devices',1,0),(242,'What is a Socket?','Easy','1 to 5 ','communication mechanism between two computers using TCP',2,0),(243,'Advantages of Java Sockets?','Easy','1 to 5 ','flexible and sufficient',2,0),(244,'Which class is used by server applications to obtain a port and listen for client requests?','Easy','1 to 5 ','java.net.ServerSocket',2,0),(245,'Why Generics are used in Java?','Easy','1 to 5 ','provide compile-time type safety',2,0),(246,'What is Nested top-level class?','Easy','1 to 5 ','a class is declared within a class',2,0),(247,'If System.exit (0); is written at the end of the try block  will the finally block still execute?','Medium','1 to 5 ','No',2,0),(248,'What is daemon thread?','Medium','1 to 5 ','low priority thread',2,0),(249,'Which method is used to create the daemon thread?','Medium','1 to 5 ','setDaemon method',2,0),(250,'What is the SimpleTimeZone class?','Medium','1 to 5 ','provides support for a Gregorian calendar',2,0),(251,'Can a vector contain heterogenous objects?','Medium','1 to 5 ','Yes a Vector can contain heterogenous objects',2,0),(252,'What is difference between Path and Classpath?','Hard','1 to 5 ','operating system level environment variales',2,0),(253,'Can a class declared as private be accessed outside it\'s package?','Hard','1 to 5 ','No',2,0),(254,'Can an Interface extend another Interface?','Hard','1 to 5 ','yes',2,0),(255,'What is an object\'s lock and which object\'s have locks?','Hard','1 to 5 ','used by multiple threads to obtain synchronized access to the object',2,0),(256,'If a method is declared as protected  where may the method be accessed?','Hard','1 to 5 ','only be accessed by classes or interfaces of the same package',2,0),(257,'What is an Iterator?','Easy','1 to 5 ','provide traversal',3,0),(258,'What if the main method is declared as private?','Easy','1 to 5 ','compiles properly but at runtime it will give  Main method not public.  message',3,0),(259,'What if I do not provide the String array as the argument to the method','Easy','1 to 5 ','runtime error  NoSuchMethodError ',3,0),(260,'What is the first argument of the String array in main method','Easy','1 to 5 ','The String array is empty',3,0),(261,'If I do not provide any arguments on the command line  then the String array of Main method will be empty or null?','Easy','1 to 5 ','It is empty. But not null.',3,0),(262,'Can an application have multiple classes having main method?','Medium','1 to 5 ','Yes it is possible.',3,0),(263,'What are Checked and UnChecked Exception?','Medium','1 to 5 ','Exception',3,0),(264,'What are different types of inner classes?','Medium','1 to 5 ','3 to 4 types',3,0),(265,'Does importing a package imports the subpackages as well?','Medium','1 to 5 ','No you will have to import the subpackages explicitly',3,0),(266,'Which methods of Serializable interface should I implement?','Medium','1 to 5 ','The serializable interface is an empty interface',3,0),(267,'What one should take care of while serializing the object?','Hard','1 to 5 ','One should make sure that all the included objects are also serializable',3,0),(268,'What happens to the static fields of a class during serialization?','Hard','1 to 5 ','Serialization ignores static fields',3,0),(269,'How are Observer and Observable used?','Hard','1 to 5 ','Objects that subclass the Observable class maintain a list of observers',3,0),(270,'How does Java handle integer overflows and underflows?','Hard','1 to 5 ','It uses those low order bytes of the result that can fit into the size of the type allowed by the operation',3,0),(271,'If I write return at the end of the try block  will the finally block still execute?','Hard','1 to 5 ','Yes',3,0),(272,'What are J2EE Patterns?','Easy','5+','These patterns are identified by Sun Java Center',1,0),(273,'What is Factory pattern?','Easy','5+','e create object without exposing the creation logic to the client',1,0),(274,'How can you create Singleton class in java?','Easy','5+','two step process',1,0),(275,'What are the difference between a static class and a singleton class?','Easy','5+','2 to 3 differences',1,0),(276,'Can we create a clone of a singleton object?','Easy','5+','yes',1,0),(277,'How to prevent cloning of a singleton object?','Medium','5+','Throw exception within the body of clone()',1,0),(278,'What is Adapter pattern?','Medium','5+','works as a bridge between two incompatible interfaces',1,0),(279,'What are the entities of Service Locator pattern?','Medium','5+','example with explanation',1,0),(280,'Name the actor classes used in Memento pattern.','Medium','5+','three actor classes',1,0),(281,'What is Strategy pattern?','Medium','5+','class behavior or its algorithm can be changed at run time',1,0),(282,'What is MVC pattern?','Hard','5+','pattern is used to separate application\'s concerns',1,0),(283,'What is Data Access Object Pattern(DAO) pattern?','Hard','5+','used to separate low level data accessing API or operations from high level business services',1,0),(284,'What is Visitor pattern?','Hard','5+','we use a visitor class which changes the executing algorithm of an element class.',1,0),(285,'Give an example where Interpreter pattern is used?','Hard','5+','This pattern is used in SQL parsing',1,0),(286,'What is Proxy pattern?','Hard','5+','a class represents functionality of another class',1,0),(287,'What is Spring?','Easy','5+','open source development framework for enterprise Java',2,0),(288,'What is Spring configuration file?','Easy','5+','contains the classes information and describes how these classes are configured and introduced to each other',2,0),(289,'What is Dependency Injection?','Easy','5+','Inversion of Control (IoC)',2,0),(290,'What are the different types of IoC (dependency injection)?','Easy','5+','2 types',2,0),(291,'What is AOP?','Easy','5+','to modularize crosscutting concerns',2,0),(292,'Give an example of BeanFactory implementation.','Medium','5+','XmlBeanFactoryclass',2,0),(293,'What is the difference between Bean Factory and ApplicationContext?','Medium','5+','2 to 3 differences',2,0),(294,'What does a bean definition contain?','Medium','5+','configuration metadata',2,0),(295,'How do add a bean in spring application?','Medium','5+','code or annotation',2,0),(296,'What bean scopes does Spring support? Explain them.','Medium','5+','five scopes',2,0),(297,'Are Singleton beans thread safe in Spring Framework?','Hard','5+','no',2,0),(298,'What is bean auto wiring?','Hard','5+','able to autowire relationships between collaborating beans',2,0),(299,'How is event handling done in Spring?','Hard','5+','ApplicationContext',2,0),(300,'How JdbcTemplate can be used?','Hard','5+','provides many convenience',2,0),(301,'Explain the@Controllerannotation.','Hard','5+','indicates that a particular class serves the role of a controller.',2,0),(302,'What is the difference between SQL and MySQL or SQL Server?','Easy','5+','proper difference with expanation',3,0),(303,'What are various DDL commands in SQL? Give brief description of their purposes.','Easy','5+','various DDL or Data Definition Language commands',3,0),(304,'Is a NULL value same as zero or a blank space? If not then what is the difference?','Easy','5+','A NULL value is not same as zero or a blank space',3,0),(305,'How can you eliminate duplicate rows from a query result?','Easy','5+','DISTINCT keyword',3,0),(306,'What are the specific uses of SQL functions?','Easy','5+','4 to 5 uses',3,0),(307,'What is the use of the NULLIF function?','Medium','5+','compares two expressions',3,0),(308,'Discuss the syntax and use of the COALESCE function?','Medium','5+','returns the first non-null expression given in the parameter list',3,0),(309,'What do you understand by a subquery? When is it used?','Medium','5+','a SELECT statement embedded in a clause of another SELECT statement',3,0),(310,'How do you copy rows from one table to another?','Medium','5+','a subquery is used in the place of the VALUES clause.',3,0),(311,'Can you modify the rows in a table based on values from another table?','Medium','5+','Yes.',3,0),(312,'What happens if you omit the WHERE clause in a delete statement?','Hard','5+','All the rows in the table are deleted.',3,0),(313,'All the rows in the table are deleted.','Hard','5+','subqueries can be used',3,0),(314,'What is the difference between VARCHAR2 AND CHAR datatypes?','Hard','5+','length character data',3,0),(315,'What is a view? Why should you use a view?','Hard','5+','view is a logical snapshot based on a table or another view.',3,0),(316,'Say True or False. Give explanation if False.All group functions ignore null values.','Hard','5+','True.',3,0);
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions_asked`
--

DROP TABLE IF EXISTS `questions_asked`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions_asked` (
  `question_id` int(11) DEFAULT NULL,
  `candidate_id` int(11) DEFAULT NULL,
  `score` float(4,2) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `round_id` int(11) DEFAULT NULL,
  `asked_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`asked_id`),
  KEY `question_id` (`question_id`),
  KEY `candidate_id` (`candidate_id`),
  KEY `round_id` (`round_id`),
  CONSTRAINT `questions_asked_ibfk_1` FOREIGN KEY (`question_id`) REFERENCES `questions` (`question_id`),
  CONSTRAINT `questions_asked_ibfk_2` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`),
  CONSTRAINT `questions_asked_ibfk_3` FOREIGN KEY (`round_id`) REFERENCES `rounds` (`round_number`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_asked`
--

LOCK TABLES `questions_asked` WRITE;
/*!40000 ALTER TABLE `questions_asked` DISABLE KEYS */;
INSERT INTO `questions_asked` VALUES (141,1,0.00,'wrong',1,1),(140,1,0.00,'wrong',1,2);
/*!40000 ALTER TABLE `questions_asked` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rounds`
--

DROP TABLE IF EXISTS `rounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rounds` (
  `round_number` int(11) NOT NULL,
  PRIMARY KEY (`round_number`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rounds`
--

LOCK TABLES `rounds` WRITE;
/*!40000 ALTER TABLE `rounds` DISABLE KEYS */;
INSERT INTO `rounds` VALUES (0),(1),(2),(3);
/*!40000 ALTER TABLE `rounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_attributes`
--

DROP TABLE IF EXISTS `system_attributes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system_attributes` (
  `no_of_rounds` int(11) DEFAULT NULL,
  `minimum_questions` int(11) DEFAULT NULL,
  `threshold` int(11) DEFAULT NULL,
  `system_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attributes`
--

LOCK TABLES `system_attributes` WRITE;
/*!40000 ALTER TABLE `system_attributes` DISABLE KEYS */;
INSERT INTO `system_attributes` VALUES (3,2,7,1);
/*!40000 ALTER TABLE `system_attributes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) DEFAULT NULL,
  `last_name` varchar(20) DEFAULT NULL,
  `is_admin` int(11) DEFAULT NULL,
  `round_number` int(11) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  KEY `round_number` (`round_number`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`round_number`) REFERENCES `rounds` (`round_number`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Shaurya','Shekhar',1,0,'shaurya@email.com','9876543210'),(2,'Hashmeet','Chadha',0,2,'hashmeet@email.com','9885473625'),(3,'Rupesh','Singh',0,1,'rupesh@email.com','9843672356');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-07-22 19:23:51
