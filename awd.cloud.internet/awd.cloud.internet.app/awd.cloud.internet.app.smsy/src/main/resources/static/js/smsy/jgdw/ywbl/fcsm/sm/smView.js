define(['hbs!js/smsy/jgdw/ywbl/fcsm/sm/sm',"app"], function(viewTemplate,app) {
	var $ = Framework7.$;

	function render(params) {
		$('.pages').html(viewTemplate(params));		
		console.info(params)
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
