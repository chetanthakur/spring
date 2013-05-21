spring
======

THIS IS PROJECT WHICH WORK WITH THER INGERATION OF SPRING AND HIBERNATE.

Please make following entry in context.xml


	 <Resource name="jdbc/mysql" auth="Container" type="javax.sql.DataSource" 
		driverClassName="com.mysql.jdbc.Driver" factory="org.apache.tomcat.jdbc.pool.DataSourceFactory"
		url="jdbc:mysql://localhost/spring" username="root" password="root"
		validationQuery="select 1 from dual" maxActive="30"
		maxIdle="10" minIdle="5" initialSize="10" />



THANKS
