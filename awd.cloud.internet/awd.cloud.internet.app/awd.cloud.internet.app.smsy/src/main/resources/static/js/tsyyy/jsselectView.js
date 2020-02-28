define(['hbs!js/tsyyy/jsselect'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {		
		$.post("/unit/getUnitList",{"type":'1'},function(data){
			data=JSON.parse(data)
			$('#select').html(viewTemplate(data));
			bindEvents(params.bindings);
		})
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
