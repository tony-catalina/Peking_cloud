define(['hbs!js/smsy/badw/ywbl/ywbl'], function(viewTemplate) {
	var $ = Framework7.$;
	
	function render(params) {
		//点击业务办理选项卡时，查询未通过人数
		var wtgrs = "0";
		$.ajax({
			url:'/smsy/getSmsyDsm',
			type: "POST",
			//async:false,
			data:{"badw":params.model.dwbh,"phase":"2"},
			success:function(res){
				res=JSON.parse(res)
				wtgrs = res.total;
				params["wtgrs"] = wtgrs;
			},
			complete:function(){
				$('.ywcontent').html(viewTemplate(params));
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
