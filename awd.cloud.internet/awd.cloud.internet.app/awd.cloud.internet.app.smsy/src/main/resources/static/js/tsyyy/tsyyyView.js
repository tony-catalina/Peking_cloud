define(['hbs!js/tsyyy/tsyyy'], function(viewTemplate) {
	var $ = Framework7.$;

	function render(params) {
		$('.page-content').html(viewTemplate(params));
		
		//日期选择框禁止选择小于当前日期的日期
		  //得到当前时间
		var date_now = new Date();
		//得到当前年份
		var year = date_now.getFullYear();
		//得到当前月份
		//注：
		//  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
		//  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
		var month = date_now.getMonth()+1 < 10 ? "0"+(date_now.getMonth()+1) : (date_now.getMonth()+1);
		//得到当前日子（多少号）
		var date = date_now.getDate() < 10 ? "0"+date_now.getDate() : date_now.getDate();
		//设置input标签的max属性
		$("#yysj").attr("min",year+"-"+month+"-"+date);
		
		bindEvents(params.bindings);
	}

	function bindEvents(bindings) {
		for (var i in bindings) {
			if(bindings[i].event!=undefined)
			$(bindings[i].element).on(bindings[i].event, bindings[i].handler);
		}
	}

	return {
		render: render
	}
});
