define(["app","js/smsy/badw/ywbl/smlist/smlistView","js/tools"], function(app,SmListView, tools) {

	var $ = Framework7.$;
	var loginuser={};

	var binding=[{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#addsm',
		event: 'click',
		handler: fqsm
	},{
		element: '.swipeout .item-title',
		event: 'click',
		handler: swipeoutclick
	},{
		element: '.action2',
		event: 'click',
		handler: qxsm
	}];


	function init(query){
		loginuser=query;
		SmListView.render({
			model: query,
			bindings: binding
		});

	}
	function back(){
		loginuser["isLoad"] = false;
		app.router.load("smsy/badw/badw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/badw/ywbl/ywbl",loginuser);
	    }, 10);
	}
	function fqsm(){
		
		delete loginuser.wsxx;
		delete loginuser.wsxxid;
		delete loginuser.xyrxm;
		delete loginuser.gyjs;
		delete loginuser.gyjsString;
		
		app.router.load("smsy/badw/ywbl/smlist/fqsm/fqsm",loginuser);		
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
	function qxsm(){
		var dataid=$(this).attr("dataid");
		app.f7.confirm("<textarea id='f7ta' style='width:100%;resize:none;margin-top:5px;height:90px;font-size:16px;'></textarea>","原因",function(){
			if (tools.isBlank($("#f7ta").val())) {
				app.f7.alert("","请填写原因!");
				return;
			}
			$.ajax({
				url:'/smsy/change',
				type: "POST",
				data:{"id":dataid,"phase":6,"chyy":$("#f7ta").val()},
				success:function(res){
					res=JSON.parse(res);
					if(res.data=="1"){
						init(loginuser);
					}				
				}
			});
	    });	
		
	}
	return {
		init: init
	};
});
