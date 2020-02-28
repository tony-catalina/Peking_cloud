define(["app","js/smsy/badw/ywbl/ywblView","js/tools"], function(app, YwblView, tools) {

	var $ = Framework7.$;
	var loginuser={};
	
	var bindings=[{
		element: '#badw_fqsm',
		event: 'click',
		handler: badw_fqsm
	},{
		element: '#badw_wtgry',
		event: 'click',
		handler: badw_wtgry
	},{
		element: '#badw_lsxx',
		event: 'click',
		handler: badw_lsxx
	},{
		element: '#badw_cydwsz',
		event: 'click',
		handler: badw_cydwsz
	}];
	
	function badw_fqsm(){
		app.router.load("smsy/badw/ywbl/smlist/smlist",loginuser)
	}
	function badw_wtgry(){
		app.router.load("smsy/badw/ywbl/wtgry/wtgry",loginuser)
	}
	function badw_lsxx(){
		app.router.load("smsy/badw/ywbl/lsxx/lsxx",loginuser)
	}
	function badw_cydwsz(){
		app.router.load("smsy/badw/ywbl/cydwsz/cydwsz",loginuser)
	}

	function init(query){
		loginuser=query;
		YwblView.render({
			model: loginuser,
			bindings: bindings
		});
		
	}
	
	return {
		init: init
	};
});
