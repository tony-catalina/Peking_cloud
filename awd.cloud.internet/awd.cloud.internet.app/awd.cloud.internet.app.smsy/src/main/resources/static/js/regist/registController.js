define(["app","js/regist/registView", "js/regist/xbselectView","js/regist/dwselectView","js/model/userModel"],
	function(app, RegistView,XbselectView,DwselectView, user) {
	var $ = Framework7.$;
    var loginuser={};
    var wxh="";
    var userid="";
	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element:'#choosemc',
		event:'click',
		handler: dwselect
	},{
		element:'.jsmc',
		event:'click',
		handler: dwselected
	},{
		element:'#choosexb',
		event:'click',
		handler: selectXb
	},{
		element:'.xbtype',
		event:'click',
		handler: xbselected
	},{
		element:'#registePerson',
		event:'click',
		handler: registePerson
	}];

	function init(userDetail){
		console.info(userDetail)
		console.info(userDetail.userid)
		var disabled = "disabled='disabled'";
		if (userDetail.userid == "445221199710115639") {
			disabled = "";
		}
		//loginuser=query;
		RegistView.render({
			model: userDetail,
			bindings: bindings,
			disabled: disabled
		});
		app.mainView.showNavbar();
	}
	function back(){
		app.router.load("login/login",loginuser);
	}
	
	function selectXb(){
		XbselectView.render({
			model: user,
			bindings: bindings
		});
		app.f7.popup(".popup-Registerslb");
	}
	
	function xbselected(){
		$('#choosexb').val($(this).attr('xbS'));
		$('#xbfm').val($(this).attr('value'))
		app.f7.closeModal(".popup-Registerslb")
	}
	
	function dwselect(){
		DwselectView.render({
			//model: type,
			model: "",
			bindings: bindings
		});
		app.f7.popup(".popup-selectBadw");
	}
	
	function dwselected(){
		$('#choosemc').val($(this).attr('jsmc'));
		$('#typefm').val($(this).attr('type'));
		$('#dwbhfm').val($(this).attr('value'));
		app.f7.closeModal(".popup-selectBadw")
	}
	
	function registePerson(){
		var data =app.f7.formToJSON("#registfm");
		$.ajax({
			url:"/register",
			type:"POST",
			data: data,
			success:function(){
				app.f7.alert("",'注册成功',function(){
					setTimeout(function(){
						app.router.load("login/login",{});
					}, 500);
				});
			},
			error:function(){
				app.f7.alert('注册失败，请重新输入');
			}
		});
	}
	return {
		init: init
	};
});
