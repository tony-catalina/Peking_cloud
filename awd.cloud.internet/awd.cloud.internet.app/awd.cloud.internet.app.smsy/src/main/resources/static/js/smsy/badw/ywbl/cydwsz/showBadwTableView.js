define(['hbs!js/smsy/badw/ywbl/cydwsz/showBadwTable',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	var url = "";
    	var tab = params.tabClass;
    	console.info("tab---"+tab)
    	if (tab == "tab2") {
    		url = "/unit/getUnitList";
		}else if (tab == "tab1") {
			url = "/unit/getCommonlyUnitList";
		}
    	
    	$.ajax({
			url:url,
			type: "POST",
			data:{
				'type':"1",
				"userid":params.model.userid
			},
			success:function(res){
				res=JSON.parse(res)
		        var search = params.search;
				var tabClass = params.tabClass;
				res["tab"] = tab;
				$('.'+ tabClass +'.showTab').html(viewTemplate(res));
				bindEvents(params.bindings);
				var mySearchbar = app.f7.searchbar('.searchbar.searchbar-badw', {
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
