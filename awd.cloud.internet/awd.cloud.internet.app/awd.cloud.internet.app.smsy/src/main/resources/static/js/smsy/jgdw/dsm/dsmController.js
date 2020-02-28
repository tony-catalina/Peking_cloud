define(["app","js/tools","js/smsy/jgdw/dsm/dsmView","js/smsy/jgdw/dsm/showSmsyView"], 
		function(app,tools,DsmView,ShowSmsyView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '.action1',
		event: 'click',
		handler: xcjs
	},{
		element: '.action2',
		event: 'click',
		handler: syrs
    },{
    	element: '.first-item',
    	//event: 'open',
    	event: 'click',
    	handler: openFirstItem
	},{
		element: '.rydetail',
		event: 'click',
		handler: getRyPhoto	
	}];
	
	function init(query){
		loginuser=query.user==undefined?query:query.user;
		DsmView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function back(){
		app.router.load("menu/menu",loginuser);
	}
	
	function bldsm(){
		app.router.load("smsy/jgdw/dsm/bldsm/bldsm",loginuser);
	}
	
	function getRyPhoto(){
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
	
	function updateFcjlAfterDsm(fcuuid,badw) {
		$.ajax({
			url:'/fcjl/updateFcjlAfterDsm',
			type: "POST",
			data:{
				"fcuuid":fcuuid,
				"badw":badw,
				"fczt":"3"
			},
			success:function(res){
				//res=JSON.parse(res);
				init(loginuser);
			}
		});
	}
	
	
	function xcjs(){
		var dataid=$(this).attr("dataid");		
		var fcuuid=$(this).attr("fcuuid");		
		var badw=$(this).attr("badw");	
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
						//init(loginuser);
						updateFcjlAfterDsm(fcuuid,badw);
					}				
				}
			});
	    });
	}
	
	function syrs(){
		var dataid=$(this).attr("dataid");
		var fcuuid=$(this).attr("fcuuid");		
		var badw=$(this).attr("badw");		
		$.ajax({
			url:'/smsy/change',
			type: "POST",
			data:{"id":dataid,"phase":5},
			success:function(res){
				res=JSON.parse(res);
				if(res.data=="1"){
					//init(loginuser);
					updateFcjlAfterDsm(fcuuid,badw);
				}				
			}
		});
	}
	
    function openFirstItem(ele) {
    	var flag = $(this).hasClass("accordion-item-expanded");
    	if (flag) {
			return ;
		}
    	var fcjlid = $(this).find(".fcjlid").val();
    	var fcuuid = $(this).find(".fcuuid").val();
    	ShowSmsyView.render({
    		model: loginuser,
    		bindings: bindings,
    		fcuuid:fcuuid,
    		fcjlid:fcjlid,
    		item : this,
    	});
		ele.stopPropagation();
    }
	
	return {
		init: init
	};
});
