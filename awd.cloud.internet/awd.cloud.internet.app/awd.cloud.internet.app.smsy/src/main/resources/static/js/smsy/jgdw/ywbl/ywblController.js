define(["app","js/smsy/jgdw/ywbl/ywblView"], 
		function(app, YwblView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '#jgdw_dsh',
		event: 'click',
		handler: jgdw_ywbl_dsh
	},{
		element: '#jgdw_fcsm',
		event: 'click',
		handler: jgdw_ywbl_fcsm
	},{
		element: '#jgdw_shwtg',
		event: 'click',
		handler: jgdw_ywbl_shwtg
	},{
		element: '#jgdw_clgl',
		event: 'click',
		handler: jgdw_ywbl_clgl
	},{
		element: '#jgdw_fcjl',
		event: 'click',
		handler: jgdw_ywbl_fcjl
	}];
	
	function init(query){
		loginuser=query;
		YwblView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function jgdw_ywbl_dsh(){
		app.router.load("smsy/jgdw/ywbl/dsh/dsh",loginuser);
	}
	
	function jgdw_ywbl_fcsm(){
		app.router.load("smsy/jgdw/ywbl/fcsm/fcsm",loginuser);
	}
	
	function jgdw_ywbl_shwtg(){
		app.router.load("smsy/jgdw/ywbl/shwtg/shwtg",loginuser);
	}
	
	function jgdw_ywbl_clgl(){
		app.router.load("smsy/jgdw/ywbl/clgl/clgl",loginuser);
	}
	
	function jgdw_ywbl_fcjl(){
		app.router.load("smsy/jgdw/ywbl/fcjl/fcjl",loginuser);
	}
	
	return {
		init: init
	};
});
