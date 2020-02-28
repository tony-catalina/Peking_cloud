define(["app","js/smsy/jgdw/ywbl/clgl/xzcl/xzclView","js/tools"], 
function(app, XzclView,tools) {

	var $ = Framework7.$;
	var loginuser={};

	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#saveClgl',
		event: 'click',
		handler: saveClgl
	}];

	function init(query){
		loginuser=query;
		XzclView.render({
			model: query,
			bindings: bindings			
		});
	}

	function saveClgl(){
		$.ajax({
			url:"clgl/addClgl",
			type:"POST",
			cache: false,
			data:app.f7.formToJSON("#fm"),
			success:function(data){
				data=JSON.parse(data)
				if (!tools.isBlank(data.result)){
					app.f7.alert("","添加成功！");
					back();
				} else {
					app.f7.alert("","添加失败！");
				}
			},
			error:function(){
				app.f7.alert("","新增失败！");
			}
		});
	}

	function back(){
		app.router.load("smsy/jgdw/ywbl/clgl/clgl",loginuser);
	}
	return {
		init: init
	};
});
