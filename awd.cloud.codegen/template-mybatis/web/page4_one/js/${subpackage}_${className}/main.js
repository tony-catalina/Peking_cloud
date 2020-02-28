<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${classNameLower}/main", [
            "../../plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
            "../../plugins/easyui/jquery-easyui-1.5.1/easyloader",
            "../../plugins/artTemplate/artTemplate",
            "../../common/tools",
            "../../common/componts",
            "../../pages/${subpackage}_${classNameLower}/form",
            "../../pages/${subpackage}_${classNameLower}/${className}ShowHide.html.js",
        ],
        function(require) {
            var comonts=require("../../common/componts");
            var ${className}Form=require("../../pages/${subpackage}_${classNameLower}/form");
            var tmp=require("../../pages/${subpackage}_${classNameLower}/${className}ShowHide.html");
            var ${classNameLower}Id="${classNameLower}Id";
            var selectid;
            var top="30px";
            var ${classNameLower}='${classNameLower}';
            var initTable=function(){
                comonts.initTable(${classNameLower}Id,{
                    title: '${table.tableAlias}',
                    url:"/${subpackage}_${classNameLower}/${classNameLower}List",
                    toolbar:'#tool',
                    firstLoad:true,
                    pageSize: 20,
                    columns:[
                        {field: 'oid',title: '',formatter: function(value, rowData, rowIndex){return '<input type="radio" name="${classNameLower}IdRadio" id="${classNameLower}IdRadio"' + rowIndex + 'value="' + rowData.oid + '" />';}},
                        <#list table.columns as column>
                        {
                            field : '${column.columnNameLower}',
                            width : 150,
                            title:'${column.columnAlias}',
                            sortable:'true',
                            formatter: function(value,row,index){
                                return '<span title='+value+'>'+(row.${column.columnNameLower} == null ? "" : row.${column.columnNameLower})+'</span>'
                            }},
                        </#list>
                        ],
                    detailFormatter:function(index,rowData){
                        return '<table style="height:40px;width:100%"><tr><td><strong></strong></td></tr></table>'
                    },
                    onClickRow:function(rowIndex, rowData){
                        if (selectid != rowData.id) {
                            $("input[name='${classNameLower}IdRadio']")[rowIndex].checked = true;
                            $('#' + ${classNameLower}Id).datagrid("selectRow", rowIndex);
                            selectid = rowData.id;
                        } else if (selectid == rowData.id) {
                            $('#' + ${classNameLower}Id).datagrid("unselectRow", rowIndex);
                            $("input[name='${classNameLower}IdRadio']")[rowIndex].checked = false;
                            selectid = "";
                        }
                    },
                });
                $('#${classNameLower}Id').datagrid('resize', {
                    height: ($(window).height()-35 )
                });
            }
            var find${className}=function(){
                $('#'+${classNameLower}Id).datagrid('clearSelections');
                $('#'+${classNameLower}Id).datagrid('load',{
                });
            }
            var bindAction=function(){
                //查询
                $("#${classNameLower}_search").on("click",function(){
                    find${className}();
                });
                //新增
                $("#${classNameLower}_add").on("click",function(){
                        ${className}Form.show(find${className},{});
                });
                //修改
                $("#${classNameLower}_update").on("click",function(){
                    var ${className}=$("#"+${classNameLower}Id).datagrid('getSelected');
                    if(${className}){
                        ${className}Form.show(find${className},${className});
                    }else{
                        $.messager.alert("提示","请选择要修改的记录!","info");
                    }
                });
                //删除
                $("#${classNameLower}_remove").on("click",function(){
                    //把你选中的 数据查询出来。
                    var selectRows = $('#${classNameLower}Id').datagrid("getSelections");
                    if(selectRows==null||selectRows==undefined){

                        return  $.messager.alert("提示消息", "请选中要删的数据!");
                    }else{
                        $.messager.confirm("操作提示", "您确定要删除该条记录吗？", function (data) {

                            if(data) {
                                submitLoad();
                                $.ajax({
                                    type : "POST",  //提交方式
                                    url : "/${subpackage}_${classNameLower}/${classNameLower}Delete",//路径
                                    data : {id:selectRows[0].id},//数据，这里使用的是Json格式进行传输
                                    success : function(result) {
                                        if (result.success) {
                                            $.messager.alert("确定","删除成功","info");
                                            find${className}();
                                        }else{
                                            removeLoad();
                                            $.messager.alert("确定","删除失败","info");
                                        }
                                    },
                                    complete:function(){
                                        removeLoad();
                                    },
                                });
                            }else {
                                return false;
                            }

                        });

                    }
                });
                comonts.ShowHide(${classNameLower},tmp);
            }
            var initView=function(){
                initTable();
                bindAction();
            }
            initView();

        });