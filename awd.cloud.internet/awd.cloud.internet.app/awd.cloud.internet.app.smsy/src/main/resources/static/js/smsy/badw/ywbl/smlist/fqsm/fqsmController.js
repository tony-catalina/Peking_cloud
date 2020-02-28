define(["app","js/smsy/badw/ywbl/smlist/fqsm/fqsmView",
	"js/smsy/badw/ywbl/smlist/fqsm/dwselectView",
	"js/smsy/badw/ywbl/smlist/fqsm/showBadwTableView",
	"js/tools"],
function(app, FqsmView, DwselectView,ShowBadwTableView,tools) {

	var $ = Framework7.$;
	var loginuser={};

	var bindings = [{
		element: '.back',
		event: 'click',
		handler: back
	},{
		element: '#flwsimg-box',
		event: 'change',
		handler: photoupload
	},{
		element: '#gyjsmc',
		event: 'click',
		handler: gyjcselect
	},{
		element: '#addTzsm',
		event: 'click',
		handler: tzsmsave
	},{
		element:'.jsmc',
		event:'click',
		handler: dwselected
	}];

	var showTabBings=[{
    	element: '#tab1',
    	event: 'show',
    	handler: showTab1
    },{
    	element: '#tab2',
    	event: 'show',
    	handler: showTab2
	}];
	
	
    function showTab1() {
    	ShowBadwTableView.render({
            model: loginuser,
            bindings: bindings,
            state:"R2",
            tabClass:"tab1"
        });
    }
    
    function showTab2() {
    	ShowBadwTableView.render({
            model: loginuser,
            bindings: bindings,
            state:"R3",
            tabClass:"tab2"
        });
    }
	
	function init(query){
		loginuser=query;
		FqsmView.render({
			model: query,
			bindings: bindings			
		});
		Array.prototype.remove = function (dx) {
		    if (isNaN(dx) || dx > this.length) { return false; }
		    for (var i = 0, n = 0; i < this.length; i++) {
		        if (this[i] != this[dx]) {
		            this[n++] = this[i]
		        }
		    }
		    this.length -= 1
		}
		
		Framework7.$(document).on('ajaxStart', function (e) {
			  var xhr = e.detail.xhr;
			  app.f7.showPreloader();;
		});
		Framework7.$(document).on('ajaxComplete', function (e) {
			  var xhr = e.detail.xhr;
			  app.f7.hidePreloader();
		});
		photoList = [];
		fileList = [];
		$('#flwsImgDiv').html("");
		console.log("初始化")
	}


	var index = 0;
	var photoList = [];
	var fileList = [];
	function photoupload(){
		index = $('.fileData_img').length;
		if (fileList.length >= 4) {
			app.f7.alert("","最多选择4张图片！");
			return;
		}
		console.log(fileList.length);
		var timestamp = new Date().getTime();//用时间戳区分不同的img标签
		var img_id = "fileData_img_"+timestamp;
		$('#flwsImgDiv').append('<div class="swiper-slide"><img class="fileData_img" id="'+img_id+'" src="" style="width:125px;height:125px;padding: 5px 20px;"/></div>');
		photoList.push($("#flwsimg-box").val());//吧文件值放入数组
		var file = $("#flwsimg-box")[0].files[0];
		fileList.push(file);
		var url = null;
		try {
			if (window.createObjectURL != undefined) {
				url = window.createObjectURL(file);
			} else if (window.URL != undefined) {
				url = window.URL.createObjectURL(file);
			} else if (window.webkitURL != undefined) {
				url = window.webkitURL.createObjectURL(file);
			}
			if (url != null) {
				console.info("fileData_img_"+timestamp);
				document.getElementsByClassName('fileData_img')[index].src = url;
				$('.fileData_img').on('click',function(e){
					var id = e.target.id;
					var ele_index = getEleIndex(id);
					$('#'+id).parent().remove();
					fileList.remove(ele_index);
				});
			}
		} catch (e) {
			$('#'+img_id).remove();
		}
		var mySwiper3 = app.f7.swiper('.swiper-3', {
			  pagination:'.swiper-3 .swiper-pagination',
			  spaceBetween: 10,
			  slidesPerView: 3
		});
	}

	var getEleIndex = function(id) {
		var list = $('#flwsImgDiv')[0].children;
		for (var i = 0; i < list.length; i++) {
			if (id == list[i].children[0].id) {
				return i;
			}
		}
	}

	function tzsmsave(){
		var badw = $('#fm').find('input[name=badw]').val();
		var badwdz = $('#fm').find('input[name=badwdz]').val();
		var gyjs = $('#fm').find('input[name=gyjs]').val();
		var xyrxm = $('#fm').find('input[name=xyrxm]').val();
		if (tools.isBlank(badw) || tools.isBlank(badwdz) || 
				tools.isBlank(gyjs) || tools.isBlank(xyrxm)) {
			app.f7.alert("","请完善相关信息!");
			return;
		}

		var sendData=new FormData();
		sendData.append('badw',badw);
		sendData.append('badwdz',badwdz);
		sendData.append('gyjs',gyjs);
		sendData.append('xyrxm',xyrxm);

		for (var i = 0; i < fileList.length; i++) {
			sendData.append('file',fileList[i]);
		}
		//sendData.append('file',fileList);
		
		$.ajax({
			url:"smsy/addTzsm",
			type:"POST",
			cache: false,
			data:sendData,
			processData: false,
			contentType: false,
			success:function(data){
				data=JSON.parse(data)
				if (data.data=="1"){
					app.f7.alert("","通知成功",function(){
						if (loginuser.wsxx) {
							$.ajax({
								url:'/smsy/smsydelete',
								type: "POST",
								data:{"id":loginuser.wsxxid},
								success:function(res){
									res=JSON.parse(res);
									if(res.status==200){
										app.router.load("smsy/badw/ywbl/wtgry/wtgry",loginuser);
									}				
								}
							});
						}else {
							app.router.load("smsy/badw/ywbl/smlist/smlist",loginuser);
						}
					});
				} else {
					app.f7.alert("","通知失败！");
				}
			},
			error:function(){
				app.f7.alert("","通知失败！");
			}
		});
	}

	function gyjcselect(){
		app.f7.popup(".popup-selectBadw");
		DwselectView.render({
			model: loginuser,
			type:"2",
			bindings: showTabBings
		});
	}
	function dwselected(){
		$('#gyjsmc').val($(this).attr('jsmc'));
		$('form input[name="gyjs"]').val($(this).attr('value'))
		app.f7.closeModal(".popup-selectBadw")
	}
	function back(){
		app.router.load("smsy/badw/ywbl/smlist/smlist",loginuser);
	}
	return {
		init: init
	};
});
