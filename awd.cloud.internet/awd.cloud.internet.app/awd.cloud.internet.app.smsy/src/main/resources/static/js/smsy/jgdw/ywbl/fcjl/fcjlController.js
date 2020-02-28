define(["app","js/tools",
	"js/smsy/jgdw/ywbl/fcjl/fcjlView",
	"js/smsy/jgdw/ywbl/fcjl/showFcjlView",
	"js/smsy/jgdw/ywbl/fcjl/showSmsyView", 
	"js/smsy/jgdw/ywbl/fcjl/cxfcView"], 
		function(app,tools,FcjlView,ShowFcjlView,ShowSmsyView,CxfcView) {
	var loginuser={};
	var selectFczt="1";
	var $ = Framework7.$; 
	var $$ = Dom7;
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
    },{
    	element: '.cxfc_back',
    	event: 'click',
    	handler: showTab2
    },{
    	element: '#tab1',
    	event: 'show',
    	handler: showTab1
    },{
    	element: '#tab2',
    	event: 'show',
    	handler: showTab2
    },{
    	element: '#tab3',
    	event: 'show',
    	handler: showTab3
    },{
    	element: '.first-item',
    	event: 'click',
    	handler: clickFirstItem
    },{
    	element: '.delay',	//延迟发车
    	event: 'click',
    	handler: delay
    },{
    	element: '.delete',	//取消发车
    	event: 'click',
    	handler: deleteFcjl
    },{
    	element: '.replay',	//重新发车
    	event: 'click',
    	handler: replay
    },{
    	element: '.jgdw_qxsy',	//取消收押
    	event: 'click',
    	handler: jgdw_qxsy
	},{
		element: '.rydetail',
		event: 'click',
		handler: getRyPhoto	
	}];
	
	function init(query){
		loginuser=query;
		FcjlView.render({
			model: query,
			bindings: bindings
		});
		Handlebars.registerHelper("is_showbutton",function(v1,options){
			if(v1 == "5"){
				return options.fn(this);
			}else {
				return options.inverse(this);
			}
		});
		Handlebars.registerHelper("is_ywcjl",function(v1,options){
			if(v1 == "3"){
				return options.fn(this);
			}else {
				return options.inverse(this);
			}
		});
        Handlebars.registerHelper("equal",function(v1,options){
			   if(v1 == "1"){
			     //满足添加继续执行
			     return options.fn(this);
			   }else if (v1 == "2") {
			     //不满足条件执行{{else}}部分
			     return options.inverse(this);
			  }
        });
	}
	
	function back(){
		console.info(loginuser)
		loginuser["isLoad"] = false;
		app.router.load("smsy/jgdw/jgdw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/jgdw/ywbl/ywbl",loginuser);	
	    }, 10);
	}
	
    function showTab1() {
    	selectFczt = "1";
    	ShowFcjlView.render({
            model: loginuser,
            bindings: bindings,
            fczt:"1",
            tabClass:"tab1"
        });
    }
    
    function showTab2() {
    	selectFczt = "2";
    	ShowFcjlView.render({
            model: loginuser,
            bindings: bindings,
            fczt:"2",
            tabClass:"tab2"
        });
    }
    
    function showTab3() {
    	selectFczt = "3";
    	ShowFcjlView.render({
    		model: loginuser,
    		bindings: bindings,
    		fczt:"3",
    		tabClass:"tab3"
    	});
    }
    
    
    function clickFirstItem(ele) {
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
    		fczt:selectFczt,
    		item : this,
    	});
    	ele.stopPropagation();
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
    
    
    function delay(){
    	console.info("延迟发车")
    	var id = $(this).attr("dataid");
    	var fcuuid = $(this).attr("fcuuid");
    	var fczt = $(this).attr("fczt");
    	updateFcjl(id,fcuuid,2,fczt);
    }
    function deleteFcjl(){
    	console.info("取消发车")
		var id = $(this).attr("dataid");
    	var fcuuid = $(this).attr("fcuuid");
    	var fczt = $(this).attr("fczt");
    	updateFcjl(id,fcuuid,4,fczt);
    }
    function replay(){
    	console.info("重新发车")
    	var id = $(this).attr("dataid");
    	var fcuuid = $(this).attr("fcuuid");
    	var cph = $(this).attr("cph");
    	var mjsl = $(this).attr("mjsl");
    	var basl = $(this).attr("basl");
    	var yssl = $(this).attr("yssl");
    	app.f7.popup('.popup-addressInput');
    	//updateFcjl(id,fcuuid,1,fczt);
    	var fcjl = {
    		"id":id,
    		"fcuuid":fcuuid,
    		"cph":cph,
    		"mjsl":mjsl,
    		"basl":basl,
    		"yssl":yssl
    	}
    	
    	CxfcView.render({
    		model: fcjl,
    		bindings: bindings,
    		callback: function(){
    			ShowFcjlView.render({
		    		model: loginuser,
		    		bindings: bindings,
		    		fczt:2,
		    		tabClass:"tab2",
		    		search:"true"
		    	});
    		}
    	});
    	$(".popup-addressInput").css({
    		'height': '460px',
    		'top': '15%'
    	})
    }
    
	function updateFcjl(id,fcuuid,action,fczt){
		$.ajax({
			url:'/fcjl/updateFcjl',
			type: "POST",
			data:{
				"id":id,
				"fcuuid":fcuuid,
				"fczt":action
			},
			success:function(data){
				//data=JSON.parse(data)
				ShowFcjlView.render({
		    		model: loginuser,
		    		bindings: bindings,
		    		fczt:fczt,
		    		tabClass:"tab"+fczt,
		    		search:"true"
		    	});
			},
			error:function(){
				app.f7.alert("","操作失败！");
			}
		});
	}
	
	function updateFcjlAfterDsm(fcuuid,badw) {
		$.ajax({
			url:'/fcjl/updateFcjlAfterDsm',
			type: "POST",
			async:false,
			data:{
				"fcuuid":fcuuid,
				"badw":badw,
				"fczt":"3"
			},
			success:function(res){
				
				if (selectFczt == "1") {
					showTab1()
				}else if (selectFczt == "2") {
					showTab2()
				}else if (selectFczt == "3") {
					showTab3()
				}
				
				/*ShowFcjlView.render({
		    		model: loginuser,
		    		bindings: bindings,
		    		tabClass:"tab"+selectFczt,
		    		search:"true"
		    	});*/
			}
		});
	}
	
	function jgdw_qxsy(){
		var dataid=$(this).attr("dataid");
		var fcuuid=$(this).attr("fcuuid");
		var badw=$(this).attr("badw");	
		app.f7.confirm("确定要取消该人的上门业务？","取消确认",function(){		
			$.ajax({
				url:'/smsy/change',
				type: "POST",
				async:false,
				data:{"id":dataid,"phase":3},
				success:function(res){
					res=JSON.parse(res);
					if(res.data=="1"){
						updateFcjlAfterDsm(fcuuid,badw)
					}				
				}
			});
		});	
	}
	
	
	return {
		init: init
	};
});
