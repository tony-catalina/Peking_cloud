define(['app',"js/tools"], function(app,tools) {
	var user={};
	var zwwxUser = {};
	var access_token;
	var $ = Framework7.$;
	var corpid="wlf6450fdb4c";
	var corpsecret="YVIjv_oa8KVPwH0he-D-YyPAvULnrrKDqMO4DvxFUho";
	var host="http://jgtsyy.gaj.sh.gov.cn";
	var authorhost="https://jwwx.gaj.sh.gov.cn";
	var	AgentId="1000171";
	var	watermark=function (settings) {
		    $(".mask_div").remove();
  		    //默认设置
  		    var defaultSettings={
  		        watermark_txt:"text",
  		        watermark_x:20,//水印起始位置x轴坐标
  		        watermark_y:20,//水印起始位置Y轴坐标
  		        watermark_rows:20,//水印行数
  		        watermark_cols:20,//水印列数
  		        watermark_x_space:100,//水印x轴间隔
  		        watermark_y_space:50,//水印y轴间隔
  		        watermark_color:'#aaa',//水印字体颜色
  		        watermark_alpha:0.4,//水印透明度
  		        watermark_fontsize:'15px',//水印字体大小
  		        watermark_font:'微软雅黑',//水印字体
  		        watermark_width:210,//水印宽度
  		        watermark_height:80,//水印长度
  		        watermark_angle:15//水印倾斜度数
  		    };
  		    //采用配置项替换默认值，作用类似jquery.extend
  		    if(arguments.length===1&&typeof arguments[0] ==="object" )
  		    {
  		        var src=arguments[0]||{};
  		        for(key in src)
  		        {
  		            if(src[key]&&defaultSettings[key]&&src[key]===defaultSettings[key])
  		                continue;
  		            else if(src[key])
  		                defaultSettings[key]=src[key];
  		        }
  		    }

  		    var oTemp = document.createDocumentFragment();

  		    //获取页面最大宽度
  		    var page_width = Math.max(document.body.scrollWidth,document.body.clientWidth);
  		    var cutWidth = page_width*0.0150;
  		    var page_width=page_width-cutWidth;
  		    //获取页面最大高度
  		    var page_height = Math.max(document.body.scrollHeight,document.body.clientHeight)+450;
  		    // var page_height = document.body.scrollHeight+document.body.scrollTop;
  		    //如果将水印列数设置为0，或水印列数设置过大，超过页面最大宽度，则重新计算水印列数和水印x轴间隔
  		    if (defaultSettings.watermark_cols == 0 || (parseInt(defaultSettings.watermark_x + defaultSettings.watermark_width *defaultSettings.watermark_cols + defaultSettings.watermark_x_space * (defaultSettings.watermark_cols - 1)) > page_width)) {
  		        defaultSettings.watermark_cols = parseInt((page_width-defaultSettings.watermark_x+defaultSettings.watermark_x_space) / (defaultSettings.watermark_width + defaultSettings.watermark_x_space));
  		        defaultSettings.watermark_x_space = parseInt((page_width - defaultSettings.watermark_x - defaultSettings.watermark_width * defaultSettings.watermark_cols) / (defaultSettings.watermark_cols - 1));
  		    }
  		    //如果将水印行数设置为0，或水印行数设置过大，超过页面最大长度，则重新计算水印行数和水印y轴间隔
  		    if (defaultSettings.watermark_rows == 0 || (parseInt(defaultSettings.watermark_y + defaultSettings.watermark_height * defaultSettings.watermark_rows + defaultSettings.watermark_y_space * (defaultSettings.watermark_rows - 1)) > page_height)) {
  		        defaultSettings.watermark_rows = parseInt((defaultSettings.watermark_y_space + page_height - defaultSettings.watermark_y) / (defaultSettings.watermark_height + defaultSettings.watermark_y_space));
  		        defaultSettings.watermark_y_space = parseInt(((page_height - defaultSettings.watermark_y) - defaultSettings.watermark_height * defaultSettings.watermark_rows) / (defaultSettings.watermark_rows - 1));
  		    }
  		    var x;
  		    var y;
  		    for (var i = 0; i < defaultSettings.watermark_rows; i++) {
  		        y = defaultSettings.watermark_y + (defaultSettings.watermark_y_space + defaultSettings.watermark_height) * i;
  		        for (var j = 0; j < defaultSettings.watermark_cols; j++) {
  		            x = defaultSettings.watermark_x + (defaultSettings.watermark_width + defaultSettings.watermark_x_space) * j;

  		            var mask_div = document.createElement('div');
  		            mask_div.id = 'mask_div' + i + j;
  		            mask_div.className = 'mask_div';
  		            mask_div.appendChild(document.createTextNode(defaultSettings.watermark_txt));
  		            //设置水印div倾斜显示
  		            mask_div.style.webkitTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
  		            mask_div.style.MozTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
  		            mask_div.style.msTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
  		            mask_div.style.OTransform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
  		            mask_div.style.transform = "rotate(-" + defaultSettings.watermark_angle + "deg)";
  		            mask_div.style.visibility = "";
  		            mask_div.style.position = "absolute";
  		            mask_div.style.left = x + 'px';
  		            mask_div.style.top = y + 'px';
  		            mask_div.style.overflow = "hidden";
  		            mask_div.style.zIndex = "9999";
  		            mask_div.style.pointerEvents='none';//pointer-events:none  让水印不遮挡页面的点击事件
  		            //mask_div.style.border="solid #eee 1px";
  		            mask_div.style.opacity = defaultSettings.watermark_alpha;
  		            mask_div.style.fontSize = defaultSettings.watermark_fontsize;
  		            mask_div.style.fontFamily = defaultSettings.watermark_font;
  		            mask_div.style.color = defaultSettings.watermark_color;
  		            mask_div.style.textAlign = "center";
  		            mask_div.style.width = defaultSettings.watermark_width + 'px';
  		            mask_div.style.height = defaultSettings.watermark_height + 'px';
  		            mask_div.style.display = "block";
  		            oTemp.appendChild(mask_div);
  		        };
  		    };
  		    document.body.appendChild(oTemp);
  		}
	var	setCookie=function(cname,cvalue,exdays){
  			  var d = new Date();
  			  d.setTime(d.getTime()+(exdays*24*60*60*1000));
  			  var expires = "expires="+d.toGMTString();
  			  document.cookie = cname + "=" + cvalue + "; " + expires;
  		}
	var	getCookie=function(cname){
  		  var name = cname + "=";
  		  var ca = document.cookie.split(';');
  		  for(var i=0; i<ca.length; i++)
  		  {
  		    var c = ca[i].trim();
  		    if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  		  }
  		  return "";
  		}
	var setLocal=function(key, value, days){
		// 设置过期原则
        if (!value) {
          localStorage.removeItem(key)
        } else {
          var Days = days || 1; // 默认保留1天
          var exp = new Date();
          localStorage[key] = JSON.stringify({
            value,
            expires: exp.getTime() + Days * 24 * 60 * 60 * 1000
          })
        }
	}
	var getLocal=function(name){
		try {
	          let o = JSON.parse(localStorage[name])
	          if (!o || o.expires < Date.now()) {
	            return null
	          } else {
	            return o.value
	          }
	        } catch (e) {
	            // 兼容其他localstorage 
	          return localStorage[name]
	        } finally {
	        }
	}
	var	getToken=function(){
    	if(getCookie("access_token")==""||getCookie("access_token")==null){
        	var authorurl=authorhost+"/cgi-bin/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
        	$.get("/http-get", {url:authorurl}, function (data) {
        		data=JSON.parse(data);
        		if(data.errcode==0){
        			setLocal("access_token",data.access_token)
        		}
        	});
    	}
    }
	
	var	getAccessToken=function(){
		var access_token;
		var authorurl = authorhost+"/cgi-bin/gettoken?corpid="+corpid+"&corpsecret="+corpsecret;
		$.get("/http-get", {url:authorurl}, function (data) {
			data=JSON.parse(data);
			if(data.errcode==0){
				access_token = data.access_token;
				setLocal("access_token",access_token);
			}
		});
		return access_token;
	}
	
	var getCode=function(){
    	 var appid=corpid;
    	 var state=browser();
    	 var redirect_uri=host;
    	 var scope = "snsapi_privateinfo";
    	 //var scope = "snsapi_base";
    	 //var scope = "snsapi_userinfo";
    	 var agentid=AgentId;
    	 var url="https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appid+"&redirect_uri="+redirect_uri+"&response_type=code&scope="+scope+"&agentid="+agentid+"&state="+state+"#wechat_redirect";
    	 //url="http://192.168.4.110:8086?code=CODE&state=android"; //本地测试使用
    	 window.location.href=url;
    }
	
	function getAuthor(){	//获取授权
		var code = getQueryVariable("code");
		if(code==false){
			getCode();	
			return false;
		}else {
			var	access_token = getAccessToken();
			getuser(app,code,function(curruser){
				getuserdetail(app,curruser.user_ticket,function(userdetail){
					zwwxUser=userdetail;
					setLocal("currentUser",JSON.stringify(zwwxUser));
					return zwwxUser;
				});
			});	
		}
	}
	
	function getZwwxUser() {
		console.info(zwwxUser)
		return zwwxUser;
	}
	
	function pushHistory(url) {  
		window.history.pushState(null, null,url);
	}
	
	var getuser=function(app,code,validateuser){
    	var access_token=getLocal("access_token");
    	var code=code;
    	if(access_token==undefined){
    		return 0;
    	}
    	if(code==undefined){
    		return 1;
    	}
    	var url=authorhost+"/cgi-bin/user/getuserinfo?code="+code+"&access_token="+access_token;
    	$.get("/http-get", {url:url}, function (data) {
     		data=JSON.parse(data);
     		//validateuser(data);	//本地测试
     		if(data.errcode==0){
     			var curruser={"userid":data.UserId,"deviceid":data.DeviceId,"user_ticket": data.user_ticket, "expires_in":data.expires_in,"usertype":data.usertype};
     			validateuser(curruser);
     		}else{
     			getCode();
     		}
     	});
    }
	var getlogin=function(){
		return getLocal("currentUser");
	}
	
	var getuserdetail=function(app,user_ticket,updateuser){
		var access_token=getLocal("access_token");
    	var url=authorhost+"/cgi-bin/user/getuserdetail?access_token="+access_token;
    	var jsonData = {"user_ticket":user_ticket};
    	$.post("http-post", {url:url,json:JSON.stringify(jsonData)}, function (data) {
    		/*data = {
    				   "errcode": 0,
    				   "errmsg": "ok",
    				   //"userid":"445221199710115639",
    				   "userid":"320322195601048678",	//测试监管单位的用户
    				   //"userid":"320322196604056547",	//测试办案单位的用户
    				   //"userid":"320322198404035345",	//带头像的用户
    				   "name":"吴晓文（安威德）",
    				   "department":[5625],
    				   "position": "后台工程师",
    				   "mobile":"15050495892",
    				   "gender":1,
    				   "email":"",
    				   "avatar":"https://wwlocal.qq.com/cgi-bin/downloadfile?type=download&fileid=WI5929-3_l~zz22t9nnWFkEk0gy097&uin=24048166&filename=icon.png/0",
    				   "order":[],
    				   "qr_code": "https://wwlocal.qq.com/wework_admin/userQRCode?vcode=vc2140a8b3c6207c74&lvc=vcf6f1acfdc4b45088"
    				}
    		data = JSON.stringify(data);*/
    		
    		data=JSON.parse(data);
    		data.department = "";
    		updateuser(data);
    	});
    }
	
	var uservalidator=function(app,user,resender){  //点击登录方法  
		console.info(user)
		$.ajax({
        	type:"POST",
            url:"/interface/getUserModelFrom4A",
			data: user,
            success:function(data){
            	data=JSON.parse(data);
            	if (data.total == 0) {
					app.f7.alert("","未获取到您的身份信息！","");
				}
            	var userDetail = data.data;
            	//该用户第一次登陆，跳转到注册页面
            	if(isBlank(userDetail.state) || isBlank(userDetail.shbz) ){
            		$.ajax({
            			url:"/register",
            			type:"POST",
            			data: userDetail,
            			success:function(result){
            				result = JSON.parse(result)
            				if (result.status == 200) {
								var userinfo = result.result;
								if (userinfo.state=="R2") {
									resender(userinfo);
								}else {
									app.f7.alert("","对不起，请等待管理员审核您的账户！","");
								}
							}
            			},
            		});
	               	//该用户已存在后的操作	  
            	}else{
                	//用户账户被禁用
                	if(userDetail.state=="R3" ){
                		app.f7.alert("","对不起，请等待管理员审核您的账户！","");
                	}                      	
                	//正常状态
                	if(userDetail.state=="R2" ){ 
						if(checkCookie()){
							setLocal("currentUser",JSON.stringify(userDetail));
						}else{
							user=userDetail;
						}  
						resender(userDetail);
                	}
            	}
            	//app.f7.alert("",'账号异常，请重新登陆！',"");
            },
            error:function(){
          	  app.f7.alert("",'账号异常，请重新登陆！',"");
            }
        });
		
	}
	var versions=function () {
		var u = navigator.userAgent, app = navigator.appVersion;
		return { //移动终端浏览器版本信息 
		ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端 
		android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1, //android终端或uc浏览器 
		iPhone: u.indexOf('iPhone') > -1, //是否为iPhone或者QQHD浏览器 
		iPad: u.indexOf('iPad') > -1, //是否iPad 
		};
	}
	var browser = function(){	
		console.log(versions());
		if (versions().iPhone || versions().iPad || versions().ios) {
			return "ios";
		}
		if (versions().android) {
			return "android";
		}	
	}
	var  getQueryVariable = function(variable){
		var query = window.location.search.substring(1);
		var vars = query.split("&");
		for (var i=0;i<vars.length;i++) {
			var pair = vars[i].split("=");
			if(pair[0] == variable){return pair[1];}
		}
		return(false);
	}
	
	var checkCookie=function (){  
		if(window.navigator.cookieEnabled)  
			   return true;  
			else{  
			   alert("浏览器配置错误，Cookie不可用！");  
			   return false;}  
	} 
	var adminvalidator=function(app,data,resender){
		$.ajax({
            url:'/admin',
            method:'post',
            data:data,
            success:function(data){
            	data=JSON.parse(data);
            	resender(data.result);                
            },
            error:function(){
          	  app.f7.alert("",'账号异常，请重新登陆！',"");
            }
        });
	}
	
	var isBlank = function(obj){
		if(typeof obj == "undefined" || obj == null || obj == "" || obj.replace(/ /g,"") == ""){
	        return true;
	    }else{
	        return false;
	    }
	}
	
	return {
		watermark:watermark,
		setCookie:setCookie,
		getCookie:getCookie,
		getToken:getToken,
		getCode:getCode,
		getAuthor:getAuthor,
		getuser:getuser,
		getuserdetail:getuserdetail,
		uservalidator:uservalidator,
		browser:browser,
		getlogin:getlogin,
		getQueryVariable:getQueryVariable,
		checkCookie:checkCookie,
		user:user,
		zwwxUser:zwwxUser,
		getZwwxUser:getZwwxUser,
		adminvalidator:adminvalidator,
		isBlank: isBlank
	}
});
