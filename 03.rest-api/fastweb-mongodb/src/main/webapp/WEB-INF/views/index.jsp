<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/commons/taglibs.jsp" %>

<myweb:override name="head">
	<title>增删改查</title>

</myweb:override>

<myweb:override name="leftcontent">
   <div id="crud-grid">
   
   		hello,world
   		
   		<br/>
   		
		
   </div> 
   
</myweb:override>

<myweb:override name="rightcontent">
   <div id="crud-grid">
   
 
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2">分页查询</a>
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2.jsonp?callback=aa">分页查询jsonp,支持跨域</a>
   		
		
   </div> 
   
</myweb:override>

<%@ include file="/WEB-INF/views/base.jsp" %>