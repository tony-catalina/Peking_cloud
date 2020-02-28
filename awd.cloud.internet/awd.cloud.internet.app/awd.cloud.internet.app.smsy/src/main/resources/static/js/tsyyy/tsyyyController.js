define(["app","js/tsyyy/tsyyyView","js/tsyyy/jsselectView", "js/model/tsyyyModel"], function(app, TsyyyView,JsselectView, user) {

	var $ = Framework7.$;
	var loginuser={};
	var bindings = [{
		element: '.yydjback',
		event: 'click',
		handler: back
	},{
		element: '#choosejs',
		event: 'click',
		handler: choosejs
	},{
		element: '.jsmc',
		event: 'click',
		handler: clickJsmc
	},{
		element: '#sqyyId',
		event: 'click',
		handler: saveTsyy
	}];

	function init(query){
		loginuser=query;
		TsyyyView.render({
			model: query,
			bindings: bindings
		});
	}
	function back(){
		app.router.load("tsyy/tsyy",loginuser);
	}
	
	function choosejs(){
		JsselectView.render({
			model: user,
			bindings: bindings
		});
		app.f7.popup(".popup-Registerslb");
	}
	
	function clickJsmc(){
		$('#choosejs').val($(this).attr("jsmc"));
		var jsbh = $(this).attr("value");
		$('#jsbhfm').val(jsbh);
		
		$.ajax({
			url:'/tsyy/getSyfj',
			type:'get',
			data:{"jsbh":jsbh},
			success:function(res){
				res=JSON.parse(res)
				if(res.status == 200){
					$("#syfj").val(res.result.sysl);
				}
			}
		})
		
		app.f7.closeModal(".popup-Registerslb")
	}
	
	function get_uuid() {
		return 'xxxxxxxx'.replace(/[xy]/g, function(c) {
			var r = Math.random() * 16 | 0, v = c == 'x' ? r
					: (r & 0x3 | 0x8);
			return v.toString(16);
		});
	}
	
	function saveTsyy(){
		//获取监所value
		var jsbh = $("#jsbhfm").val();
		//获取预约时间
		var time = $("#yysj").val();
		//获取剩余会见室
		var dwphone = $("#syfj").val();

		var tsid = get_uuid();
		$.ajax({
			url : 'tsyy/getYyslByjs',
			type : 'get',
			data : {"jsbh" : jsbh},
			success : function(res) {
				res = JSON.parse(res)
				if (res) {
					if (time == '' || time == undefined) {
						app.f7.alert("",'请填写预约时间');
					} else {
						if (dwphone != 0) {
							$.ajax({
								url : "tsyy/saveTsyy",
								type : "POST",
								data : {
									yyid : tsid,
									jsbh : jsbh,
									yyrq : time,
									fjsl : dwphone,
								},
								success : function(res) {
									res = JSON.parse(res)
									if (res.status == 200) {
										app.f7.alert("",'预约成功');
										app.router.load("tsyy/tsyy",{})
									}
								},
								error : function() {
									app.f7.alert("",'预约失败，请联系管理员');
								}
							})
						} else {
							app.f7.alert("",'剩余房间数不足');
						}
					}
				} else {
					app.f7.alert("",'该监所已办理过预约,请办理完成后再次预约');
				}
			}
		})
	}
	
	return {
		init: init
	};
});
