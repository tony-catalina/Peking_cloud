define(['hbs!js/smsy/jgdw/ywbl/fcjl/showSmsy',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	var fczt = params.fczt;
    	var phase = "4"
    	if (fczt == "3") {
    		phase = "5"
		}
    	$.ajax({
			url:'/smsy/getKssSmsyListByFcjl',
			type: "POST",
			data:{
				//'jsbh':params.model.dwbh,
				'fcuuid':params.fcuuid,
				"phase":phase
			},
			success:function(res){
				res=JSON.parse(res)
		        var search = params.search;
				var tabClass = params.tabClass;
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
