define(["app","js/registing/registingView", "js/model/userModel","js/tools"], function(app, RegistingView, user,tools) {

	var bindings = [{
		element: '#login',
		event: 'click',
		handler: login
	}];

	function init(query){		
		RegistingView.render({
			model: query,
			bindings: bindings
		});
		
	}
	function login(){		

	}
	
	function regist(){
	}
	
	return {
		init: init
	};
});
