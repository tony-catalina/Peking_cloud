define(["app","js/smsy/jgdw/ywbl/clgl/clglView"], 
		function(app, ClglView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#addclgl',
		event: 'click',
		handler: addclgl	
	},{
		element: '.delete',
		event: 'click',
		handler: deleteClgl
	}];
	
	function init(query){
		loginuser=query;
		ClglView.render({
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
	
	
	function addclgl(){
		app.router.load("smsy/jgdw/ywbl/clgl/xzcl/xzcl",loginuser);
	}
	function deleteClgl(){
		var id=$(this).attr("dataid");	
		$.ajax({
			url:'/clgl/deleteByid',
			type: "POST",
			data:{"id":id},
			success:function(data){
				data=JSON.parse(data)
				if (data.result == "1"){
					app.f7.alert("","删除成功！");
					app.router.load("smsy/jgdw/ywbl/clgl/clgl",loginuser);
				} else {
					app.f7.alert("","删除失败！");
				}
			},
			error:function(){
				app.f7.alert("","删除失败！");
			}
		});
	}
	
	return {
		init: init
	};
});
