<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${className}/form", [
            '../../pages/${subpackage}_${className}/${className}Form.html.js',
        ],
        function(require){
            var _show=function(_ryjbxx,_${classNameLower},find${className}){
                var tmp= require("../../pages/${subpackage}_${className}/${className}Form.html");
                var htmlcompile = template.compile(tmp);
                var html = htmlcompile(
                    {
                        title:"${table.tableAlias}",
                        jbxx:_ryjbxx,
                        data:_${classNameLower},
                    });
                $("#gjcx").dialog('destroy')
                $("#dlg${className}").dialog('destroy')
                $("#${classNameLower}Form").html(html);
                $.parser.parse($("#${classNameLower}Form").parent());
                $('#dlg${className}').window('open');
                bindAction(find${className});
            }

            var submit${className}=function(find${className}){
                var row = $('#jbxxId').datagrid('getSelected');
                var validate=$("#dlg${className}").form('validate');
                if (validate) {
                    var data =$("#fm").serialize()+"&jsbh="+row.jsbh+"&rybh="+row.rybh;
                    submitLoad();
                    $.ajax({
                        type : "POST",  //提交方式
                        url : "/${subpackage}_${classNameLower}/${classNameLower}Add",//路径
                        data : data,//数据，这里使用的是Json格式进行传输
                        success : function(result) {
                            if (result.success) {
                                if($.isFunction(find${className})){
                                    find${className}();
                                }
                                $.messager.alert("确定","保存成功","info");
                                javascript:$('#dlg${className}').dialog('close');
                                removeLoad();
                            }else{
                                $.messager.alert("确定","保存失败","info");
                                removeLoad();
                            }
                        },
                        error:function(){
                            removeLoad();

                        }
                    });
                }else{
                    $.messager.alert("提示", '必填项不能为空！');
                }
            };
            var update${className}=function(find${className}){
                var row = $('#jbxxId').datagrid('getSelected');
                var validate=$("#dlg${className}").form('validate');
                if (validate) {
                    var data =$("#fm").serialize();
                    submitLoad();
                    $.ajax({
                        type : "POST",  //提交方式
                        url : "/${subpackage}_${classNameLower}/${classNameLower}Update",//路径
                        data : data,//数据，这里使用的是Json格式进行传输
                        success : function(result) {
                            if (result.success) {
                                $.messager.alert("确定","修改成功","info");
                                javascript:$('#dlg${className}').dialog('close');
                                removeLoad();
                                if($.isFunction(find${className})){
                                    find${className}();
                                }
                            }else{
                                $.messager.alert("确定",result.msg,"info");
                                removeLoad();

                            }
                        },
                        error:function(){
                            removeLoad();

                        }
                    });
                }else{
                    $.messager.alert("提示", '必填项不能为空！');
                }
            };

            var bindAction=function(find${className}){
                $("#${classNameLower}Save").on("click",function(){
                    var ${classNameLower}Id = $("#${classNameLower}Id").val();
                    if(${classNameLower}Id=="" || ${classNameLower}Id==null){
                        submit${className}(find${className});
                    }else{
                        update${className}(find${className});
                    }
                });
                $("#${classNameLower}Canel").on("click",function(){
                    $("#dlg${className}").dialog("close");
                });
            }
            return{
                show:_show
            }
        });
