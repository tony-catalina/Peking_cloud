define(['hbs!js/tsyyjl/tsyyjlxx'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		var yyid = params.model;
		$('.page-content').html(viewTemplate({}));
		$.get("/tsyy/getTsyy",{"yyid":yyid},function(data){
			data=JSON.parse(data);
			if(data.status == 200){
				$("#yyrq").val(data.result[0].yyrq);
				$("#jsmc").val(data.result[0].jsbhString);
				if(data.result[0].state == "R2" && data.result[0].revokes=="0"){
					$("#state").val("办理中");
				}else if(data.result[0].state == "R2" && data.result[0].revokes=="1"){
					$("#state").val("撤销");
				}else if(data.result[0].state == "R3" && data.result[0].revokes=="0"){
					$("#state").val("办理完成");
				}
			}
		})
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
