define(['hbs!js/conservatorTable/showUserTable',"app"], function(viewTemplate,app) {
    var $ = Framework7.$;

    function render(params) { 
    	console.info(params)
    	$.ajax({
			url:'/userlist',
			type: "GET",
			data:{
				'dwbh':params.model.jsbh,
				'state':params.state
			},
			success:function(res){
				res=JSON.parse(res)
		        var search = params.search;
				var tabClass = params.tabClass;
				$('.'+ tabClass +'.showTab').html(viewTemplate(res));
				bindEvents(params.bindings);
				var mySearchbar = app.f7.searchbar('.searchbar.searchbar-showUserTable', {
					searchList: '.list-block-search',
					searchIn: '.item-title'
				});  
		        if (search == "true") {
		        	var input = mySearchbar.input[0].value ;
		        	console.info(input);
		        	//mySearchbar.enable();
		        	//mySearchbar.disable();
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
