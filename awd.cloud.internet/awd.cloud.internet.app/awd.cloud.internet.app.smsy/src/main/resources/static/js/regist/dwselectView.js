define(['hbs!js/regist/dwselect',"app"], function(viewTemplate,app) {
	var $ = Framework7.$;

	function render(params) {
		$.post("/unit/getUnitList",{"type":params.model},function(data){
		//$.post("/interface/getOrgList",{"type":params.model},function(data){
			data=JSON.parse(data)
			$('.popup.popup-selectBadw').html(viewTemplate(data));
			bindEvents(params.bindings);
			var mySearchbar = app.f7.searchbar('.searchbar.searchbar-badw', {
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
