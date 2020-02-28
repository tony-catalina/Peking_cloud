define(['hbs!js/smsy/jgdw/ywbl/ywsp/ywsp'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$('#ryxx').html(viewTemplate(params));
		bindEvents(params.bindings);
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
