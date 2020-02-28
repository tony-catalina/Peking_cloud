define(['hbs!js/smsy/jgdw/ywbl/fcjl/fcjl',"js/smsy/jgdw/ywbl/fcjl/showFcjlView"], function(viewTemplate,ShowFcjlView) {
	var $ = Framework7.$;

	function render(params) {
		$('.pages').html(viewTemplate(params));
		bindEvents(params.bindings);
		ShowFcjlView.render({
            model: params.model,
            bindings: params.bindings,
            fczt:"1",
            tabClass:"tab1"
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
