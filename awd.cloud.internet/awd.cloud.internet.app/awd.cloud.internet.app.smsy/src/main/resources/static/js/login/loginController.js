define(["app","js/login/loginView", "js/model/userModel","js/regist/dwselectView","js/tools"], function(app, LoingView, user,DwselectView,tools) {

	var $ = Framework7.$;
	var loginuser={};
	var zwwxUser = {};
	var bindings = [{
		element: '#login',
		event: 'click',
		handler: login
	},{
		element: '#register',
		event: 'click',
		handler: openadminlogin
	},{
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
	},{
		element:'#deleteRemoteCache',
		event:'click',
		handler: deleteRemoteCache
	},{
		element:'#interface',
		event:'click',
		handler: interfaceFor4a
	},{
		element:'#test',
		event:'click',
		handler: getCode
	}];

	function init(query){
		loginuser=query;
		LoingView.render({
			model: query,
			bindings: bindings
		});
	}
	
	function login(){
		tools.uservalidator(app,tools.getZwwxUser(),function(data){
			loginuser=data;
			app.router.load("menu/menu",loginuser);
			tools.watermark({watermark_txt:loginuser.name+"("+loginuser.dwbhString+")"});
		});
	}
	
	function openadminlogin(){
		//app.f7.loginScreen();
		app.router.load("admin/admin",{});
	}

	function closeloginscreen(){
		app.f7.closeModal(".login-screen") ;
	}
	
	function dwselect(){
		DwselectView.render({
			model: 1,
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
		tools.adminvalidator(app,user,function(data){
			loginuser=data;
			if(data){
				app.f7.closeModal(".login-screen") ;
				app.router.load("conservatorTable/conservatorTable",loginuser)				
			}else{
				app.f7.alert("",'密码错误，请重新输入');
			}
		});
	}

	function deleteCache(app,zwwxUser){
		app.f7.confirm("是否删除 "+zwwxUser.name+" 的缓存数据",function(r){
       		if(r){
       			$.ajax({
       				url:'/deleteRemoteCache',
       				type: "POST",
       				data:{
       					"userid":zwwxUser.userid
       				},
       				success:function(res){
       					res=JSON.parse(res);
       					if(res.status==200){
       						localStorage.removeItem("currentUser");//删除原有的cookie
       						app.f7.alert("","清除缓存成功!");
       						//app.router.load("login/login",{});
       					}else {
       						app.f7.alert("","清除缓存失败!");
       					}				
       				}
       			});
       		}
       	});
	}
	
	function deleteRemoteCache(){
		zwwxUser=tools.getZwwxUser();
		deleteCache(app,zwwxUser);
	}
	
	
	function getCode(){
		var user = tools.getZwwxUser();
		console.info(user)
		app.router.load("menu/menu",user);
		tools.watermark({watermark_txt:user.username+"("+user.dwbhString+")"});
	}
	
	
	function interfaceFor4a(){
		app.router.load("interface/interface",{});
	}
	
	return {
		init: init
	};
});
