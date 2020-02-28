define(["app","js/tzgg/tzggView", "js/model/tzggModel"], function(app, TzggView,tzgg) {

	var loginuser={};
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	}];

	function init(query){
		loginuser=query;
		TzggView.render({
			model: tzgg,
			bindings: bindings
		});
		Handlebars.registerHelper("equal",function(v1,v2,options){
			   if(v1 == v2){
			     //满足添加继续执行
			     return options.fn(this);
			   }else{
			     //不满足条件执行{{else}}部分
			     return options.inverse(this);
			  }
		});
	}
	function back(){
		app.router.load("menu/menu",loginuser);
	}
	return {
		init: init
	};
});
