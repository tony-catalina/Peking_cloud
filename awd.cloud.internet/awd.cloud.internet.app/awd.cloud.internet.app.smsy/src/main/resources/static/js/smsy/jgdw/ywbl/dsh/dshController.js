define(["app","js/tools","js/smsy/jgdw/ywbl/dsh/dshView"], 
function(app,tools,DshView) {
	var loginuser={};
	var $ = Framework7.$; 
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '.dshry',
		event: 'click',
		handler: rysh
	},{
		element: '.swipeout .item-title',
		event: 'click',
		handler: swipeoutclick	
	},{
		element: '.action1',
		event: 'click',
		handler: shbtg
	/*},{
		element: '.action2',
		event: 'click',
		handler: shtg*/
	},{
		element: '#tgsh',
		event: 'click',
		handler: plShtg
	}];
	
	function init(query){
		loginuser=query;
		DshView.render({
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
	
	function rysh(){
		app.router.load("smsy/jgdw/ywbl/dsh/rysh/rysh",loginuser);
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
	
	function shtg(){
		var id=$(this).attr("dataid");	
		$.ajax({
			url:'/smsy/change',
			type: "POST",
			data:{"id":id,"phase":3},
			success:function(res){
				res=JSON.parse(res);
				if(res.data=="1"){
					//app.f7.alert("审核通过!");
					init(loginuser);
				}				
			}
		});
	}
	
	function plShtg(){
		var ids = [];
		$('input[name="my-checkbox"]').each(function() {
			var id = $(this).val();
	        if ($(this).prop('checked') ==true) {
	        	ids.push($(this).val())
	        }
		});
		
		if (ids.length == 0) {
			app.f7.alert("","请选中一条记录办理!");
			return;
		}
		$.ajax({
			url:'/smsy/change',
			type: "POST",
			data:{"id":ids,"phase":3},
			success:function(res){
				res=JSON.parse(res);
				if(res.data=="1"){
					//app.f7.alert("审核通过!");
					init(loginuser);
				}				
			}
		});
	}
	
	
	function shbtg(){
		var dataid=$(this).attr("dataid");		
		app.f7.confirm("<textarea id='f7ta' style='width:100%;resize:none;margin-top:5px;height:90px;font-size:16px;'></textarea>","原因",function(){
			if (tools.isBlank($("#f7ta").val())){
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
