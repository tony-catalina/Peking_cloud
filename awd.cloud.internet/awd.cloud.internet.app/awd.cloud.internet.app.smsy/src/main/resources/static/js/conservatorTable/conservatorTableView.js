define(['hbs!js/conservatorTable/conservatorTable',"js/conservatorTable/showUserTableView","app"], 
function(viewTemplate,ShowUserView,app) {
    var $ = Framework7.$;

    function render(params) { 
		$('.pages').html(viewTemplate(params));
		bindEvents(params.bindings);
		ShowUserView.render({
            model: params.model,
            bindings: params.bindings,
            state:"R2",
            tabClass:"tab1"
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
