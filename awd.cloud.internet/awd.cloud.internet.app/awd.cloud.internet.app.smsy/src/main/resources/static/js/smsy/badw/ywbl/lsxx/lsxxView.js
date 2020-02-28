define(['hbs!js/smsy/badw/ywbl/lsxx/lsxx'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {	
		$.ajax({
			url:'/smsy/getSmsyDsm',
			type: "POST",
			data:{"badw":params.model.dwbh,"phase":"5,6"},
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
