<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${className}/${className}Form.html", [],
        '<div id="dlg${className}" class="easyui-dialog" title="{{title}}"\
                style="width: 69%; height:90%; padding: 10px 20px; padding-top: 10px"\
                    closed="true" buttons="#dlg-buttons" data-options="maximizable:true,minimizable:true,collapsible:true">\
                    <div class="easyui-layout" data-options="fit:true">\
                        <form id="fm" method="post" novalidate>\
                            <input type="hidden" id="id" name="id" value="{{data.id}}" style="width: 265px">\
                            <div data-options="region:\'center\'" style="position: relative;">\
                                <div align="center" style="margin-right: 90px;">\
                                    <br>\
                                    <div class="fitem" style="margin-left: 60px; margin-bottom: 10px">\
                                        <label style="margin-left: 50px">人员编号:</label> <input id="rybhId"\
                                            name="snbh" value="{{jbxx.snbh}}" class="easyui-textbox" readOnly style="width: 265px">\
                                    </div>\
                                    <div class="fitem" style="margin-left: 60px; margin-bottom: 10px">\
                                        <label style="margin-left: 26px">在押人员姓名:</label> <input\
                                            id="ryxmId" name="xm" value="{{jbxx.xm}}" class="easyui-textbox" readOnly style="width: 265px">\
                                    </div>\
                                    <div class="fitem" style="margin-left: 60px; margin-bottom: 10px">\
                                        <label style="margin-left: 62px">监室号:</label> <input id="jshId"\
                                            class="easyui-textbox" name="jsh" value="{{jbxx.jsh}}" readOnly style="width: 265px">\
                                    </div>\
                                </div>\
                                <#list table.columns as column>
                                <div class="fitem" style=" margin-bottom: 10px">\
                                    <label>${column.columnAlias}:</label>\
                                    <input id="${column.columnNameLower}fm" class="easyui-textbox" value="{{data.${column.columnNameLower}}}"   name="${column.columnNameLower}"  style="width: 50%">\
                                </div>\
                                </#list>
                            </div>\
                        </form>\
                    </div>\
                <div id="dlg-buttons">\
                <a href="#" class="easyui-linkbutton" iconCls="icon-ok"\
                    id="${classNameLower}Save">保存</a> <a href="#" class="easyui-linkbutton"\
                    iconCls="icon-cancel" id="${classNameLower}Canel" >关闭</a>\
            </div>\
                </div>');
