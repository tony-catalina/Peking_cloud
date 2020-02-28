define(["app","js/smsy/badw/ywbl/lsxx/lsxxView","js/tools"],
function(app, LsxxView,tools) {

	var $ = Framework7.$;
	var loginuser={};
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back		
	},{
		element: '.swipeout .item-title',
		event: 'click',
		handler: swipeoutclick	
	},{
		element: '.chyy',
		event: 'click',
		handler: chyyshow	
	}];

	function init(query){
		loginuser=query;
		LsxxView.render({
			model: query,
			bindings: bindings
		});
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

	function back(){
		loginuser["isLoad"] = false;
		app.router.load("smsy/badw/badw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/badw/ywbl/ywbl",loginuser);
	    }, 10);
	}
	
	function chyyshow(){
		app.f7.alert("",$(this).attr("chyy"));
	}
	return {
		init: init,
	};
});
