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
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidate`
--

LOCK TABLES `candidate` WRITE;
/*!40000 ALTER TABLE `candidate` DISABLE KEYS */;
INSERT INTO `candidate` VALUES (1,'Komal','Naik','komal@email.com',0.00,'PENDING',NULL,'5+'),(2,'Akash','Kadel','akash@email.com',0.00,'PENDING',NULL,'5+'),(3,'Chetan','Kalode','chetan@email.com',0.00,'PENDING',NULL,'5+'),(4,'Rushabh','Kapadia','rushabh@email.com',0.00,'PENDING',NULL,'5+'),(6,'Ninad','xyz','ninda@xyz.com',0.00,'PENDING','C:\\fakepath\\demo.csv','1-5'),(7,'heenu','sheenu','heenu@sheenu.com',0.00,'PENDING','C:\\fakepath\\demo.csv','1-5'),(8,'saurabh','begnani','begnani@email.com',6.22,'REJECTED','C:\\fakepath\\demo.csv','1-5'),(9,'darshan','dontknow','darshan@email.com',9.00,'SELECTED','C:\\fakepath\\demo.csv','0'),(10,'sahay','xyz','sahay@gmail.com',0.00,'PENDING','C:\\fakepath\\demo.csv','5+'),(11,'guru','murti','guru@email.com',5.85,'REJECTED','C:\\fakepath\\demo.csv','5+'),(12,'hsrshal','shah','shah@email.com',0.00,'PENDING','C:\\fakepath\\demo.csv','1-5'),(13,'hashmeet','chadha','hashmeet@email.com',6.00,'REJECTED','fake','1-5');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
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
INSERT INTO `login` VALUES (15,'shaurya@email.com','WISSEN'),(16,'komal@email.com','WISSEN'),(17,'rupesh@email.com','WISSEN');
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
  `difficulty` varchar(25) NOT NULL,
  `experience` varchar(10) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  `round_number` int(11) DEFAULT NULL,
  `is_approved` int(11) DEFAULT NULL,
  PRIMARY KEY (`question_id`),
  KEY `round_number` (`round_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1137 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (729,'What do you mean by Object?','EASY','0','runtime entity',1,1),(730,'Define class?','EASY','0','runtime entity and its state',1,1),(731,'What is Singleton class?','EASY','0','control object creation',1,1),(732,'What is a static variable?','EASY','0','Class variables',1,1),(733,'What is the default value of float and double datatype in Java?','EASY','0','0.0f and 0.0d',1,1),(734,'What do you mean by Access Modifier?','MEDIUM','0','set access levels',1,1),(735,'Why is String class considered immutable?','MEDIUM','0','object cannot be changed',1,1),(736,'Why is StringBuffer called mutable?','MEDIUM','0','modifications to Strings of characters then StringBuffer',1,1),(737,'What is the difference between StringBuffer and StringBuilder class?','MEDIUM','0','StringBuilder is faster than StringBuffer',1,1),(738,'Which package is used for pattern matching with regular expressions?','MEDIUM','0','sample comment',1,1),(739,'What do you mean by Multithreaded program?','HARD','0','run concurrently',1,1),(740,'Explain garbage collection in Java?','HARD','0','free the memory',1,1),(741,'Explain the usage of this() with constructors?','HARD','0','call constructer of same class',1,1),(742,'Difference between throw and throws?','HARD','0','Throw is used to trigger an exception where as throws is used in declaration of exception',1,1),(743,'Can a constructor be made final?','HARD','0','No',1,1),(744,'What is static block?','EASY','0','used to initialize the static data member',2,1),(745,'Define composition?','EASY','0','Holding the reference of the other class',2,1),(746,'What is function overloading?','EASY','0','multiple functions by same name but different parameters',2,1),(747,'What is function overriding?','EASY','0','subclass provides a specific implementation of a method',2,1),(748,'Difference between Overloading and Overriding?','EASY','0','2 to 3 differences',2,1),(749,'What is NullPointerException?','MEDIUM','0','instance method of a null object',2,1),(750,'What are the ways in which a thread can enter the waiting state?','MEDIUM','0',' sleep() and wait()',2,1),(751,'How does multi-threading take place on a computer with a single CPU?','MEDIUM','0','operating system\'s task scheduler',2,1),(752,'What invokes a thread\'s run() method?','MEDIUM','0','when the thread is initially executed',2,1),(753,'Does it matter in what order catch statements for FileNotFoundException and IOException are written?','MEDIUM','0','Yes it does',2,1),(754,'What is the difference between yielding and sleeping?','HARD','0','ready and waiting state',2,1),(755,'What are Wrapper classes?','HARD','0','allow primitive types to be accessed as objects',2,1),(756,'Which package has light weight components?','HARD','0','javax.Swing package',2,1),(757,'What is the difference between the paint() and repaint() methods?','HARD','0','Graphics object',2,1),(758,'What is the purpose of File class?','HARD','0','provide access to the files and directories',2,1),(759,'What is Serialization and deserialization?','EASY','0','writing the state of an object',3,1),(760,'What are use cases?','EASY','0','describes a situation that a program might encounter',3,1),(761,'What is the difference between Swing and AWT components?','EASY','0','AWT components are heavy-weight whereas Swing components are lightweight.',3,1),(762,'Is there any limitation of using Inheritance?','EASY','0','makes the subclass too clustering and sometimes error-prone',3,1),(763,'When is the ArrayStoreException thrown?','EASY','0','When copying elements between different arrays',3,1),(764,'Can you call one constructor from another if a class has multiple constructors?','MEDIUM','0','Yes use this() syntax.',3,1),(765,'What is a transient variable?','MEDIUM','0','not be serialized during Serialization',3,1),(766,'What is synchronization?','MEDIUM','0','capability to control the access of multiple threads',3,1),(767,'Does garbage collection guarantee that a program will not run out of memory?','MEDIUM','0','No',3,1),(768,'What is the purpose of the System class?','MEDIUM','0','provide access to system resources',3,1),(769,'Under what conditions is an object\'s finalize() method invoked by the garbage collector?','HARD','0','when it detects that the object has become unreachable',3,1),(770,'How can a dead thread be restarted?','HARD','0','not possible',3,1),(771,'What are ClassLoaders?','HARD','0','object that is responsible for loading classes',3,1),(772,'Why do we need wrapper classes?','HARD','0','provides utility methods',3,1),(773,'What is the difference between error and an exception?','HARD','0','error is an irrecoverable',3,1),(774,'What are the advantages of ArrayList over arrays?','EASY','1-5','involves only updating the next pointer',1,1),(775,'How do you decide when to use ArrayList and LinkedList?','EASY','1-5','2 to 3 differences',1,1),(776,'What is a Values Collection View ?','EASY','1-5','What is a Values Collection View ?',1,1),(777,'Where and how can you use a private constructor?','EASY','1-5','do not want other classes to instantiate',1,1),(778,'Describe life cycle of thread?','EASY','1-5','5 states',1,1),(779,'What is the difference between the >> and >>> operators?','MEDIUM','1-5','zero-fills bits',1,1),(780,'Which method of the Component class is used to set the position and size of a component?','MEDIUM','1-5','setBounds()',1,1),(781,'What is the range of the short type?','MEDIUM','1-5','(2^15) to 2^15 - 1',1,1),(782,'What is the immediate superclass of Menu?','MEDIUM','1-5','MenuItem class',1,1),(783,'Does Java allow Default Arguments?','MEDIUM','1-5','No',1,1),(784,'Which number is denoted by leading zero in java?','HARD','1-5','Octal Numbers',1,1),(785,'Explain suspend() method under Thread class>','HARD','1-5','to pause or temporarily stop the execution',1,1),(786,'Explain main thread under Thread class execution?','HARD','1-5','created automatically and it begins to execute immediately when a program starts',1,1),(787,'Define code as an attribute of Applet?','HARD','1-5','specify the name of the applet class',1,1),(788,'Define Network Programming?','HARD','1-5','writing programs that execute across multiple devices',1,1),(789,'What is a Socket?','EASY','1-5','communication mechanism between two computers using TCP',2,1),(790,'Advantages of Java Sockets?','EASY','1-5','flexible and sufficient',2,1),(791,'Which class is used by server applications to obtain a port and listen for client requests?','EASY','1-5','java.net.ServerSocket',2,1),(792,'Why Generics are used in Java?','EASY','1-5','provide compile-time type safety',2,1),(793,'What is Nested top-level class?','EASY','1-5','a class is declared within a class',2,1),(794,'If System.exit (0); is written at the end of the try block  will the finally block still execute?','MEDIUM','1-5','No',2,1),(795,'What is daemon thread?','MEDIUM','1-5','low priority thread',2,1),(796,'Which method is used to create the daemon thread?','MEDIUM','1-5','setDaemon method',2,1),(797,'What is the SimpleTimeZone class?','MEDIUM','1-5','provides support for a Gregorian calendar',2,1),(798,'Can a vector contain heterogenous objects?','MEDIUM','1-5','Yes a Vector can contain heterogenous objects',2,1),(799,'What is difference between Path and Classpath?','HARD','1-5','operating system level environment variales',2,1),(800,'Can a class declared as private be accessed outside it\'s package?','HARD','1-5','No',2,1),(801,'Can an Interface extend another Interface?','HARD','1-5','yes',2,1),(802,'What is an object\'s lock and which object\'s have locks?','HARD','1-5','used by multiple threads to obtain synchronized access to the object',2,1),(803,'If a method is declared as protected  where may the method be accessed?','HARD','1-5','only be accessed by classes or interfaces of the same package',2,1),(804,'What is an Iterator?','EASY','1-5','provide traversal',3,1),(805,'What if the main method is declared as private?','EASY','1-5','compiles properly but at runtime it will give  Main method not public.  message',3,1),(806,'What if I do not provide the String array as the argument to the method','EASY','1-5','runtime error  NoSuchMethodError ',3,1),(807,'What is the first argument of the String array in main method','EASY','1-5','The String array is empty',3,1),(808,'If I do not provide any arguments on the command line  then the String array of Main method will be empty or null?','EASY','1-5','It is empty. But not null.',3,1),(809,'Can an application have multiple classes having main method?','MEDIUM','1-5','Yes it is possible.',3,1),(810,'What are Checked and UnChecked Exception?','MEDIUM','1-5','Exception',3,1),(811,'What are different types of inner classes?','MEDIUM','1-5','3 to 4 types',3,1),(812,'Does importing a package imports the subpackages as well?','MEDIUM','1-5','No you will have to import the subpackages explicitly',3,1),(813,'Which methods of Serializable interface should I implement?','MEDIUM','1-5','The serializable interface is an empty interface',3,1),(814,'What one should take care of while serializing the object?','HARD','1-5','One should make sure that all the included objects are also serializable',3,1),(815,'What happens to the static fields of a class during serialization?','HARD','1-5','Serialization ignores static fields',3,1),(816,'How are Observer and Observable used?','HARD','1-5','Objects that subclass the Observable class maintain a list of observers',3,1),(817,'How does Java handle integer overflows and underflows?','HARD','1-5','It uses those low order bytes of the result that can fit into the size of the type allowed by the operation',3,1),(818,'If I write return at the end of the try block  will the finally block still execute?','HARD','1-5','Yes',3,1),(819,'What are J2EE Patterns?','EASY','5+','These patterns are identified by Sun Java Center',1,1),(820,'What is Factory pattern?','EASY','5+','e create object without exposing the creation logic to the client',1,1),(821,'How can you create Singleton class in java?','EASY','5+','two step process',1,1),(822,'What are the difference between a static class and a singleton class?','EASY','5+','2 to 3 differences',1,1),(823,'Can we create a clone of a singleton object?','EASY','5+','yes',1,1),(824,'How to prevent cloning of a singleton object?','MEDIUM','5+','Throw exception within the body of clone()',1,1),(825,'What is Adapter pattern?','MEDIUM','5+','works as a bridge between two incompatible interfaces',1,1),(826,'What are the entities of Service Locator pattern?','MEDIUM','5+','example with explanation',1,1),(827,'Name the actor classes used in Memento pattern.','MEDIUM','5+','three actor classes',1,1),(828,'What is Strategy pattern?','MEDIUM','5+','class behavior or its algorithm can be changed at run time',1,1),(829,'What is MVC pattern?','HARD','5+','pattern is used to separate application\'s concerns',1,1),(830,'What is Data Access Object Pattern(DAO) pattern?','HARD','5+','used to separate low level data accessing API or operations from high level business services',1,1),(831,'What is Visitor pattern?','HARD','5+','we use a visitor class which changes the executing algorithm of an element class.',1,1),(832,'Give an example where Interpreter pattern is used?','HARD','5+','This pattern is used in SQL parsing',1,1),(833,'What is Proxy pattern?','HARD','5+','a class represents functionality of another class',1,1),(834,'What is Spring?','EASY','5+','open source development framework for enterprise Java',2,1),(835,'What is Spring configuration file?','EASY','5+','contains the classes information and describes how these classes are configured and introduced to each other',2,1),(836,'What is Dependency Injection?','EASY','5+','Inversion of Control (IoC)',2,1),(837,'What are the different types of IoC (dependency injection)?','EASY','5+','2 types',2,1),(838,'What is AOP?','EASY','5+','to modularize crosscutting concerns',2,1),(839,'Give an example of BeanFactory implementation.','MEDIUM','5+','XmlBeanFactoryclass',2,1),(840,'What is the difference between Bean Factory and ApplicationContext?','MEDIUM','5+','2 to 3 differences',2,1),(841,'What does a bean definition contain?','MEDIUM','5+','configuration metadata',2,1),(842,'How do add a bean in spring application?','MEDIUM','5+','code or annotation',2,1),(843,'What bean scopes does Spring support? Explain them.','MEDIUM','5+','five scopes',2,1),(844,'Are Singleton beans thread safe in Spring Framework?','HARD','5+','no',2,1),(845,'What is bean auto wiring?','HARD','5+','able to autowire relationships between collaborating beans',2,1),(846,'How is event handling done in Spring?','HARD','5+','ApplicationContext',2,1),(847,'How JdbcTemplate can be used?','HARD','5+','provides many convenience',2,1),(848,'Explain the@Controllerannotation.','HARD','5+','indicates that a particular class serves the role of a controller.',2,1),(849,'What is the difference between SQL and MySQL or SQL Server?','EASY','5+','proper difference with expanation',3,1),(850,'What are various DDL commands in SQL? Give brief description of their purposes.','EASY','5+','various DDL or Data Definition Language commands',3,1),(851,'Is a NULL value same as zero or a blank space? If not then what is the difference?','EASY','5+','A NULL value is not same as zero or a blank space',3,1),(852,'How can you eliminate duplicate rows from a query result?','EASY','5+','DISTINCT keyword',3,1),(853,'What are the specific uses of SQL functions?','EASY','5+','4 to 5 uses',3,1),(854,'What is the use of the NULLIF function?','MEDIUM','5+','compares two expressions',3,1),(855,'Discuss the syntax and use of the COALESCE function?','MEDIUM','5+','returns the first non-null expression given in the parameter list',3,1),(856,'What do you understand by a subquery? When is it used?','MEDIUM','5+','a SELECT statement embedded in a clause of another SELECT statement',3,1),(857,'How do you copy rows from one table to another?','MEDIUM','5+','a subquery is used in the place of the VALUES clause.',3,1),(858,'Can you modify the rows in a table based on values from another table?','MEDIUM','5+','Yes.',3,1),(859,'What happens if you omit the WHERE clause in a delete statement?','HARD','5+','All the rows in the table are deleted.',3,1),(860,'All the rows in the table are deleted.','HARD','5+','subqueries can be used',3,1),(861,'What is the difference between VARCHAR2 AND CHAR datatypes?','HARD','5+','length character data',3,1),(862,'What is a view? Why should you use a view?','HARD','5+','view is a logical snapshot based on a table or another view.',3,1),(863,'Say True or False. Give explanation if False.All group functions ignore null values.','HARD','5+','True.',3,1),(864,'What do you mean by Object?','EASY','0','runtime entity',1,1),(865,'Define class?','EASY','0','runtime entity and its state',1,1),(866,'What is Singleton class?','EASY','0','control object creation',1,1),(867,'What is a static variable?','EASY','0','Class variables',1,1),(868,'What is the default value of float and double datatype in Java?','EASY','0','0.0f and 0.0d',1,1),(869,'What do you mean by Access Modifier?','MEDIUM','0','set access levels',1,1),(870,'Why is String class considered immutable?','MEDIUM','0','object cannot be changed',1,1),(871,'Why is StringBuffer called mutable?','MEDIUM','0','modifications to Strings of characters then StringBuffer',1,1),(872,'What is the difference between StringBuffer and StringBuilder class?','MEDIUM','0','StringBuilder is faster than StringBuffer',1,1),(873,'Which package is used for pattern matching with regular expressions?','MEDIUM','0','sample comment',1,1),(874,'What do you mean by Multithreaded program?','HARD','0','run concurrently',1,1),(875,'Explain garbage collection in Java?','HARD','0','free the memory',1,1),(876,'Explain the usage of this() with constructors?','HARD','0','call constructer of same class',1,1),(877,'Difference between throw and throws?','HARD','0','Throw is used to trigger an exception where as throws is used in declaration of exception',1,1),(878,'Can a constructor be made final?','HARD','0','No',1,1),(879,'What is static block?','EASY','0','used to initialize the static data member',2,1),(880,'Define composition?','EASY','0','Holding the reference of the other class',2,1),(881,'What is function overloading?','EASY','0','multiple functions by same name but different parameters',2,1),(882,'What is function overriding?','EASY','0','subclass provides a specific implementation of a method',2,1),(883,'Difference between Overloading and Overriding?','EASY','0','2 to 3 differences',2,1),(884,'What is NullPointerException?','MEDIUM','0','instance method of a null object',2,1),(885,'What are the ways in which a thread can enter the waiting state?','MEDIUM','0',' sleep() and wait()',2,1),(886,'How does multi-threading take place on a computer with a single CPU?','MEDIUM','0','operating system\'s task scheduler',2,1),(887,'What invokes a thread\'s run() method?','MEDIUM','0','when the thread is initially executed',2,1),(888,'Does it matter in what order catch statements for FileNotFoundException and IOException are written?','MEDIUM','0','Yes it does',2,1),(889,'What is the difference between yielding and sleeping?','HARD','0','ready and waiting state',2,1),(890,'What are Wrapper classes?','HARD','0','allow primitive types to be accessed as objects',2,1),(891,'Which package has light weight components?','HARD','0','javax.Swing package',2,1),(892,'What is the difference between the paint() and repaint() methods?','HARD','0','Graphics object',2,1),(893,'What is the purpose of File class?','HARD','0','provide access to the files and directories',2,1),(894,'What is Serialization and deserialization?','EASY','0','writing the state of an object',3,1),(895,'What are use cases?','EASY','0','describes a situation that a program might encounter',3,1),(896,'What is the difference between Swing and AWT components?','EASY','0','AWT components are heavy-weight whereas Swing components are lightweight.',3,1),(897,'Is there any limitation of using Inheritance?','EASY','0','makes the subclass too clustering and sometimes error-prone',3,1),(898,'When is the ArrayStoreException thrown?','EASY','0','When copying elements between different arrays',3,1),(899,'Can you call one constructor from another if a class has multiple constructors?','MEDIUM','0','Yes use this() syntax.',3,1),(900,'What is a transient variable?','MEDIUM','0','not be serialized during Serialization',3,1),(901,'What is synchronization?','MEDIUM','0','capability to control the access of multiple threads',3,1),(902,'Does garbage collection guarantee that a program will not run out of memory?','MEDIUM','0','No',3,1),(903,'What is the purpose of the System class?','MEDIUM','0','provide access to system resources',3,1),(904,'Under what conditions is an object\'s finalize() method invoked by the garbage collector?','HARD','0','when it detects that the object has become unreachable',3,1),(905,'How can a dead thread be restarted?','HARD','0','not possible',3,1),(906,'What are ClassLoaders?','HARD','0','object that is responsible for loading classes',3,1),(907,'Why do we need wrapper classes?','HARD','0','provides utility methods',3,1),(908,'What is the difference between error and an exception?','HARD','0','error is an irrecoverable',3,1),(909,'What are the advantages of ArrayList over arrays?','EASY','1-5','involves only updating the next pointer',1,1),(910,'How do you decide when to use ArrayList and LinkedList?','EASY','1-5','2 to 3 differences',1,1),(911,'What is a Values Collection View ?','EASY','1-5','What is a Values Collection View ?',1,1),(912,'Where and how can you use a private constructor?','EASY','1-5','do not want other classes to instantiate',1,1),(913,'Describe life cycle of thread?','EASY','1-5','5 states',1,1),(914,'What is the difference between the >> and >>> operators?','MEDIUM','1-5','zero-fills bits',1,1),(915,'Which method of the Component class is used to set the position and size of a component?','MEDIUM','1-5','setBounds()',1,1),(916,'What is the range of the short type?','MEDIUM','1-5','(2^15) to 2^15 - 1',1,1),(917,'What is the immediate superclass of Menu?','MEDIUM','1-5','MenuItem class',1,1),(918,'Does Java allow Default Arguments?','MEDIUM','1-5','No',1,1),(919,'Which number is denoted by leading zero in java?','HARD','1-5','Octal Numbers',1,1),(920,'Explain suspend() method under Thread class>','HARD','1-5','to pause or temporarily stop the execution',1,1),(921,'Explain main thread under Thread class execution?','HARD','1-5','created automatically and it begins to execute immediately when a program starts',1,1),(922,'Define code as an attribute of Applet?','HARD','1-5','specify the name of the applet class',1,1),(923,'Define Network Programming?','HARD','1-5','writing programs that execute across multiple devices',1,1),(924,'What is a Socket?','EASY','1-5','communication mechanism between two computers using TCP',2,1),(925,'Advantages of Java Sockets?','EASY','1-5','flexible and sufficient',2,1),(926,'Which class is used by server applications to obtain a port and listen for client requests?','EASY','1-5','java.net.ServerSocket',2,1),(927,'Why Generics are used in Java?','EASY','1-5','provide compile-time type safety',2,1),(928,'What is Nested top-level class?','EASY','1-5','a class is declared within a class',2,1),(929,'If System.exit (0); is written at the end of the try block  will the finally block still execute?','MEDIUM','1-5','No',2,1),(930,'What is daemon thread?','MEDIUM','1-5','low priority thread',2,1),(931,'Which method is used to create the daemon thread?','MEDIUM','1-5','setDaemon method',2,1),(932,'What is the SimpleTimeZone class?','MEDIUM','1-5','provides support for a Gregorian calendar',2,1),(933,'Can a vector contain heterogenous objects?','MEDIUM','1-5','Yes a Vector can contain heterogenous objects',2,1),(934,'What is difference between Path and Classpath?','HARD','1-5','operating system level environment variales',2,1),(935,'Can a class declared as private be accessed outside it\'s package?','HARD','1-5','No',2,1),(936,'Can an Interface extend another Interface?','HARD','1-5','yes',2,1),(937,'What is an object\'s lock and which object\'s have locks?','HARD','1-5','used by multiple threads to obtain synchronized access to the object',2,1),(938,'If a method is declared as protected  where may the method be accessed?','HARD','1-5','only be accessed by classes or interfaces of the same package',2,1),(939,'What is an Iterator?','EASY','1-5','provide traversal',3,1),(940,'What if the main method is declared as private?','EASY','1-5','compiles properly but at runtime it will give  Main method not public.  message',3,1),(941,'What if I do not provide the String array as the argument to the method','EASY','1-5','runtime error  NoSuchMethodError ',3,1),(942,'What is the first argument of the String array in main method','EASY','1-5','The String array is empty',3,1),(943,'If I do not provide any arguments on the command line  then the String array of Main method will be empty or null?','EASY','1-5','It is empty. But not null.',3,1),(944,'Can an application have multiple classes having main method?','MEDIUM','1-5','Yes it is possible.',3,1),(945,'What are Checked and UnChecked Exception?','MEDIUM','1-5','Exception',3,1),(946,'What are different types of inner classes?','MEDIUM','1-5','3 to 4 types',3,1),(947,'Does importing a package imports the subpackages as well?','MEDIUM','1-5','No you will have to import the subpackages explicitly',3,1),(948,'Which methods of Serializable interface should I implement?','MEDIUM','1-5','The serializable interface is an empty interface',3,1),(949,'What one should take care of while serializing the object?','HARD','1-5','One should make sure that all the included objects are also serializable',3,1),(950,'What happens to the static fields of a class during serialization?','HARD','1-5','Serialization ignores static fields',3,1),(951,'How are Observer and Observable used?','HARD','1-5','Objects that subclass the Observable class maintain a list of observers',3,1),(952,'How does Java handle integer overflows and underflows?','HARD','1-5','It uses those low order bytes of the result that can fit into the size of the type allowed by the operation',3,1),(953,'If I write return at the end of the try block  will the finally block still execute?','HARD','1-5','Yes',3,1),(954,'What are J2EE Patterns?','EASY','5+','These patterns are identified by Sun Java Center',1,1),(955,'What is Factory pattern?','EASY','5+','e create object without exposing the creation logic to the client',1,1),(956,'How can you create Singleton class in java?','EASY','5+','two step process',1,1),(957,'What are the difference between a static class and a singleton class?','EASY','5+','2 to 3 differences',1,1),(958,'Can we create a clone of a singleton object?','EASY','5+','yes',1,1),(959,'How to prevent cloning of a singleton object?','MEDIUM','5+','Throw exception within the body of clone()',1,1),(960,'What is Adapter pattern?','MEDIUM','5+','works as a bridge between two incompatible interfaces',1,1),(961,'What are the entities of Service Locator pattern?','MEDIUM','5+','example with explanation',1,1),(962,'Name the actor classes used in Memento pattern.','MEDIUM','5+','three actor classes',1,1),(963,'What is Strategy pattern?','MEDIUM','5+','class behavior or its algorithm can be changed at run time',1,1),(964,'What is MVC pattern?','HARD','5+','pattern is used to separate application\'s concerns',1,1),(965,'What is Data Access Object Pattern(DAO) pattern?','HARD','5+','used to separate low level data accessing API or operations from high level business services',1,1),(966,'What is Visitor pattern?','HARD','5+','we use a visitor class which changes the executing algorithm of an element class.',1,1),(967,'Give an example where Interpreter pattern is used?','HARD','5+','This pattern is used in SQL parsing',1,1),(968,'What is Proxy pattern?','HARD','5+','a class represents functionality of another class',1,1),(969,'What is Spring?','EASY','5+','open source development framework for enterprise Java',2,1),(970,'What is Spring configuration file?','EASY','5+','contains the classes information and describes how these classes are configured and introduced to each other',2,1),(971,'What is Dependency Injection?','EASY','5+','Inversion of Control (IoC)',2,1),(972,'What are the different types of IoC (dependency injection)?','EASY','5+','2 types',2,1),(973,'What is AOP?','EASY','5+','to modularize crosscutting concerns',2,1),(974,'Give an example of BeanFactory implementation.','MEDIUM','5+','XmlBeanFactoryclass',2,1),(975,'What is the difference between Bean Factory and ApplicationContext?','MEDIUM','5+','2 to 3 differences',2,1),(976,'What does a bean definition contain?','MEDIUM','5+','configuration metadata',2,1),(977,'How do add a bean in spring application?','MEDIUM','5+','code or annotation',2,1),(978,'What bean scopes does Spring support? Explain them.','MEDIUM','5+','five scopes',2,1),(979,'Are Singleton beans thread safe in Spring Framework?','HARD','5+','no',2,1),(980,'What is bean auto wiring?','HARD','5+','able to autowire relationships between collaborating beans',2,1),(981,'How is event handling done in Spring?','HARD','5+','ApplicationContext',2,1),(982,'How JdbcTemplate can be used?','HARD','5+','provides many convenience',2,1),(983,'Explain the@Controllerannotation.','HARD','5+','indicates that a particular class serves the role of a controller.',2,1),(984,'What is the difference between SQL and MySQL or SQL Server?','EASY','5+','proper difference with expanation',3,1),(985,'What are various DDL commands in SQL? Give brief description of their purposes.','EASY','5+','various DDL or Data Definition Language commands',3,1),(986,'Is a NULL value same as zero or a blank space? If not then what is the difference?','EASY','5+','A NULL value is not same as zero or a blank space',3,1),(987,'How can you eliminate duplicate rows from a query result?','EASY','5+','DISTINCT keyword',3,1),(988,'What are the specific uses of SQL functions?','EASY','5+','4 to 5 uses',3,1),(989,'What is the use of the NULLIF function?','MEDIUM','5+','compares two expressions',3,1),(990,'Discuss the syntax and use of the COALESCE function?','MEDIUM','5+','returns the first non-null expression given in the parameter list',3,1),(991,'What do you understand by a subquery? When is it used?','MEDIUM','5+','a SELECT statement embedded in a clause of another SELECT statement',3,1),(992,'How do you copy rows from one table to another?','MEDIUM','5+','a subquery is used in the place of the VALUES clause.',3,1),(993,'Can you modify the rows in a table based on values from another table?','MEDIUM','5+','Yes.',3,1),(994,'What happens if you omit the WHERE clause in a delete statement?','HARD','5+','All the rows in the table are deleted.',3,1),(995,'All the rows in the table are deleted.','HARD','5+','subqueries can be used',3,1),(996,'What is the difference between VARCHAR2 AND CHAR datatypes?','HARD','5+','length character data',3,1),(997,'What is a view? Why should you use a view?','HARD','5+','view is a logical snapshot based on a table or another view.',3,1),(998,'Say True or False. Give explanation if False.All group functions ignore null values.','HARD','5+','True.',3,1),(999,'wedcaescwfcv','HARD','1-5','howierrghelightnr',2,0),(1000,'dfvdfbrs','MEDIUM','1-5','cdscsdcsc',2,0),(1001,'how are you?','HARD','1-5','fewf',2,1),(1002,'What do you mean by Object?','EASY','0','runtime entity',1,1),(1003,'Define class?','EASY','0','runtime entity and its state',1,1),(1004,'What is Singleton class?','EASY','0','control object creation',1,1),(1005,'What is a static variable?','EASY','0','Class variables',1,1),(1006,'What is the default value of float and double datatype in Java?','EASY','0','0.0f and 0.0d',1,1),(1007,'What do you mean by Access Modifier?','MEDIUM','0','set access levels',1,1),(1008,'Why is String class considered immutable?','MEDIUM','0','object cannot be changed',1,1),(1009,'Why is StringBuffer called mutable?','MEDIUM','0','modifications to Strings of characters then StringBuffer',1,1),(1010,'What is the difference between StringBuffer and StringBuilder class?','MEDIUM','0','StringBuilder is faster than StringBuffer',1,1),(1011,'Which package is used for pattern matching with regular expressions?','MEDIUM','0','sample comment',1,1),(1012,'What do you mean by Multithreaded program?','HARD','0','run concurrently',1,1),(1013,'Explain garbage collection in Java?','HARD','0','free the memory',1,1),(1014,'Explain the usage of this() with constructors?','HARD','0','call constructer of same class',1,1),(1015,'Difference between throw and throws?','HARD','0','Throw is used to trigger an exception where as throws is used in declaration of exception',1,1),(1016,'Can a constructor be made final?','HARD','0','No',1,1),(1017,'What is static block?','EASY','0','used to initialize the static data member',2,1),(1018,'Define composition?','EASY','0','Holding the reference of the other class',2,1),(1019,'What is function overloading?','EASY','0','multiple functions by same name but different parameters',2,1),(1020,'What is function overriding?','EASY','0','subclass provides a specific implementation of a method',2,1),(1021,'Difference between Overloading and Overriding?','EASY','0','2 to 3 differences',2,1),(1022,'What is NullPointerException?','MEDIUM','0','instance method of a null object',2,1),(1023,'What are the ways in which a thread can enter the waiting state?','MEDIUM','0',' sleep() and wait()',2,1),(1024,'How does multi-threading take place on a computer with a single CPU?','MEDIUM','0','operating system\'s task scheduler',2,1),(1025,'What invokes a thread\'s run() method?','MEDIUM','0','when the thread is initially executed',2,1),(1026,'Does it matter in what order catch statements for FileNotFoundException and IOException are written?','MEDIUM','0','Yes it does',2,1),(1027,'What is the difference between yielding and sleeping?','HARD','0','ready and waiting state',2,1),(1028,'What are Wrapper classes?','HARD','0','allow primitive types to be accessed as objects',2,1),(1029,'Which package has light weight components?','HARD','0','javax.Swing package',2,1),(1030,'What is the difference between the paint() and repaint() methods?','HARD','0','Graphics object',2,1),(1031,'What is the purpose of File class?','HARD','0','provide access to the files and directories',2,1),(1032,'What is Serialization and deserialization?','EASY','0','writing the state of an object',3,1),(1033,'What are use cases?','EASY','0','describes a situation that a program might encounter',3,1),(1034,'What is the difference between Swing and AWT components?','EASY','0','AWT components are heavy-weight whereas Swing components are lightweight.',3,1),(1035,'Is there any limitation of using Inheritance?','EASY','0','makes the subclass too clustering and sometimes error-prone',3,1),(1036,'When is the ArrayStoreException thrown?','EASY','0','When copying elements between different arrays',3,1),(1037,'Can you call one constructor from another if a class has multiple constructors?','MEDIUM','0','Yes use this() syntax.',3,1),(1038,'What is a transient variable?','MEDIUM','0','not be serialized during Serialization',3,1),(1039,'What is synchronization?','MEDIUM','0','capability to control the access of multiple threads',3,1),(1040,'Does garbage collection guarantee that a program will not run out of memory?','MEDIUM','0','No',3,1),(1041,'What is the purpose of the System class?','MEDIUM','0','provide access to system resources',3,1),(1042,'Under what conditions is an object\'s finalize() method invoked by the garbage collector?','HARD','0','when it detects that the object has become unreachable',3,1),(1043,'How can a dead thread be restarted?','HARD','0','not possible',3,1),(1044,'What are ClassLoaders?','HARD','0','object that is responsible for loading classes',3,1),(1045,'Why do we need wrapper classes?','HARD','0','provides utility methods',3,1),(1046,'What is the difference between error and an exception?','HARD','0','error is an irrecoverable',3,1),(1047,'What are the advantages of ArrayList over arrays?','EASY','1 to 5 ','involves only updating the next pointer',1,1),(1048,'How do you decide when to use ArrayList and LinkedList?','EASY','1 to 5 ','2 to 3 differences',1,1),(1049,'What is a Values Collection View ?','EASY','1 to 5 ','What is a Values Collection View ?',1,1),(1050,'Where and how can you use a private constructor?','EASY','1 to 5 ','do not want other classes to instantiate',1,1),(1051,'Describe life cycle of thread?','EASY','1 to 5 ','5 states',1,1),(1052,'What is the difference between the >> and >>> operators?','MEDIUM','1 to 5 ','zero-fills bits',1,1),(1053,'Which method of the Component class is used to set the position and size of a component?','MEDIUM','1 to 5 ','setBounds()',1,1),(1054,'What is the range of the short type?','MEDIUM','1 to 5 ','(2^15) to 2^15 - 1',1,1),(1055,'What is the immediate superclass of Menu?','MEDIUM','1 to 5 ','MenuItem class',1,1),(1056,'Does Java allow Default Arguments?','MEDIUM','1 to 5 ','No',1,1),(1057,'Which number is denoted by leading zero in java?','HARD','1 to 5 ','Octal Numbers',1,1),(1058,'Explain suspend() method under Thread class>','HARD','1 to 5 ','to pause or temporarily stop the execution',1,1),(1059,'Explain main thread under Thread class execution?','HARD','1 to 5 ','created automatically and it begins to execute immediately when a program starts',1,1),(1060,'Define code as an attribute of Applet?','HARD','1 to 5 ','specify the name of the applet class',1,1),(1061,'Define Network Programming?','HARD','1 to 5 ','writing programs that execute across multiple devices',1,1),(1062,'What is a Socket?','EASY','1 to 5 ','communication mechanism between two computers using TCP',2,1),(1063,'Advantages of Java Sockets?','EASY','1 to 5 ','flexible and sufficient',2,1),(1064,'Which class is used by server applications to obtain a port and listen for client requests?','EASY','1 to 5 ','java.net.ServerSocket',2,1),(1065,'Why Generics are used in Java?','EASY','1 to 5 ','provide compile-time type safety',2,1),(1066,'What is Nested top-level class?','EASY','1 to 5 ','a class is declared within a class',2,1),(1067,'If System.exit (0); is written at the end of the try block  will the finally block still execute?','MEDIUM','1 to 5 ','No',2,1),(1068,'What is daemon thread?','MEDIUM','1 to 5 ','low priority thread',2,1),(1069,'Which method is used to create the daemon thread?','MEDIUM','1 to 5 ','setDaemon method',2,1),(1070,'What is the SimpleTimeZone class?','MEDIUM','1 to 5 ','provides support for a Gregorian calendar',2,1),(1071,'Can a vector contain heterogenous objects?','MEDIUM','1 to 5 ','Yes a Vector can contain heterogenous objects',2,1),(1072,'What is difference between Path and Classpath?','HARD','1 to 5 ','operating system level environment variales',2,1),(1073,'Can a class declared as private be accessed outside it\'s package?','HARD','1 to 5 ','No',2,1),(1074,'Can an Interface extend another Interface?','HARD','1 to 5 ','yes',2,1),(1075,'What is an object\'s lock and which object\'s have locks?','HARD','1 to 5 ','used by multiple threads to obtain synchronized access to the object',2,1),(1076,'If a method is declared as protected  where may the method be accessed?','HARD','1 to 5 ','only be accessed by classes or interfaces of the same package',2,1),(1077,'What is an Iterator?','EASY','1 to 5 ','provide traversal',3,1),(1078,'What if the main method is declared as private?','EASY','1 to 5 ','compiles properly but at runtime it will give  Main method not public.  message',3,1),(1079,'What if I do not provide the String array as the argument to the method','EASY','1 to 5 ','runtime error  NoSuchMethodError ',3,1),(1080,'What is the first argument of the String array in main method','EASY','1 to 5 ','The String array is empty',3,1),(1081,'If I do not provide any arguments on the command line  then the String array of Main method will be empty or null?','EASY','1 to 5 ','It is empty. But not null.',3,1),(1082,'Can an application have multiple classes having main method?','MEDIUM','1 to 5 ','Yes it is possible.',3,1),(1083,'What are Checked and UnChecked Exception?','MEDIUM','1 to 5 ','Exception',3,1),(1084,'What are different types of inner classes?','MEDIUM','1 to 5 ','3 to 4 types',3,1),(1085,'Does importing a package imports the subpackages as well?','MEDIUM','1 to 5 ','No you will have to import the subpackages explicitly',3,1),(1086,'Which methods of Serializable interface should I implement?','MEDIUM','1 to 5 ','The serializable interface is an empty interface',3,1),(1087,'What one should take care of while serializing the object?','HARD','1 to 5 ','One should make sure that all the included objects are also serializable',3,1),(1088,'What happens to the static fields of a class during serialization?','HARD','1 to 5 ','Serialization ignores static fields',3,1),(1089,'How are Observer and Observable used?','HARD','1 to 5 ','Objects that subclass the Observable class maintain a list of observers',3,1),(1090,'How does Java handle integer overflows and underflows?','HARD','1 to 5 ','It uses those low order bytes of the result that can fit into the size of the type allowed by the operation',3,1),(1091,'If I write return at the end of the try block  will the finally block still execute?','HARD','1 to 5 ','Yes',3,1),(1092,'What are J2EE Patterns?','EASY','5+','These patterns are identified by Sun Java Center',1,1),(1093,'What is Factory pattern?','EASY','5+','e create object without exposing the creation logic to the client',1,1),(1094,'How can you create Singleton class in java?','EASY','5+','two step process',1,1),(1095,'What are the difference between a static class and a singleton class?','EASY','5+','2 to 3 differences',1,1),(1096,'Can we create a clone of a singleton object?','EASY','5+','yes',1,1),(1097,'How to prevent cloning of a singleton object?','MEDIUM','5+','Throw exception within the body of clone()',1,1),(1098,'What is Adapter pattern?','MEDIUM','5+','works as a bridge between two incompatible interfaces',1,1),(1099,'What are the entities of Service Locator pattern?','MEDIUM','5+','example with explanation',1,1),(1100,'Name the actor classes used in Memento pattern.','MEDIUM','5+','three actor classes',1,1),(1101,'What is Strategy pattern?','MEDIUM','5+','class behavior or its algorithm can be changed at run time',1,1),(1102,'What is MVC pattern?','HARD','5+','pattern is used to separate application\'s concerns',1,1),(1103,'What is Data Access Object Pattern(DAO) pattern?','HARD','5+','used to separate low level data accessing API or operations from high level business services',1,1),(1104,'What is Visitor pattern?','HARD','5+','we use a visitor class which changes the executing algorithm of an element class.',1,1),(1105,'Give an example where Interpreter pattern is used?','HARD','5+','This pattern is used in SQL parsing',1,1),(1106,'What is Proxy pattern?','HARD','5+','a class represents functionality of another class',1,1),(1107,'What is Spring?','EASY','5+','open source development framework for enterprise Java',2,1),(1108,'What is Spring configuration file?','EASY','5+','contains the classes information and describes how these classes are configured and introduced to each other',2,1),(1109,'What is Dependency Injection?','EASY','5+','Inversion of Control (IoC)',2,1),(1110,'What are the different types of IoC (dependency injection)?','EASY','5+','2 types',2,1),(1111,'What is AOP?','EASY','5+','to modularize crosscutting concerns',2,1),(1112,'Give an example of BeanFactory implementation.','MEDIUM','5+','XmlBeanFactoryclass',2,1),(1113,'What is the difference between Bean Factory and ApplicationContext?','MEDIUM','5+','2 to 3 differences',2,1),(1114,'What does a bean definition contain?','MEDIUM','5+','configuration metadata',2,1),(1115,'How do add a bean in spring application?','MEDIUM','5+','code or annotation',2,1),(1116,'What bean scopes does Spring support? Explain them.','MEDIUM','5+','five scopes',2,1),(1117,'Are Singleton beans thread safe in Spring Framework?','HARD','5+','no',2,1),(1118,'What is bean auto wiring?','HARD','5+','able to autowire relationships between collaborating beans',2,1),(1119,'How is event handling done in Spring?','HARD','5+','ApplicationContext',2,1),(1120,'How JdbcTemplate can be used?','HARD','5+','provides many convenience',2,1),(1121,'Explain the@Controllerannotation.','HARD','5+','indicates that a particular class serves the role of a controller.',2,1),(1122,'What is the difference between SQL and MySQL or SQL Server?','EASY','5+','proper difference with expanation',3,1),(1123,'What are various DDL commands in SQL? Give brief description of their purposes.','EASY','5+','various DDL or Data Definition Language commands',3,1),(1124,'Is a NULL value same as zero or a blank space? If not then what is the difference?','EASY','5+','A NULL value is not same as zero or a blank space',3,1),(1125,'How can you eliminate duplicate rows from a query result?','EASY','5+','DISTINCT keyword',3,1),(1126,'What are the specific uses of SQL functions?','EASY','5+','4 to 5 uses',3,1),(1127,'What is the use of the NULLIF function?','MEDIUM','5+','compares two expressions',3,1),(1128,'Discuss the syntax and use of the COALESCE function?','MEDIUM','5+','returns the first non-null expression given in the parameter list',3,1),(1129,'What do you understand by a subquery? When is it used?','MEDIUM','5+','a SELECT statement embedded in a clause of another SELECT statement',3,1),(1130,'How do you copy rows from one table to another?','MEDIUM','5+','a subquery is used in the place of the VALUES clause.',3,1),(1131,'Can you modify the rows in a table based on values from another table?','MEDIUM','5+','Yes.',3,1),(1132,'What happens if you omit the WHERE clause in a delete statement?','HARD','5+','All the rows in the table are deleted.',3,1),(1133,'All the rows in the table are deleted.','HARD','5+','subqueries can be used',3,1),(1134,'What is the difference between VARCHAR2 AND CHAR datatypes?','HARD','5+','length character data',3,1),(1135,'What is a view? Why should you use a view?','HARD','5+','view is a logical snapshot based on a table or another view.',3,1),(1136,'Say True or False. Give explanation if False.All group functions ignore null values.','HARD','5+','True.',3,1);
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
  CONSTRAINT `questions_asked_ibfk_2` FOREIGN KEY (`candidate_id`) REFERENCES `candidate` (`candidate_id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions_asked`
--

LOCK TABLES `questions_asked` WRITE;
/*!40000 ALTER TABLE `questions_asked` DISABLE KEYS */;
INSERT INTO `questions_asked` VALUES (999,8,7.00,'satisfactory',3,36),(999,13,6.00,'string',3,37);
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
INSERT INTO `rounds` VALUES (0),(1),(2);
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
  `system_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`system_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_attributes`
--

LOCK TABLES `system_attributes` WRITE;
/*!40000 ALTER TABLE `system_attributes` DISABLE KEYS */;
INSERT INTO `system_attributes` VALUES (3,2,75,0);
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
  KEY `round_number` (`round_number`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (15,'shaurya','shekhar',1,0,'shaurya@email.com','9876543210'),(16,'komal','naik',0,1,'komal@email.com','9876543210'),(17,'rupesh','singh',0,1,'rupesh@email.com','9876543210');
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

-- Dump completed on 2018-07-25 21:36:23
