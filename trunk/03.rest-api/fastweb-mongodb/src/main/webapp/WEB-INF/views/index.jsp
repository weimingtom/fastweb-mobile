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
<script src="http://127.0.0.1/jqm/js/jquery-1.9.1.min.js"></script>

<script>
jQuery.postJSON = function(data, url, callback) {
	return jQuery.ajax({
			type: "POST",
			url: url,
			data: data,
			contentType: "application/json; charset=utf-8",
			dataType: "json",
			success: callback
		});
};

menu_url="http://localhost:8080/fastweb-mongodb/menu.jsonp?callback=?";
function createmenu1() { 	$.postJSON("{}",menu_url, funOK); 	}
	
function funOK(msg) {
	console.debug(msg);
}


</script>
   <div id="crud-grid">
   
   		<a href="http://localhost:8080/fastweb-mongodb/menu/list/0/100.jsonp?callback=?">分页查询所有菜单</a>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/51dbbcc517f416991a71775a.jsonp?callback=?">获取菜单</a>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/add.jsonp?callback=?">新增菜单</a>
   		 nginx 反响影射解决将前端和后端整合到一个域，解决跨域post提交的问题
   		<a href="#" onclick="createmenu1();">创建菜单</a>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/add.jsonp?callback=?">新增菜单</a>
   		<a href="http://localhost:8080/fastweb-mongodb/menu/add.jsonp?callback=?">新增菜单</a>
   		
   		
 		<br/>
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2">分页查询</a>
   		<a href="http://localhost:8080/fastweb-mongodb/person/list/2/2.jsonp?callback=aa">分页查询jsonp,支持跨域</a>
   		
		
   </div> 
   
</myweb:override>

<%@ include file="/WEB-INF/views/base.jsp" %>