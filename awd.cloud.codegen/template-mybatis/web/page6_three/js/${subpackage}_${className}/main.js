<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${className}/main", [
            "../../plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
            "../../plugins/easyui/jquery-easyui-1.5.1/easyloader",
            "../../plugins/artTemplate/artTemplate",
            "../../common/tools",
            "../../common/componts",
            "../../common/kss",
            "../../pages/${subpackage}_${className}/form",
            "../../pages/${subpackage}_Jbxx/JbxxShowHide.html.js",
            "../../pages/${subpackage}_${className}/${className}ShowHide.html.js"
        ],
        function(require) {
            var comonts=require("../../common/componts");
            var ${classNameLower}Form=require("../../pages/${subpackage}_${className}/form");
            var tmp=require("../../pages/${subpackage}_Jbxx/JbxxShowHide.html");
            var tmp${className}=require("../../pages/${subpackage}_${className}/${className}ShowHide.html");
            var kss=require("../../common/kss");
            var ryjbxxId="jbxxId";
            var ${classNameLower}Id="${classNameLower}Id";
            var ${classNameLower}="${classNameLower}";
            var selectid;
            var id;
            var jbxx="jbxx";

            var tableInit=function(){
                kss.initJbxxTable(ryjbxxId,{
                    singleSelect:true,
                    url:'/${subpackage}_jbxx/jbxxList',
                    detailFormatter : function(index, rowData) {
                        return '<table  style="height:60px;width:100%"><tr><td>户籍地详细地址：'
                            + (rowData.hjszd == null ? ""
                                : rowData.hjszd)
                            + '</td></tr><tr><td>现住地详细地址:'
                            + (rowData.xzd == null ? "" : rowData.xzd)
                            + '</td></tr><tr><td>办案单位:'
                            + (rowData.badw == null ? "" : rowData.badw)
                            + '</td></tr><tr><td>简要案情:'
                            + (rowData.aq == null ? "" : rowData.aq)
                            + '</td></tr></table>'
                    },
                    onDblClickRow:function(rowIndex, rowData){
                        if (rowData.rybh != undefined) {
                            $("input[name='ryRadio']")[rowIndex].checked = true;
                            $("#"+ryjbxxId).datagrid("selectRow", rowIndex);
                            var jbxx=$("#"+ryjbxxId).datagrid('getSelected');
                            ${classNameLower}Form.show(jbxx,{},refreshTable);

                            $('input').next().find('input[type=text]').css('background-color','#EBEBE4');
                            var myDate = new Date();
                            var str=myDate.toLocaleTimeString();
                        }
                    },
                    onClickRow:function(rowIndex, rowData){
                        if(selectid!=rowData.id){
                            $("input[name='ryRadio']")[rowIndex].checked = true;
                            selectid=rowData.id;
                        }else{
                            $("#"+ryjbxxId).datagrid("unselectRow",rowIndex);
                            $("input[name='ryRadio']")[rowIndex].checked = false;
                            $('#'+ryjbxxId).datagrid('clearSelections');
                            selectid="";
                        }
                    },
                    onSelect:function(rowIndex, rowData){
                        var jbxx = $('#'+ryjbxxId).datagrid('getSelected');
                        var flag = $("input[name='ryRadio']")[rowIndex].checked;
                        if(jbxx && !flag){
                            var rybh = jbxx.rybh;
                            $('#'+${classNameLower}Id).datagrid('loadData', { total: 0, rows: [] });
                            if (rybh != undefined) {
                                var opts = $("#"+${classNameLower}Id).datagrid("options");
                                $("#"+${classNameLower}Id).datagrid("clearSelections");
                                $('#'+${classNameLower}Id).datagrid('load',{
                                    'rybh': rybh,
                                });
                            }
                        }else {
                            $('#'+${classNameLower}Id).datagrid('loadData', { total: 0, rows: [] });
                        }
                    },
                    rightmenu:function(){
                    }

                });
                comonts.initTable(${classNameLower}Id,{
                    title: '${table.tableAlias}',
                    url:"/${subpackage}_${classNameLower}/${classNameLower}List",
                    columns:[                    	
                        <#list table.columns as column>
                        {field: '${column.columnNameLower}',title: '${column.columnAlias}',formatter: function(value, rowData, rowIndex){return '<input type="radio" name="${column.columnNameLower}Radio" id="${column.columnNameLower}Radio"' + rowIndex + 'value="' + rowData.${column.columnNameLower} + '" />';}},
                    	</#list>
                        ],
                    detailFormatter:function(index,rowData){
                        return ;
                    },
                    onClickRow:function(rowIndex, rowData){
                        if(id!=rowData.id){
                            $("input[name='${classNameLower}Radio']")[rowIndex].checked = true;
                            $('#'+${classNameLower}Id).datagrid("selectRow", rowIndex);
                            id=rowData.id;
                        }else if(id==rowData.id){
                            $('#'+${classNameLower}Id).datagrid("unselectRow",rowIndex);
                            $("input[name='${classNameLower}Radio']")[rowIndex].checked = false;
                            id="";
                        }
                    },
                    //双击事件
                    onDblClickRow:function(rowIndex, rowData){
                        $("input[name='${classNameLower}Radio']")[rowIndex].checked = true;
                        $('#'+${classNameLower}Id).datagrid("selectRow", rowIndex);
                        id=rowData.id;
                        var jbxx = $('#jbxxId').datagrid('getSelected');
                        ${classNameLower}Form.show(jbxx,rowData,refreshTable);

                    },

                });
                $('#'+${classNameLower}Id).datagrid('resize',{
                    height:($(window).height() -320)
                });

            }
            var find${className}=function(){
                var val = $("#jqjsTree").tree('getSelected');
                var s_rybh = $("#s_rybh").textbox('getValue');
                var s_xm = $("#s_xm").textbox('getValue');
                //人员状态,单选框
                var opts = $('#'+ryjbxxId).datagrid("options");
                opts.url = "/${subpackage}_jbxx/jbxxList";
                $('#'+ryjbxxId).datagrid('clearSelections');
                $('#'+ryjbxxId).datagrid('load',{
                	'xm':s_xm,
                    'rybh':s_rybh,
                });
            }
            var initJsTree=function(){
                kss.initJsTree("jqjsTree","jbxxId",{});
            }
            var bindAction=function(){
                $("#${classNameLower}_search").on("click",function(){
                    find${className}();
                });
                $("#${classNameLower}_add").on("click",function(){
                    var ryjbxx=$("#"+ryjbxxId).datagrid('getSelected');
                    if(ryjbxx){
                        ${classNameLower}Form.show(ryjbxx,{},refreshTable);
                    }else{
                        $.messager.alert("提示","请选择要新增的记录!","info");
                    }
                });
                $("#${classNameLower}_update").on("click",function(){
                    var ryjbxx=$("#"+ryjbxxId).datagrid('getSelected');
                    var ${classNameLower}=$("#"+${classNameLower}Id).datagrid('getSelected');
                    if(ryjbxx&&bjjl){
                        ${classNameLower}Form.show(ryjbxx${classNameLower},refreshTable,{});
                        $('#rybhId').next().find('input[type=text]').css('background-color','#ebebe4');
                        $('#ryxmId').next().find('input[type=text]').css('background-color','#ebebe4');
                        $('#jshId').next().find('input[type=text]').css('background-color','#ebebe4');
                    }else{
                        $.messager.alert("提示","请选择要修改的记录!","info");
                    }
                });

                $(window).keydown(function(event) {
                    if (event.keyCode == 13) {
                        find${classNameLower}();
                    }
                });
                comonts.ShowHide(jbxx,tmp);
                comonts.ShowHide(${classNameLower},tmp${className});
                $("#${classNameLower}_print").on("click",function(){
                    var label="${subpackage}_${classNameLower}";
                    var name="${subpackage}_${classNameLower}";
                    var rybh=$("#"+ryjbxxId).datagrid('getSelected').rybh;
                    var url=comonts.getReportUrl(true,label,name,'rybh%3d'+rybh);
                    console.info(url);
                    addTab("${table.tableAlias}",url);
                });

            }
            var refreshTable=function(data){
                $('#'+${classNameLower}Id).datagrid('reload',{rybh:$('#'+ryjbxxId).datagrid('getSelected').rybh});
            }


            var initView=function(){
                tableInit();
                bindAction();
                initJsTree();

            }


            initView();
        });
