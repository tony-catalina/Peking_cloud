define(["app","js/smsy/jgdw/ywbl/fcsm/sm/smView","js/smsy/jgdw/ywbl/fcsm/sm/selectCphView","js/tools"], function(app,SmView,CphView, tools) {

	var $ = Framework7.$;
	var loginuser={};
	
	var binding=[{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#tzsm',
		event: 'click',
		handler: tzsm
	},{
		element:'#chooseCph',
		event:'click',
		handler: chooseCph
	},{
		element:'.cph',
		event:'click',
		handler: cphSelected
	}];
	

	function init(query){	
		loginuser=query;
		SmView.render({
			model: query,
			bindings: binding
		});
		
	}
	function back(){
		app.router.load("smsy/jgdw/ywbl/fcsm/fcsm",loginuser);
	}
	
	function chooseCph(){
		CphView.render({
			model: loginuser,
			bindings: binding
		});
		app.f7.popup(".popup-selectCph");
	}
	
	function cphSelected(){
		$('#chooseCph').val($(this).attr('value'));
		$('#cphfm').val($(this).attr('value'));
		app.f7.closeModal(".popup-selectCph")
	}
	
	function tzsm(){
		var data = app.f7.formToJSON("#fm");
		if (tools.isBlank(data.fcsj) || tools.isBlank(data.smsyrq) || tools.isBlank(data.cph)) {
			app.f7.alert("","请完善相关信息!");
			return;
		}
		
		data.fcsj = data.smsyrq + " " + data.fcsj;
		
		$.ajax({
			url:'/smsy/smSave',
			type: "POST",
			data:data,
			success:function(res){
				res=JSON.parse(res);
				if(res.status==200){
					app.router.load("smsy/jgdw/ywbl/fcsm/fcsm",loginuser);
				}				
			}
		});
	}
	
	return {
		init: init
	};
});
