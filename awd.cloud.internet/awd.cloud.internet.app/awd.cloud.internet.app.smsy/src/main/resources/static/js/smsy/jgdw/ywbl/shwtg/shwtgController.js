define(["app","js/smsy/jgdw/ywbl/shwtg/shwtgView"], 
		function(app, ShwtgView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '.swipeout .item-title',
		event: 'click',
		handler: swipeoutclick	
	},{
		element: '.action1',
		event: 'click',
		handler: showinfo
	}];
	
	function init(query){
		loginuser=query;
		ShwtgView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function back(){
		console.info(loginuser)
		loginuser["isLoad"] = false;
		app.router.load("smsy/jgdw/jgdw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/jgdw/ywbl/ywbl",loginuser);	
	    }, 10);
	}
	
	function swipeoutclick(){
		var uuid=$(this).attr("uuid");		
		$.ajax({
			url:'/pcs/ryxx',
			type: "POST",
			data:{"id":uuid},
			success:function(res){
				var myPhotoBrowserStandalone
				res=JSON.parse(res);
				myPhotoBrowserStandalone = app.f7.photoBrowser({
				    photos : res
				});
				myPhotoBrowserStandalone.open();
			}
		});
	}
	
	function showinfo(){
		app.f7.alert("",$(this).attr("jsly"));
	}
	
	return {
		init: init
	};
});
