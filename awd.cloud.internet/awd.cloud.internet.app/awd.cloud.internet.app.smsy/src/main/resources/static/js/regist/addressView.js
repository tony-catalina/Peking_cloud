define(['hbs!js/regist/address',"app"], function(viewTemplate,app) {
	var $ = Framework7.$;
	function render(params) {
		console.info(params)
		$('.popup.popup-addressInput').html(viewTemplate(params));
		bindEvents(params.bindings);
		bindaction(params.callback);
	}
	
	function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].event!=undefined)
			$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
		}
	}
	
	var bindaction = function(callback) {
		$(".addressCancel").on('click',function(){
			app.f7.closeModal(".popup-addressInput");
		});
		$(".addressSubmit").on('click',function(){
			var context = $(".addressText").val();
			callback(context);
			app.f7.closeModal(".popup-addressInput");
		});
	}

	return {
		render: render
	}
});
