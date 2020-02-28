<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package awd.bj.jls.webs.controller;

import awd.bj.jls.webs.api.JlsServersApi;
import awd.bj.jls.webs.api.model.User;
import awd.bj.jls.webs.api.model.${className}Model;
import awd.bj.jls.webs.utils.*;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.context.SecurityContextHolder;
import awd.framework.common.datasource.orm.core.param.QueryParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
@Controller
@RequestMapping("/jls_${classNameLower}")
@RefreshScope
public class ${className}Controller {

	/**
	 * 
	@GetMapping("/${classNameLower}")
    public ResponseMessage<PagerResult<${className}Model>> ${classNameLower}QueryForPage(@RequestBody QueryParam param);

    @PostMapping("/${classNameLower}")
    public ResponseMessage<String> ${classNameLower}Save(@RequestBody ${className}Model ${classNameLower}Entity);

    @PutMapping("/${classNameLower}/{id}")
    public String ${classNameLower}Update(@RequestParam(value="id") String id ,@RequestBody ${className}Model ${classNameLower}Entity);

	 */
    @Autowired
    private JlsServersApi jlsServersApi;

    /**
     * 初始化模板
     *
     * @param model
     * @return
     */
    @RequestMapping("/index.html")
    public String ${classNameLower}Index(Model model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userName", users.getUsername());
        return "${subpackage}_${classNameLower}/index";
    }
    @RequestMapping("/${classNameLower}.html")
    public String ${classNameLower}(Model model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userName", users.getUsername());
        return "${subpackage}_${classNameLower}/${classNameLower}";
    }
        @RequestMapping("/${classNameLower}List")
        @ResponseBody
        public Map<String, Object> ${classNameLower}List(HttpServletRequest request) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        QueryParam param = new QueryParam();
        String state = request.getParameter("state");
        String kssj=request.getParameter("kssj");
        String jssj=request.getParameter("jssj");
        DefaultQueryParam.addDefaultQueryParam(request, param, state);
        param.and("jsbh",TermType.eq,users.getJsbh());
        ResponseMessage<PagerResult<${className}Model>> list = jlsServersApi.${classNameLower}QueryForPage(param);
        Map<String, Object> outmap = Maps.newHashMap();
        outmap.put("total", list.getResult().getTotal());
        outmap.put("rows", list.getResult().getData());
        return outmap;
        }
        @RequestMapping("/${classNameLower}Add")
        @ResponseBody
        public Result<?> ${classNameLower}Add(@ModelAttribute ${className}Model ${classNameLower}Model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
        ${classNameLower}Model.setState("R2");
        ${classNameLower}Model.setJsbh(users.getJsbh());
        ${classNameLower}Model.setCreator(users.getUsername());
        ${classNameLower}Model.setCreatetime(new Date());
        return ResultUtils.success(jlsServersApi.${classNameLower}Save(${classNameLower}Model));
        } catch (Exception e) {
        e.printStackTrace();
        return ResultUtils.error(Result.ERR_SAVE, "保存失败");
        }
        }
        @RequestMapping("/${classNameLower}Delete")
        @ResponseBody
        public Result<?> ${classNameLower}Delete(@ModelAttribute ${className}Model ${classNameLower}Model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
        ${classNameLower}Model.setState("R3");
        ${classNameLower}Model.setJsbh(users.getJsbh());
        ${classNameLower}Model.setUpdator(users.getUsername());
        ${classNameLower}Model.setUpdatetime(new Date());
        return ResultUtils.success(jlsServersApi.${classNameLower}Update(${classNameLower}Model.getId(),${classNameLower}Model));
        } catch (Exception e) {
        e.printStackTrace();
        return ResultUtils.error(Result.ERR_SAVE, "保存失败");
        }
        }
        @RequestMapping("/${classNameLower}Update")
        @ResponseBody
        public Result<?> ${classNameLower}Update(@ModelAttribute ${className}Model ${classNameLower}Model) {
        User users = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        try {
        ${classNameLower}Model.setState("R2");
        ${classNameLower}Model.setJsbh(users.getJsbh());
        ${classNameLower}Model.setUpdator(users.getUsername());
        ${classNameLower}Model.setUpdatetime(new Date());
        return ResultUtils.success(jlsServersApi.${classNameLower}Update(${classNameLower}Model.getId(),${classNameLower}Model));
        } catch (Exception e) {
        e.printStackTrace();
        return ResultUtils.error(Result.ERR_SAVE, "保存失败");
        }
        }

        
       
}
