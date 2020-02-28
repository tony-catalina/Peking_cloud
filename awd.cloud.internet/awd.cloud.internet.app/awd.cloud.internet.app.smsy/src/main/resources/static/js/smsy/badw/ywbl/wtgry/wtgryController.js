define(["app","js/smsy/badw/ywbl/wtgry/wtgryView", "js/tools"], function(app, WtgryView, tools) {

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
		element: '.action1',
		event: 'click',
		handler: wsxx	
	},{
		element: '.action2',
		event: 'click',
		handler: cxcm	
	},{
		element: '.action3',
		event: 'click',
		handler: jsyy	
	}];

	function init(query){
		loginuser=query;
		WtgryView.render({
			model: query,
			bindings: bindings
		});
		
	}

	function back(){
		loginuser["isLoad"] = false;
		app.router.load("smsy/badw/badw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/badw/ywbl/ywbl",loginuser);
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
	function cxcm(){
		var dataid=$(this).attr("dataid");
		app.f7.confirm("确定要撤销上门业务？","撤销确认",function(){			
			app.f7.prompt("",'原因', function (value) {
				if (tools.isBlank(value)) {
					app.f7.alert("","请填写原因!");
					return;
				}
				$.ajax({
					url:'/smsy/change',
					type: "POST",
					data:{"id":dataid,"phase":6,"chyy":value},
					success:function(res){
						res=JSON.parse(res);
						if(res.data=="1"){
							init(loginuser);
						}				
					}
				});
		    });	
		},function(){
			
		});
	}
	function wsxx(){
		var dataid=$(this).attr("dataid");
		var data={ 
				wsxx : true,
				wsxxid : dataid,
				xyrxm:$(this).attr("xyrxm"),
				gyjs:$(this).attr("gyjs"),
				gyjsString:$(this).attr("gyjsString")
			};
		Object.assign(loginuser, data); 
		
		app.router.load("smsy/badw/ywbl/smlist/fqsm/fqsm",loginuser);
		
	}
	function jsyy(){
		var data=$(this).attr("jsly");
		app.f7.alert("",data);
	}
	return {
		init: init
	};
});
