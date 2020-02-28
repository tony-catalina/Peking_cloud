define(["app","js/admin/adminView", "js/model/userModel","js/regist/dwselectView","js/tools"], function(app, AdminView, user,DwselectView,tools) {

	var $ = Framework7.$;
	
	var bindings = [{
		element: '.close-login-screen',
		event: 'click',
		handler: closeloginscreen
	},{
		element:'#choosemc',
		event:'click',
		handler: dwselect
	},{
		element:'.jsmc',
		event:'click',
		handler: dwselected
	},{
		element:'#adminlogin',
		event:'click',
		handler: adminlogin
	}];

	function init(query){		
		AdminView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function closeloginscreen(){
		//app.f7.closeModal(".login-screen");
		app.router.load("login/login", {});
	}
	
	function dwselect(){
		DwselectView.render({
			model: 1,
			bzw:1,
			bindings: bindings
		});
		app.f7.popup(".popup-selectBadw");
	}
	
	function dwselected(){
		$('#choosemc').val($(this).attr('jsmc'));
		$('#dwbhfm').val($(this).attr('value'))
		app.f7.closeModal(".popup-selectBadw")
	}
	
	function adminlogin(){
		var user=app.f7.formToJSON('#adminform');
		if (tools.isBlank(user.jsbh) || tools.isBlank(user.password)) {
			app.f7.alert("","请填写用户名或密码!");
			return;
		}
		
		tools.adminvalidator(app,user,function(data){
			loginuser=data;
			if(data){
				//app.f7.closeModal(".login-screen") ;
				app.router.load("conservatorTable/conservatorTable",loginuser)				
			}else{
				app.f7.alert("",'密码错误，请重新输入');
			}
		});
	}
	
	return {
		init: init
	};
});
