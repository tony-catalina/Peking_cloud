define(['hbs!js/smsy/jgdw/ywbl/fcjl/showFcjl',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	$.ajax({
			url:'/fcjl/getFcjlList',
			type: "POST",
			data:{
				'jsbh':params.model.dwbh,
				'fczt':params.fczt
			},
			success:function(res){
				res=JSON.parse(res)
		        var search = params.search;
				var tabClass = params.tabClass;
				$('.'+ tabClass +'.showTab').html(viewTemplate(res));
				bindEvents(params.bindings);
				var mySearchbar = app.f7.searchbar('.searchbar.searchbar-showFcjl', {
					searchList: '.list-block-search',
					searchIn: '.item-title'
				});  
		        if (search == "true") {
		        	var input = mySearchbar.input[0].value ;
					mySearchbar.search(input);
				}else {
					mySearchbar.disable();
				}
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
