逻辑分层和json、分页
	spring-rest 

数据存储和大数据、实时数据
	spring-mongodb

mvn clean         		--> cleans the project
mvn clean test    		--> cleans the project and runs all tests
mvn clean package 		--> cleans the project and builds the WAR
mvn tomcat:run	 		--> web run
mvn eclipse:eclipse		--> gen eclipse .project and .classpath config file
mvn jar:jar				--> only build jar file.
mvn javadoc:javadoc     --> gen api files;

mvn dependency:copy-dependencies  拷贝jar
mvn dependency:tree

mvn test -Dtest={测试文件名称}

support:
	service junit test
	junit test

默认规范	
	service:包的默认规范  **.service

!!!*------------------------------------------------------------------------------------*!!!

20130228
domain 必须的属性：@JsonAutoDetect
domain 必须要有get set 方法

2013-02-25

MongoDB并发测试，报出上述错误。究其原因，是数据库连接数太少，资源耗尽。查看com.mongodb.MongoOptions源代码，其中有connectionsPerHost和threadsAllowedToBlockForConnectionMultiplier两个重要的属性。

connectionsPerHost：每个主机的连接数

threadsAllowedToBlockForConnectionMultiplier：线程队列数，它以上面connectionsPerHost值相乘的结果就是线程队列最大值。如果连接线程排满了队列就会抛出“Out of semaphores to get db”错误。

connectionsPerHost默认是10，threadsAllowedToBlockForConnectionMultiplier默认是5，也就是线程池有50个连接数可供使用。因此只要将这个属性的值加大就可以避免上述错误。

 

其它属性设置：

maxWaitTime:最大等待连接的线程阻塞时间

connectTimeout：连接超时的毫秒。0是默认和无限

socketTimeout：socket超时。0是默认和无限

autoConnectRetry：这个控制是否在一个连接时，系统会自动重试
	