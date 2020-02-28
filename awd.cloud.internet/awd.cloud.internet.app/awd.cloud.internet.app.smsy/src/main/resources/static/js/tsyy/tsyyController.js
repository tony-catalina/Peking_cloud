define(["app","js/tsyy/tsyyView", "js/model/tsyyModel"], function(app, TsyyView, user) {

	var loginuser={};
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '.yydj',
		event: 'click',
		handler: yydj
	},{
		element: '.yyjl',
		event: 'click',
		handler: yyjl
	}];

	function init(query){
		loginuser=query;
		TsyyView.render({
			model: query,
			bindings: bindings
		});
	}
	function back(){
		app.router.load("menu/menu",loginuser)
	}
	function yydj(){
		app.router.load("tsyyy/tsyyy",loginuser)
	}
	function yyjl(){
		app.router.load("tsyyjl/tsyyjl",loginuser)
	}
	return {
		init: init
	};
});
