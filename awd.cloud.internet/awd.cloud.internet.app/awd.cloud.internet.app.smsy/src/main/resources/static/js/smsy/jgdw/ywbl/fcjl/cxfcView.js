define(['hbs!js/smsy/jgdw/ywbl/fcjl/cxfc',"js/tools","app"], function(viewTemplate,tools,app) {
	var $ = Framework7.$;

	function render(params) {
		console.info(params)
		$('.popup.popup-addressInput').html(viewTemplate(params));
		bindEvents(params.bindings);
		bindaction(params.callback);
	}

	function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].event!=undefined){
				$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
			}
		}
	}

	var bindaction = function(callback) {
		$("#cancel").on('click',function(){
			app.f7.closeModal(".popup-addressInput");
		});
		$("#submit").on('click',function(){
			
			var data = app.f7.formToJSON("#fcfm");
			if (tools.isBlank(data.fcsj) || tools.isBlank(data.smsyrq)) {
				app.f7.alert("","请完善相关信息!");
				return;
			}
			data.fcsj = data.smsyrq + " " + data.fcsj;
			$.ajax({
				url:'/fcjl/updateFcjl',
				type: "POST",
				data:data,
				success:function(data){
					callback();
				},
				error:function(){
					app.f7.alert("","操作失败！");
				}
			});
			app.f7.closeModal(".popup-addressInput");
		});
	}
	return {
		render: render
	}
});
