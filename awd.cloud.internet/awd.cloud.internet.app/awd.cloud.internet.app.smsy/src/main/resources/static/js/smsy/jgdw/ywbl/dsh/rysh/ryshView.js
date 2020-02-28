define(['hbs!js/smsy/jgdw/ywbl/dsh/rysh/rysh'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$('.page-content').html(viewTemplate(params));
		bindEvents(params.bindings);
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
