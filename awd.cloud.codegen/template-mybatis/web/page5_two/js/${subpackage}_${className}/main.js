<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_shff/main", [
            "../../plugins/easyui/jquery-easyui-1.5.1/jquery.easyui.min",
            "../../plugins/easyui/jquery-easyui-1.5.1/easyloader",
            "../../plugins/artTemplate/artTemplate",
            "../../common/tools",
            "../../common/componts",
            "../../common/kss",
            '../../pages/${subpackage}_${className}/form.js',
            "../../common/rfid",
            "../../pages/${subpackage}_jbxx/jbxxShowHide.html.js",
        ],
        function (require) {
            var comonts = require("../../common/componts");
            var kss=require("../../common/kss");
            var ${className}Form = require("../../pages/${subpackage}_${classNameLower}/form");
            var jbxxtmp =require("../../pages/${subpackage}_jbxx/jbxxShowHide.html");
            var ${classNameLower}Id = "${classNameLower}Id";
            var jqjsTree="jqjsTree";
            var selectid;
            var jbxxId = "jbxxId"
            var tableInit = function () {
                kss.initJbxxTable(jbxxId,{
                    title: '人员基本信息',
                    hight:280,
                    url:"/${subpackage}_jbxx/jbxxList",
                    toolbar:'#toolbar',
                    onLoadSuccess:function(data){
                        if(data.total>0){
                            $('#'+jbxxId).datagrid("selectRow", 0);
                            $("input[name='ryRadio']")[0].checked = true;
                            //鼠标悬浮显示人员状态
                            //kss.sbxt(jbxxId);
                        }else {
                        }
                    },
                    onDblClickRow:function(rowIndex, rowData){
                        if (rowData.rybh != undefined) {
                            $("input[name='ryRadio']")[rowIndex].checked = true;
                            $("#"+jbxxId).datagrid("selectRow", rowIndex);
                            var row = $('#'+jbxxId).datagrid('getSelected');
                            var jbxx={};
                            if(row) {
                                var data = {"rybh": row.rybh}
                                var u = "/${subpackage}_jbxx/jbxxList";
                                var url = sjurl(u);
                            }
                        }
                    },
                    onClickRow:function(rowIndex, rowData){
                        if(selectid!=rowData.id){
                            $("input[name='ryRadio']")[rowIndex].checked = true;
                            selectid=rowData.id;
                            var rybh = rowData.rybh;
                            //给url加上时间戳，使每次查询url都不同（解决IE中reload无用问题）
                            var u = $('#'+${classNameLower}Id).datagrid('options').url;
                            var url = sjurl(u);
                                $('#' + ${classNameLower}Id).datagrid('load', {
                                    "rybh":rybh,
                                    "state":'R2',
                                },url);
                        }else{
                            $("#"+jbxxId).datagrid("unselectRow",rowIndex);
                            $("input[name='ryRadio']")[rowIndex].checked = false;
                            $('#'+jbxxId).datagrid('clearSelections');
                            $('#'+${classNameLower}Id).datagrid('loadData', { total: 0, rows: [] });
                            selectid="";
                        }
                    },
                    onSelect:function(rowIndex, rowData){
                        var flag = $("input[name='ryRadio']")[rowIndex].checked;
                        if(!flag){
                            var rybh = rowData.rybh;
                            //给url加上时间戳，使每次查询url都不同（解决IE中reload无用问题）
                            var u = $('#'+${classNameLower}Id).datagrid('options').url;
                            var url = sjurl(u);
                                $('#' + ${classNameLower}Id).datagrid('load', {
                                    "rybh":rybh,
                                    "state":R2,
                                },url);
                        }else {
                            $('#'+${classNameLower}Id).datagrid('loadData', { total: 0, rows: [] });
                        }
                    },
                    rightmenu:function(){
                        kss.initJbxxRightMenu(jbxxrightmenuid);
                    }
                });
                comonts.initTable(${classNameLower}Id, {
                    title: '手环管理',
                    url: '/${subpackage}_${classNameLower}/${classNameLower}List',
                    firstLoad:false,

                    columns: [
                        {
                            field: 'oid', title: '', formatter: function (value, rowData, rowIndex) {
                                return '<input type="radio" name="${classNameLower}Radio" id="${classNameLower}Radio"' + rowIndex + 'value="' + rowData.oid + '" />';
                            }
                        },
                        {field : 'id', title:'id',hidden:true},
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
                    onLoadSuccess:function(data){
                        if(data.total>0){
                            $('#'+${classNameLower}lId).datagrid("selectRow", 0);
                            $("input[name='${classNameLower}Radio']")[0].checked = true;
                        }
                    },
                    onBeforeLoad:function(){
                        var jbxx = $('#'+jbxxId).datagrid('getSelected');
                        if (jbxx) {
                            if (jbxx.rybh != undefined) {
                                return true;
                            }else {
                                return false;
                            }
                        }else {
                            return false;
                        }
                    },

                });
                //高度自适应
                $('#' + ${classNameLower}Id).datagrid('resize', {
                    height: ($(window).height() -325)
                });
            }

            //查询事件
            var find${className} = function () {
                var s_snbh = $("#s_snbh").textbox('getValue');
                //给url加上时间戳，使每次查询url都不同（解决IE中reload无用问题）
                var u = $('#'+jbxxId).datagrid('options').url;
                var url = sjurl(u);
                    $('#' + jbxxId).datagrid('load', {
                        "rybh":s_snbh,
                    },url);
            }

            var find2 = function () {
                var row = $('#'+jbxxId).datagrid('getSelected');
                //给url加上时间戳，使每次查询url都不同（解决IE中reload无用问题）
                var u = $('#'+${classNameLower}Id).datagrid('options').url;
                var url = sjurl(u);
                $('#' + ${classNameLower}Id).datagrid('load', {
                    "rybh":row.rybh,
                },url);

            }
            //回车事件
            $(window).keydown(function (event) {
                if (event.keyCode == 13) {
                    find();
                }
            });

            var initJsTree = function () {
                kss.initJsTree(jqjsTree, ${classNameLower}lId, {});
            }
            //单击事件
            var bindAction = function () {
                comonts.ShowHide("jbxx",jbxxtmp);
                $('#find').on('click', function () {
                    find();
                });
                $('#zs_state').on('click',function () {
                    find();
                });
                $('#cs_state').on('click',function () {
                    find();
                });
                $('#all_state').on('click',function () {
                    find();
                });
                $('#${classNameLower}_delete').on('click', function () {
                    var jbxx=$("#"+jbxxId).datagrid('getSelected');
                    var ${classNameLower} = $("#"+${classNameLower}Id).datagrid('getRows');
                    if(jbxx){
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
                    }else{
                        $.messager.alert("提示","必须选中一人办理!","info");
                    }
                });
                $("#${classNameLower}_add").on("click",function(){
                    var jbxx=$("#"+jbxxId).datagrid('getSelected');
                    if(jbxx){
                            //新增
                            ${className}Form.show(find${className},{});
                    }else{
                        $.messager.alert("提示","必须选中一人办理!","info");
                    }
                });
                //修改
                $("#${classNameLower}_update").on("click",function(){
                    var jbxx=$("#"+jbxxId).datagrid('getSelected');
                    if(jbxx){
                        var ${className}=$("#"+${classNameLower}Id).datagrid('getSelected');
                        if(${className}){
                            ${className}Form.show(find${className},${className});
                        }else{
                            $.messager.alert("提示","请选择要修改的记录!","info");
                        }
                    }else{
                        $.messager.alert("提示","必须选中一人办理!","info");
                    }

                });

            }
            function dcExcel(){
                var row=$("#"+${classNameLower}Id).datagrid('getRows');
                if(row.length<=0){
                    $.messager.alert("提示","请选择需要导出的记录!","info");
                }else{
                    $.messager.confirm('提示', '是否下载?', function(r){
                    });
                }
            }
            //给url加上时间戳，使每次查询url都不同（解决IE中reload无用问题）
            var sjurl = function (url) {
                if (url.indexOf("_t=") > 0) {
                    url = url.replace(/_t=\d+/, "_t=" + new Date().getTime());
                } else {
                    url = url.indexOf("?") > 0
                        ? url + "&_t=" + new Date().getTime()
                        : url + "?_t=" + new Date().getTime();
                }
                return url;
            }

            $("#${classNameLower}_print").on("click",function(){
                dcExcel();
            });
            var initView = function () {
                tableInit();
                bindAction();
            }
            initView();
        });
