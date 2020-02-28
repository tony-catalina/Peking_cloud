define(function() {
	var $ = Framework7.$;

	/**
	 * Init router, that handle page events
	 */
    function init() {
		$(document).on('pageBeforeInit', function (e) {
			var page = e.detail.page;
			load(page.name, page.query);
		});
    }

	/**
	 * Load (or reload) controller from js code (another controller) - call it's init function
	 * @param controllerName
	 * @param query
	 */
	function load(controllerName, query) {
		require(['js/' + controllerName + 'Controller'], function(controller) {
			controller.init(query);
		});
		//history.pushState(null, null, document.URL);
		/*history.pushState(null, null, "#");
		window.addEventListener("popstate",function(e) {  
		  //history.pushState(null, null, document.URL);
			history.pushState(null, null, "#");
		}, false);*/
	}

	/*function pushHistory() {  
		  var state = {  
		    title: controllerName,  
		    url: "#"+controllerName  
		  };  
		  window.history.pushState(state, controllerName, "#"+controllerName);  
	}
	
	var listenBack = function(){
		pushHistory();
		window.addEventListener("popstate", function(e) {  
			console.log(e);
			alert("我监听到了浏览器的返回按钮事件啦"); 
		}, false);
	}*/
	
	return {
		init: init,
		load: load,
    };
});
