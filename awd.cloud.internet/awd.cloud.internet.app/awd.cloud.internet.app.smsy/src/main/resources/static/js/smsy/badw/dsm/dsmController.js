define(["app","js/smsy/badw/dsm/dsmView","js/smsy/badw/dsm/showSmsyView","js/tools"], 
		function(app,DsmView,ShowSmsyView,tools) {

	var $ = Framework7.$;
	var loginuser={};
	
	var bindings=[{
		element: '.action1',
		event: 'click',
		handler: badw_dsm_tzsm
	},{
		element: '.action2',
		event: 'click',
		handler: badw_dsm_qx
    },{
    	element: '.first-item',
    	event: 'click',
    	handler: openFirstItem
	},{
		element: '.rydetail',
		event: 'click',
		handler: getRyPhoto	
	}];
	
	function badw_dsm_tzsm(){
		var id=$(this).attr("dataid");
		app.router.load("smsy/badw/badw",loginuser)
	}
	function badw_dsm_qx(){
		var dataid=$(this).attr("dataid");
		app.f7.confirm("确定要取消上门业务？","取消确认",function(){			
			app.f7.prompt("",'原因', function (value) {
				if (tools.isBlank(value)) {
					app.f7.alert("","请填写原因!");
					return;
				}
				$.ajax({
					url:'/smsy/change',
					type: "POST",
					data:{"id":dataid,"phase":6,"chyy":value},
					success:function(res){
						res=JSON.parse(res);
						if(res.data=="1"){
							init(loginuser);
						}				
					}
				});
		    });	
		},function(){
			
		});
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

    function openFirstItem(ele) {
    	console.info("openFirstItem")
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
    
	function init(query){
		loginuser=query;
		DsmView.render({
			model: query,
			bindings: bindings
		});
		
		Framework7.$(document).on('ajaxStart', function (e) {
			  var xhr = e.detail.xhr;
			  app.f7.showPreloader();;
		});
		Framework7.$(document).on('ajaxComplete', function (e) {
			  var xhr = e.detail.xhr;
			  app.f7.hidePreloader();
		});
		
	}
	
	return {
		init: init
	};
});
