define(['hbs!js/smsy/badw/dsm/showSmsy',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	$.ajax({
    		url:'/smsy/getBadwSmsyListByFcjl',
			type: "POST",
			data:{
				'badw':params.model.dwbh,
				'fcuuid':params.fcuuid
			},
			success:function(res){
				res=JSON.parse(res)
				$('.smsy-content_'+ params.fcjlid).html(viewTemplate(res));
				bindEvents(params.bindings);
				app.f7.accordionOpen(params.item)
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
