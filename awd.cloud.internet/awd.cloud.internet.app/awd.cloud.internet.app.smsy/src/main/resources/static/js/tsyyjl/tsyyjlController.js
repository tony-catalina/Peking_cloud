define(["app","js/tsyyjl/tsyyjlView","js/tsyyjl/tsyyjlxxView", "js/model/tsyyjlModel"], function(app, TsyyjlView,TsyyjlxxView, user) {

	var $ = Framework7.$;
	var loginuser={};
	var bindings = [{
		element: '.backTsyy',
		event: 'click',
		handler: back
	},{
		element: '.tsyyjlxx',
		event: 'click',
		handler: tsyyjlxx
	},{
		element: '.tsyyjlback',
		event: 'click',
		handler: tsyyjlback
	},{
		element: '.pull-to-refresh-content',
		event: 'refresh',
		handler: refreshtsyyjl
	}];

	function init(query){
		loginuser=query;
		TsyyjlView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function back(){
		app.router.load("tsyy/tsyy",loginuser)
	}
	function tsyyjlback(){
		app.router.load("tsyyjl/tsyyjl",loginuser)
	}
	function tsyyjlxx(){
		var yyid = $(this).attr("value")
		TsyyjlxxView.render({
			model: yyid,
			bindings: bindings
		});
	}
	function refreshtsyyjl(){
		setTimeout(function () {
	        // 随机图片
	        var picURL = 'http://hhhhold.com/88/d/jpg?' + Math.round(Math.random() * 100);
	        // 随机音乐
	        var song = songs[Math.floor(Math.random() * songs.length)];
	        // 随机作者
	        var author = authors[Math.floor(Math.random() * authors.length)];
	        // 列表元素的HTML字符串
	        var itemHTML = '<li class="item-content">' +
	                          '<div class="item-media"><img src="' + picURL + '" width="44"/></div>' +
	                          '<div class="item-inner">' +
	                            '<div class="item-title-row">' +
	                              '<div class="item-title">' + song + '</div>' +
	                            '</div>' +
	                            '<div class="item-subtitle">' + author + '</div>' +
	                          '</div>' +
	                        '</li>';
	        // 前插新列表元素
	        $(".pull-to-refresh-content").find('ul').prepend(itemHTML);
	        // 加载完毕需要重置
	        app.pullToRefreshDone();
	    }, 2000);
	}
	return {
		init: init
	};
});
