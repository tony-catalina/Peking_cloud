define(['hbs!js/smsy/badw/ywbl/cydwsz/cydwsz'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {	
		//$('.popup.popup-selectBadw').html(viewTemplate(params));
		$('.pages').html(viewTemplate(params));
		bindEvents(params.bindings);
		params.bindings[1].handler();
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
