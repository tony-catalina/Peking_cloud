define(["app","js/smsy/badw/info/infoView","js/regist/xbselectView","js/regist/dwselectView","js/regist/addressView"],
function(app,  InfoView,XbselectView,DwselectView,AddressView) {
	var loginuser={};
	var $ = Framework7.$;

	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#infosave',
		event: 'click',
		handler: infosave
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
		element:'.useraddress',
		event:'click',
		handler: useraddress
	}];

	function init(query){
		loginuser=query;
		var disabled = "disabled='disabled'";
		if (query.userid == "445221199710115639") {
			disabled = "";
		}
		InfoView.render({
			model: query,
			bindings: bindings,
			disabled: disabled
		});

	}
	
	function back(){
		app.router.load("menu/menu",loginuser)
	}
	
	function infosave(){
		$.ajax({
			url:'/userupdate',
			type: "POST",
			data:app.f7.formToJSON("#fm"),
			success:function(res){
				res=JSON.parse(res);
				if(res.status==200){
					localStorage.removeItem("currentUser");//删除原有的cookie
					app.f7.alert("","保存成功!");
					app.router.load("login/login",{});
				}				
			}
		});
	}
	
	function selectXb(){
		XbselectView.render({
			model: loginuser,
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
	
	function useraddress(){
		AddressView.render({
			model: loginuser,
			bindings: bindings,
			address:$("input[name='address']").val(),
			callback:function(address){
				$("input[name='address']").val(address);
				$(".useraddresslabel").html(address);
			}
		});
		app.f7.popup(".popup-addressInput");
	}

	
	
	return {
		init: init
	};
});
