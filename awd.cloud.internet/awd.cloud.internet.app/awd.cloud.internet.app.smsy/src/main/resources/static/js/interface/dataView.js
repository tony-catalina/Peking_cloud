define(['hbs!js/interface/data',"app"], function(viewTemplate,app) {
	var $ = Framework7.$;

	function render(params) {
		//$('.pages').html(viewTemplate(params));
		console.info(params);
		$('.popup.popup-interface').html(viewTemplate(params));
		bindEvents(params.bindings);
		app.f7.popup(".popup-interface");
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
