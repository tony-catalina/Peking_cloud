define(['hbs!js/tsyyjl/tsyyjl'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {		
		$.get("/tsyy/getTsyy",{},function(data){
			data=JSON.parse(data)
			if(data.status == 200){
				$('.page-content').html(viewTemplate(data));
				bindEvents(params.bindings);
			}			
		});
//		$('.page-content').html(viewTemplate());
//		bindEvents(params.bindings);
	}

	function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].event!=undefined)
			$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
		}
	}

	return {
		render: render
	}
});
