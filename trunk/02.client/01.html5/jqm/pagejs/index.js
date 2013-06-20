$(function(){
	$.slides();
	
	console.debug("start dyna news ... ... ");
	$.getJSON('pagedata/slides.json', function(data) {

		imgtemplate=$('#slides-template');
		//console.debug(template);
		imgview= Mustache.to_html(imgtemplate.html(),data).replace(/^\s*/mg, '');
		//console.debug($('#slides'));
		$('#slides-list').html(imgview);//.trigger('create').listview('refresh');
		$.slides();
	});

	$.getJSON('pagedata/news.json', function(data) {

		template=$('#news-template');
		//console.debug(template);
		newsview= Mustache.to_html(template.html(),data).replace(/^\s*/mg, '');
		//console.debug(newsview);
		$('#news-listview').empty().append(newsview).trigger('create').listview('refresh');
	});

});


(function($){
	$.slides = function()
	{
		$('#slides').slidesjs({
			width: 940,
			height: 528,
			play: {
			  active: true,
			  auto: true,
			  interval: 4000,
			  swap: true
			}
		});		
	}
})(jQuery);
