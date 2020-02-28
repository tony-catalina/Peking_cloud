define(['hbs!js/smsy/jgdw/dsm/showSmsy',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	$.ajax({
			url:'/smsy/getKssSmsyListByFcjl',
			type: "POST",
			data:{
				'jsbh':params.model.dwbh,
				'phase':"4",
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
