define(['hbs!js/smsy/jgdw/dsm/dsm'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$.ajax({
			url:'/fcjl/getFcjlList',
			type: "POST",
			data:{
				'jsbh':params.model.dwbh,
				'fczt':"1"
				//"phase":"4"
			},
			success:function(res){
				res=JSON.parse(res)
				var template = '<div class="content-block">暂无数据</div>';
				if (res.total > 0) {
					template = viewTemplate(res)
				}
				$('.jgdwcontent').html(template);
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
