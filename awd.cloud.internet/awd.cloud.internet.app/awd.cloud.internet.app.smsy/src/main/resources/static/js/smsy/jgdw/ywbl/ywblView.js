define(['hbs!js/smsy/jgdw/ywbl/ywbl'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		//点击业务办理选项卡时，查询待审核人数
		var dshrs = "0";
		$.ajax({
			//async:false,
			url:'/smsy/getSmsyList',
			type: "POST",
			data:{"phase":"1"},
			success:function(res){
				res=JSON.parse(res)
				dshrs = res.total;
				params["dshrs"] = dshrs;
			},
			complete:function(){
				$('.jgdwcontent').html(viewTemplate(params));
				bindEvents(params.bindings);
			}
		});
		
	}

	function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].event!=undefined){
				$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
			}
		}
	}

	return {
		render: render
	}
});
