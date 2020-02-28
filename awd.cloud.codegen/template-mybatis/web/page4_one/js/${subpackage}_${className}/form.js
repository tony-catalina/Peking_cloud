<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${classNameLower}/form", [
            '../../pages/${subpackage}_${classNameLower}/${className}Form.html.js',
            "../../common/componts",
        ],
        function(require){
            var _show=function(_title,_data){
                var comonts=require("../../common/componts");
                var tmp= require("../../pages/${subpackage}_${classNameLower}/${className}Form.html");
                var htmlcompile = template.compile(tmp);
                //var _blr=loginuser.username;//获取当前办理人
                var html = htmlcompile(
                    {
                        title:_title,
                        data:_data
                    });
                $("#dlg${className}").dialog('destroy');
                $("#${classNameLower}Form").html(html);
                comonts.initSelect(undefined,undefined,undefined);
                $.parser.parse($("#${classNameLower}Form").parent());
                $('#dlg${className}').window('open');
                bindAction();
            }
            var save${className}=function () {
                var validate=$("#dlg${className}").form('validate');
                if (validate) {
                    var data =$("#fm${className}").serialize();
                    submitLoad();
                    $.ajax({
                        type : "POST",  //提交方式
                        url : "/${subpackage}_${classNameLower}/${classNameLower}Add",//路径
                        data : data,//数据，这里使用的是Json格式进行传输
                        success : function(result) {
                            if (result.success) {
                                $('#dlg${className}').dialog('close');
                                find${className}();
                                $.messager.alert('提示','保存成功!','info');
                            }else{
                                removeLoad();
                                $.messager.alert('提示',result.msg,'info');
                            }
                        },
                        complete:function(){
                            removeLoad();
                        },

                    });
                }else{
                    $.messager.alert("提示", '必填项不能为空！');
                }
            }
            var update${className}=function () {
                var validate=$("#dlg${className}").form('validate');
                if (validate) {
                    var data =$("#fm${className}").serialize();
                    submitLoad();
                    $.ajax({
                        type : "POST",  //提交方式
                        url : "/${subpackage}_${classNameLower}/${classNameLower}Update",//路径
                        data : data,//数据，这里使用的是Json格式进行传输
                        success : function(result) {
                            if (result.success) {
                                $('#dlg${className}').dialog('close');
                                find${className}();
                                $.messager.alert('提示','更新成功!','info');
                            }else{
                                removeLoad();
                                $.messager.alert('提示',result.msg,'info');
                            }
                        },
                        complete:function(){
                            removeLoad();
                        },

                    });
                }else{
                    $.messager.alert("提示", '必填项不能为空！');
                }
            }
            var bindAction=function(){
                var ${classNameLower}Id=$("#idfm").val();
                $("#${classNameLower}_save").on("click",function(){
                    if(${classNameLower}Id==""||${classNameLower}Id==null){
                        save${className}();
                    }else {
                        update${className}();
                    }
                });
                $("#${classNameLower}_cancel").on("click",function(){
                    $("#dlg${className}").dialog("close");
                });

            }
            function find${className}(){
                $('#${classNameLower}Id').datagrid('clearSelections');
                $('#${classNameLower}Id').datagrid('reload',{
                });
            }
            return{
                show:_show
            }
        });
