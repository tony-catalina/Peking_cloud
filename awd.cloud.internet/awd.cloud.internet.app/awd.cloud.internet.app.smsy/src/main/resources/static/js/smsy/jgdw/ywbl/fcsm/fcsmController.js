define(["app","js/tools","js/smsy/jgdw/ywbl/fcsm/fcsmView"], 
		function(app,tools, FcsmView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#addTzsm',
		event: 'click',
		handler: plTzsm
	/*},{
		element: '.action1',
		event: 'click',
		handler: blsm*/
	},{
		element: '.action2',
		event: 'click',
		handler: flwsbq
	},{
		element: '.swipeout .item-title',
		event: 'click',
		handler: swipeoutclick	
	}];
	
	function init(query){
		loginuser=query.user==undefined?query:query.user;
		FcsmView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function back(){
		loginuser["isLoad"] = false;
		app.router.load("smsy/jgdw/jgdw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/jgdw/ywbl/ywbl",loginuser);	
	    }, 10);
	}
	
	function bldsm(){
		app.router.load("smsy/jgdw/ywbl/fcsm/bldsm/bldsm",loginuser);
	}
	
	function swipeoutclick(){
		var uuid=$(this).attr("uuid");		
		$.ajax({
			url:'/pcs/ryxx',
			type: "POST",
			data:{"id":uuid},
			success:function(res){
				var myPhotoBrowserStandalone
				res=JSON.parse(res);
				myPhotoBrowserStandalone = app.f7.photoBrowser({
				    photos : res
				});
				myPhotoBrowserStandalone.open();
			}
		});
	}
	
	function distinct(arr) {	//数组去重
	    let result = []
	    let obj = {}
	    for (let i of arr) {
	        if (!obj[i]) {
	            result.push(i)
	            obj[i] = 1
	        }
	    }
	    return result
	}
	
	function plTzsm(){
		//var id=$(this).attr("dataid");
		var ids = [];
		//var badws = new Set();
		var badws = [];
		$('input[name="my-checkbox"]').each(function() {
			var id = $(this).val();
			var badw = $(this).attr("badw");
	        if ($(this).prop('checked') ==true) {
	        	ids.push(id);
	        	badws.push(badw);
	        	//badws.add(badw);
	        }
		});
		
		badws = distinct(badws);
		
		if (ids.length == 0) {
			app.f7.alert("","请选中一条记录办理!");
			return;
		}
		app.router.load("smsy/jgdw/ywbl/fcsm/sm/sm",{"user":loginuser,"id":ids,"badw":badws});
	}
	
	function blsm(){
		var id=$(this).attr("dataid");
		app.router.load("smsy/jgdw/ywbl/fcsm/sm/sm",{"user":loginuser,"id":id})		
	}
	
	function flwsbq(){
		var dataid=$(this).attr("dataid");
		app.f7.confirm("<textarea id='f7ta' style='width:100%;resize:none;margin-top:5px;height:90px;font-size:16px;'></textarea>","原因",function(){
			if (tools.isBlank($("#f7ta").val())) {
				app.f7.alert("","请填写原因!");
				return;
			}
			$.ajax({
				url:'/smsy/change',
				type: "POST",
				data:{"id":dataid,"phase":2,"jsly":$("#f7ta").val()},
				success:function(res){
					res=JSON.parse(res);
					if(res.data=="1"){
						init(loginuser);
					}				
				}
			});
	    });
	}
	
	
	return {
		init: init
	};
});
