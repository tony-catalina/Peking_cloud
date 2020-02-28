define(['hbs!js/smsy/jgdw/ywbl/shwtg/shwtg'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		console.log(params);
		$.ajax({
			url:'/smsy/getSmsyList',
			type: "POST",
			data:{"phase":"2"},
			success:function(res){
				res=JSON.parse(res)
				$('.pages').html(viewTemplate(res));
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
