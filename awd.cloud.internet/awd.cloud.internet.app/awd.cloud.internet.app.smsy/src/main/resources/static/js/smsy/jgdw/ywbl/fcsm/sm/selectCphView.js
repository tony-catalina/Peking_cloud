define(['hbs!js/smsy/jgdw/ywbl/fcsm/sm/selectCph',"app"], function(viewTemplate,app) {
	var $ = Framework7.$;

	function render(params) {
		$.post("/clgl/getClglList",{"jsbh":params.model.dwbh},function(data){
			data=JSON.parse(data)
			$('.popup.popup-selectCph').html(viewTemplate(data));
			bindEvents(params.bindings);
			var mySearchbar = app.f7.searchbar('.searchbar.searchbar-cph', {
		    	searchList: '.list-block-search',
		    	searchIn: '.item-title'
		    });  
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
