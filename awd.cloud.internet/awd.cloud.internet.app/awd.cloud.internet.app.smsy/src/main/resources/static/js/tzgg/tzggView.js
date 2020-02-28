define(['hbs!js/tzgg/tzgg2','js/tools'], function(viewTemplate,tools) {
	var $ = Framework7.$;

	function render(params) {
		$.get("/tzgg/getTzgg",{},function(data){
			data=JSON.parse(data);
			$('.pages').html(viewTemplate(data));
			bindEvents(params.bindings);
		});
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
