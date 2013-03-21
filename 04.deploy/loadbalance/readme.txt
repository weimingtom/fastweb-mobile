1.启动Tomcat MSM的集成环境；
	先手动启动mem-clu1.bat mem-clu2.bat mem-clu3.bat 
2.启动集群
	手动启动tomcat1.bat tomcat2.bat
3.测试集群Session,Session一致，集群环境OK
	http://localhost:8180/examples/jsp/sessions/my.jsp
	http://localhost:8280/examples/jsp/sessions/my.jsp
3.启动nginx.bat,测试返回session与tomcat的session一致
	http://localhost/examples/jsp/sessions/my.jsp