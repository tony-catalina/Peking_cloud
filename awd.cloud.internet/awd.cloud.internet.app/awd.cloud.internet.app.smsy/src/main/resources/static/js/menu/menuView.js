define(['hbs!js/menu/menu'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$('.toolbar').remove();

		var smsyrs = "0";
		$.ajax({
			//async:false,
			url:'/smsy/getSmsyList',
			type: "POST",
			data:{"phase":"1"},
			success:function(res){
				res=JSON.parse(res)
				smsyrs = res.total;
				params["smsyrs"] = smsyrs;
			},
			complete:function(){
				$('.pages').html(viewTemplate(params));
				bindEvents(params.bindings);
			}
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
