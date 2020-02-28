define(['hbs!js/smsy/badw/ywbl/smlist/fqsm/fqsm'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$('.pages').html(viewTemplate(params));
		bindEvents(params.bindings);		
	}

	/*function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].element!=undefined){
				if($(bindings[i].element))
					$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
			}
			
		}
	}*/

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
