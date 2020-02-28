define(["app","js/smsy/jgdw/ywbl/dsh/rysh/ryshView"], 
		function(app, RyshView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#shbtg',
		event: 'click',
		handler: ryshbtg
	}];
	
	function init(query){
		loginuser=query;
		RyshView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function back(){
		app.router.load("smsy/jgdw/ywbl/dsh/dsh",loginuser);
	}
	
	function ryshbtg(){
		$("#btgyy").css("display","block");
	}
	
	return {
		init: init
	};
});
