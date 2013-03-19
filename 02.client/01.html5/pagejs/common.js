$(function(){
	console.debug("start dyna login ... ... ");
	
	console.debug($('[data-role=header]'));
	
	$('[data-position=fixed]').fixedtoolbar({ tapToggle:false });
	$( "#signinform" ).on( "click", $.login());
	$('#login').hide();
	$('#login-info').show();
});


(function($){
	$.login = function(name,password)
	{
		console.debug("login ......");		
		
		
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
	

			//$('[data-role=header]').append(view).trigger('create').button("refresh");//.page();
		});		
	}
})(jQuery);

$(function(){
	console.debug("/left menu dymn load from json file...");
	
	$.getJSON('pagedata/menu-left.json', function(data) {		
		template=$('#left-menu-template');
		//use source Mustarche pref 		
		mylistview1= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(mylistview1);
		
		//mylistview2=template.mustache(data);
		//console.debug(mylistview2);
		
		$('#menuitem').empty().append(mylistview1).trigger('create').collapsibleset('refresh');
		
		//$('#menuitem').html(mylistview2.html()).trigger('create').collapsibleset('refresh');
		//$('#menuitem').html(mylistview2.html()).collapsibleset('refresh');
	});
});


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
