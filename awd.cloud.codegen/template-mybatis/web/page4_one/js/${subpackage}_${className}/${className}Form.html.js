<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
    define("pages/${subpackage}_${classNameLower}/${className}Form.html", [],
        '<div id="dlg${className}" class="easyui-dialog" title="{{title}}"\
                 style="width: 50%; height: 90%; padding: 10px 20px; padding-top: 10px"\
                     modal="true" closed="true" buttons="#dlg-buttons" data-options="maximized:true,maximizable:true,minimizable:true,collapsible:true">\
                     <div class="easyui-layout" style="height:100%;width:100%">\
                         <div data-options="region:\'center\'" style="">\
         <form id="fm${className}" method="post" novalidate>\
         <div class="easyui-layout"  style="width: 100%;height:128%"  >\
             <#list table.columns as column>
               <#if column.isDateTimeColumn>
                <div class="fitem" style="margin: 0 21% 10px 0"  align="right">\
                    <label>${column.columnAlias}:</label>\
                    <input id="${column.columnNameLower}fm" class="easyui-datetimebox" name="${column.columnNameLower}"  value="{{date.${column.columnNameLower}}}" style="width: 60%">\
                </div>\
                </#if>
                <div class="fitem" style=" margin-bottom: 10px">\
                    <label>${column.columnAlias}:</label>\
                    <input id="${column.columnNameLower}fm" class="easyui-textbox" value="{{data.${column.columnNameLower}}}"   name="${column.columnNameLower}"  style="width: 50%">\
                </div>\
            </#list>
        </div>\
        </form>\
            </div>\
         </div>\
        </div>\
    <div id="dlg-buttons">\
        <a href="#" class="easyui-linkbutton" iconCls="icon-ok" id="${classNameLower}_save">保存</a>\
        <a href="#" class="easyui-linkbutton" iconCls="icon-cancel" id="${classNameLower}_cancel">关闭</a>\
    </div>');