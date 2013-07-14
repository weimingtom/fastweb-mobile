//服务器地址
server_url="http://localhost:8080/fastweb-mongodb/";

$(document).on('pageinit','[data-role=page]', function(){
    $('[data-position=fixed]').fixedtoolbar({ tapToggle:false });
    //console.debug($( ".lineview" ));
	//$.lineview();		

	console.debug("开始自动登陆 ... ... ");	
	console.debug($('[data-role=header]'));
	
	$( "#signinform" ).on( "click", $.login());
	$('#login').hide();
	$('#login-info').show();
	
});

$(function(){
	
	
});


(function($){
	$.login = function(name,password)
	{
		console.debug("登陆加载......");		
		
		
		$.getJSON('pagedata/signin.json', function(data) {
			console.debug(data);
			template=$('#login-template');
			//console.debug(template);
			view= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
			//console.debug($('#slides'));
			
			$('#login').hide();
			console.debug($('#login-info .ui-btn-text'));
			$('#login-info .ui-btn-text').html(view);
			$('#login-info').show();
			
			console.debug("登陆加载......完成");		


			//$('[data-role=header]').append(view).trigger('create').button("refresh");//.page();
		});		
	}
})(jQuery);

$(function(){
	console.debug("动态加载左侧菜单数据...");
	
	menu_url=server_url+"menu/list/0/100.jsonp?callback=?"
	//'pagedata/menu-left.json'
	
	$.getJSON(menu_url,{}, function(data) {	
		menudata={"data":data};
		console.debug("menu data:"+menudata.data[0].subtitle);
		console.debug("menu data:"+menudata.data[0].items[0].title);
		template=$('#left-menu-template');
		mylistview1= Mustache.to_html(template.html(),menudata).replace(/^\s*/mg, '');
		$('#menuitem').empty().append(mylistview1).trigger('create').collapsibleset('refresh');
		console.debug("动态加载左侧菜单数据...完成");
	});
	
});

//模板引擎定义
(function ($) {
    'use strict';
  $.mustache = function (template, view, partials) {
	return Mustache.render(template, view, partials);	
  };

  $.fn.mustache = function (view, partials) {
    return $(this).map(function (i, elm) {
		//console.debug($(elm).html());
      var template = $.trim($(elm).html());//jquery2html
	  	//console.debug(template);
		//console.debug(view);
      var output = $.mustache(template, view, partials);
      return $(output).get();//html2jquery
    });
  };

})(jQuery);


(function($){
	//$.getUrlParam('cid');
	$.getUrlParam = function(name)
	{
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
		}
})(jQuery);
