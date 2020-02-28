define(["app","js/smsy/jgdw/jgdwView"], 
		function(app, JgdwView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#jgdw_dsm',
		event: 'click',
		handler: jgdw_dsm
	},{
		element: '#jgdw_ywbl',
		event: 'click',
		handler: jgdw_ywbl
	},{
		element: '#jgdw_info',
		event: 'click',
		handler: jgdw_info
	}];
	
	function init(query){
		isLoad = query.isLoad;
		loginuser=query;
		JgdwView.render({
			model: query,
			bindings: bindings
		});
		if (isLoad != undefined && isLoad == true) {
			setTimeout(function () {
				jgdw_dsm();
			}, 500);
		}
		app.mainView.showToolbar();
	}
	function back(){
		app.router.load("menu/menu",loginuser)
	}
	
	function jgdw_dsm(){
		app.router.load("smsy/jgdw/dsm/dsm",loginuser);
	}
	function jgdw_ywbl(){
		app.router.load("smsy/jgdw/ywbl/ywbl",loginuser);
	}
	function jgdw_info(){
		app.router.load("smsy/jgdw/info/info",loginuser);
	}

	return {
		init: init
	};
});
