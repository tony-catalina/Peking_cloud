define(["app","js/smsy/badw/ywbl/cydwsz/cydwszView","js/smsy/badw/ywbl/cydwsz/showBadwTableView","js/tools"],
function(app, CydwszView,ShowBadwTableView,tools) {

	var $ = Framework7.$;
	var loginuser={};
	
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back		
	},{
    	element: '#tab1',
    	event: 'show',
    	handler: showTab1
    },{
    	element: '#tab2',
    	event: 'show',
    	handler: showTab2
    },{
    	element: '.setCommonlyUnit',
    	event: 'click',
    	handler: setCommonlyUnit
	}];
	
	function init(query){
		loginuser=query;
		CydwszView.render({
			model: query,
			bindings: bindings
		});
		Handlebars.registerHelper("equal",function(v1,v2,options){
			   if(v1 == v2){
			     //满足添加继续执行
			     return options.fn(this);
			   }else{
			     //不满足条件执行{{else}}部分
			     return options.inverse(this);
			  }
		});
	}
	
	function back(){
		loginuser["isLoad"] = false;
		app.router.load("smsy/badw/badw",loginuser);
		setTimeout(function () {
			app.router.load("smsy/badw/ywbl/ywbl",loginuser);
	    }, 10);
	}
	
    function showTab1() {
    	ShowBadwTableView.render({
            model: loginuser,
            bindings: bindings,
            tabClass:"tab1"
        });
    }
    
    function showTab2() {
    	ShowBadwTableView.render({
            model: loginuser,
            bindings: bindings,
            tabClass:"tab2"
        });
    }
	
    function setCommonlyUnit(){
    	var dwbh = $(this).attr("dwbh");		
    	var option = $(this).attr("option");
    	$.ajax({
			url:"/unit/setCommonlyUnit",
			type: "POST",
			data:{
				"dwbh":dwbh,
				"option":option,
				"userid":loginuser.userid
			},
			success:function(res){
				res=JSON.parse(res)
				if (res.status == 200) {
					app.f7.alert("","设置成功!");
					if (option == "2") {
						app.f7.swipeoutDelete($("#isCommonly_"+dwbh))
					}else if (option == "1") {
						app.f7.swipeoutClose($("#notCommonly_"+dwbh))
					}
				}
			}
		});
    }
    
	return {
		init: init,
	};
});
