define(["app","js/smsy/badw/badwView"], 
		function(app,  BadwView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#badw_dsm',
		event: 'click',
		handler: badw_dsm
	},{
		element: '#badw_ywbl',
		event: 'click',
		handler: badw_ywbl
	},{
		element: '#badw_info',
		event: 'click',
		handler: badw_info
	}];
		
	
	function init(query){
		isLoad = query.isLoad;
		loginuser=query;
		BadwView.render({
			model: query,
			bindings: bindings
		});
		if (isLoad != undefined && isLoad == true) {
			setTimeout(function () {
				badw_dsm();	
			}, 500);
		}
		app.mainView.showToolbar();
	}
	function back(){
		app.router.load("menu/menu",loginuser)
	}	
	
	function badw_dsm(){	
		app.router.load("smsy/badw/dsm/dsm",loginuser)
	}
	
	function badw_ywbl(){
		app.router.load("smsy/badw/ywbl/ywbl",loginuser)
	}	

	function badw_info(){
		app.router.load("smsy/badw/info/info",loginuser)
	}
	function ywsppop(){
		YwspView.render({
			model: loginuser,
			bindings: bindings
		});	
		app.f7.popup(".popup-services");
	}	
	
	return {
		init: init
	};
});
