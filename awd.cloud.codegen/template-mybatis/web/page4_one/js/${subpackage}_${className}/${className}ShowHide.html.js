<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
define("pages/${subpackage}_${classNameLower}/${className}ShowHide.html", [],
        '<div id="contextMenu_${classNameLower}" class="easyui-window" title="" \
                 data-options="modal:false,closed:true," \
                style="width: 460px; height:70px z-index: 9001; margin-left: auto;margin-right: auto;">\
                <table id="ycl_${classNameLower}">\
                 <#list table.columns as column>
                 <tr>\
                    <td style="width: 20px;">\
                    <input type="checkbox" name="${column.columnNameLower}" id="${column.columnNameLower}" checked="checked"/>\
                    </td>\
                    <td>\
                    <span>${column.columnAlias}</span>\
                    </td>\
                    </tr>\
                    </#list>
            </table>\
        </div>');