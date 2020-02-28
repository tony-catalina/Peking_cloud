require.config({
	paths: {
		handlebars: "lib/handlebars",
		text: "lib/text",
		hbs: "lib/hbs"
	},
	shim: {
		handlebars: {
			exports: "Handlebars"
		}
	},
	urlArgs:'v3.5.1'

});
define('app', ['js/router','js/tools'], function(Router,tools) {
	Router.init();
	//tools.getToken();	
	tools.getAuthor();	
	var f7 = new Framework7({
		modalTitle: '监管e约',
	    modalButtonOk: '确认',
	    modalButtonCancel: '取消',
	    modalPreloaderTitle: '加载中...',
	    cache: false,
	    material: false,
	    pushState: false,
	    swipePanel: 'left',
	    swipeBackPage: false,
	    swipePanelThreshold: 15,
	    domCache: false,
	    smartSelectBackText: '返回',
	    smartSelectPickerCloseText: '完成',
	    smartSelectPopupCloseText: '关闭',
	    swipePanelOnlyClose: true,
	    preloadPreviousPage: false,
	    uniqueHistory: false,
	});
	var mainView = f7.addView('.view-main', {
		dynamicNavbar: true
	});
	
	
	
	return {
		f7: f7,
		mainView: mainView,
		router: Router
	};
});
