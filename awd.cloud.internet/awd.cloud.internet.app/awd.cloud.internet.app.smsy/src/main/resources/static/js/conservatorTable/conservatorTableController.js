define(["app", "js/conservatorTable/conservatorTableView","js/conservatorTable/showUserTableView", "js/tools"],
    function (app, UserView,ShowUserView,tools) {

        var $ = Framework7.$;
        var loginuser={};
        var bindings = [{
            element: '.back',
            event: 'click',
            handler: back
        },{
            element: '.action1',
            event: 'click',
            handler: ActionOne
        },{
            element: '.action2',
            event: 'click',
            handler: ActionTwo
        },{
        	element: '#tab1',
        	event: 'show',
        	handler: showTab1
        },{
        	element: '#tab2',
        	event: 'show',
        	handler: showTab2
        }];

        function init(query) {
        	loginuser=query;
        	UserView.render({
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

        function back() {
            app.router.load("admin/admin",{});
        }

        function showTab1() {
        	ShowUserView.render({
                model: loginuser,
                bindings: bindings,
                state:"R2",
                tabClass:"tab1"
            });
        }
        
        function showTab2() {
        	ShowUserView.render({
                model: loginuser,
                bindings: bindings,
                state:"R3",
                tabClass:"tab2"
            });
        }
        
        function  ActionOne(){
        	var dataid=$(this).attr("dataid");
        	$.ajax({
    			url:'/userupdate',
    			type: "POST",
    			data:{id:dataid,state:"R2"},
    			success:function(res){
    				res=JSON.parse(res);
    				if(res.status==200){
    					//app.router.load("conservatorTable/conservatorTable",loginuser)
    					ShowUserView.render({
    		                model: loginuser,
    		                bindings: bindings,
    		                state:"R3",
    		                tabClass:"tab2",
    		                search:"true"
    		            });
    				}				
    			}
    		});
        }
        
        
        function  ActionTwo(){
        	var dataid=$(this).attr("dataid");
        	$.ajax({
    			url:'/userupdate',
    			type: "POST",
    			data:{id:dataid,state:"R3"},
    			success:function(res){
    				res=JSON.parse(res);
    				if(res.status==200){
    					//app.router.load("conservatorTable/conservatorTable",loginuser)
    					ShowUserView.render({
    		                model: loginuser,
    		                bindings: bindings,
    		                state:"R2",
    		                tabClass:"tab1",
    		                search:"true"
    		            });
    				}				
    			}
    		});
        }
        
        return {
            init: init
        };
    });
