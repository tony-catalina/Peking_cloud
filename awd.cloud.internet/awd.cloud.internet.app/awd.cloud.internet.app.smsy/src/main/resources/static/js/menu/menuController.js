define(["app","js/menu/menuView", "js/model/userModel","js/tools"], function(app, MenuView,user,tools) {
    var marked=false;
    var loginuser={};
	var bindings = [{
		element: '#jgyy',
		event: 'click',
		handler: jgyy
	},{
		element: '#smsy',
		event: 'click',
		handler: smsy
	},{
		element: '#tzgg',
		event: 'click',
		handler: tzgg
	},{
		element: '.back',
		event: 'click',
		handler: back
	}];

	function init(query){
		loginuser=query;
		MenuView.render({
			model: loginuser,
			bindings: bindings,
			jgdw:loginuser.type==1?1:null
		});		
	}
	function jgyy(){
		app.router.load("tsyy/tsyy",loginuser)
	}
	function smsy(){
		if(loginuser.type==1){
			loginuser["isLoad"] = true;
			app.router.load("smsy/jgdw/jgdw",loginuser)			
		}else{
			loginuser["isLoad"] = true;
			app.router.load("smsy/badw/badw",loginuser);
		}		
			
	}
	function tzgg(){
		app.router.load("tzgg/tzgg",loginuser)
	}
	function back(){
		app.router.load("login/login",loginuser)
	}
	return {
		init: init
	};
});
