define(["app","js/interface/interfaceView","js/interface/dataView","js/tools"], function(app,View,dataView,tools) {

	var $ = Framework7.$;
	
	var bindings = [{
		element:'.back',
		event:'click',
		handler: back
	},{
		element:'.init',
		event:'click',
		handler: action
	},{
		element:'#action1',
		event:'click',
		handler: action1
	},{
		element:'#action2',
		event:'click',
		handler: action2
	},{
		element:'#action3',
		event:'click',
		handler: action3
	},{
		element:'#action4',
		event:'click',
		handler: action4
	},{
		element:'#action5',
		event:'click',
		handler: action5
	},{
		element:'#action6',
		event:'click',
		handler: action6
	},{
		element:'#action7',
		event:'click',
		handler: action7
	}];

	showDataView = function(url,param){
		$.ajax({
			url: url,
			type: "POST",
			data: param,
			success:function(data){
				data = JSON.parse(data);
				var isList ;
				if (data.total > 1) {
					isList = true;
				}else {
					isList = false;
				}
				dataView.render({
					isList : isList,
					model: data.data,
					bindings: bindings
				});
			}
		});
	}
	
	var initInterface = function(url,param){
		var type;
		if (param.type == 1) {
			type = "用户";
		}else if(param.type == 2){
			type = "授权";
		}
		$.ajax({
			url: url,
			type: "POST",
			data: param,
			success:function(data){
				data = JSON.parse(data);
				if (data.status == 200) {
					app.f7.alert("",type + "初始化成功!");
				}else {
					app.f7.alert("",type + "初始化失败!");
				}
			}
		});
	}
	
	
	function back(){
		app.f7.closeModal(".popup-interface");
	}
	
	function action(){
		var type = $(this).attr("value");
		console.info("type------"+type)
		var url = "/interface/init";
		initInterface(url,{"type":type});
	}
	
	function action1(){
		var url = "/interface/getRootOrg";
		showDataView(url,{});
	}
	
	function action2(){
		var rootCode = $("#rootCode").val();
		if (tools.isBlank(rootCode)) {
			app.f7.alert("","请填写rootCode!");
			return;
		}
		console.info("rootCode："+rootCode);
		var url = "/interface/getOrgsByParentCode";
		showDataView(url,{"rootCode":rootCode});
	}
	
	function action7(){
		var orgCode = $("#orgCode").val();
		if (tools.isBlank(orgCode)) {
			app.f7.alert("","请填写orgCode!");
			return;
		}
		console.info("orgCode："+orgCode);
		var url = "/interface/getUserByOrgCode";
		showDataView(url,{"orgCode":orgCode});
	}
	
	function action3(){
		var orgCode = $("#orgCode").val();
		if (tools.isBlank(orgCode)) {
			app.f7.alert("","请填写orgCode!");
			return;
		}
		console.info("orgCode："+orgCode);
		var url = "/interface/getOrgInfo";
		showDataView(url,{"orgCode":orgCode});
	}
	
	function action4(){
		var userCode = $("#userCode").val();
		if (tools.isBlank(userCode)) {
			app.f7.alert("","请填写userCode!");
			return;
		}
		console.info("userCode："+userCode);
		var url = "/interface/getUserInfo";
		showDataView(url,{"userCode":userCode});
	}
	
	function action5(){
		var url = "/interface/getResourceByAppCode";
		showDataView(url,{});
	}
	
	function action6(){
		var url = "/interface/getRoleByAppCode";
		showDataView(url,{});
	}
	
	function init(query){		
		View.render({
			model: query,
			bindings: bindings
		});
	}
	
	return {
		init: init
	};
});
