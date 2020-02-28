<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.controller.kss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import awd.cloud.platform.utils.QueryParam;
import awd.cloud.platform.utils.TermType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import awd.framework.common.utils.OpenAPI;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.RequestWrapper;

@RestController
@RefreshScope
@RequestMapping("/v4/kss/${classNameLower}")
@Api(tags = "kss-${classNameLower}",description = "${table.tableAlias}") 
public class Kss_${className}Controller {
	
	@Autowired
    private KssService kssService;

	/**
	 * @api {get} /kss/${classNameLower} ${classNameLower}_query
	 * @apiVersion 0.4.0
	 * @apiName ${classNameLower}_query
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription ${table.tableAlias}分页查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * @apiParam {String} page 												页码
	 * @apiParam {String} param 											请求参数
	 *
	 <#list table.columns as column>
	 * @apiSuccess {String} ${column.columnNameLower}         				${column.columnAlias}
	 </#list>
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	@ApiOperation("分页查询")
	@GetMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	public ResponseMessage<PagerResult<Kss_${className}Model>> ${classNameLower}_query(HttpServletRequest request, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		QueryParam queryParam = new QueryParam();
        ResponseMessage<PagerResult<Kss_${className}Model>> result= kssService.${classNameLower}_query(queryParam);
        if(result.getStatus()==200) {
            result.setMessage("查询成功");
            if(result.getResult()==null) {
                result.setMessage("未查询数据");
            }
        }
        return result;
	}
	
	
	/**
	 * @api {post} /kss/${classNameLower} ${classNameLower}_save
	 * @apiVersion 0.4.0
	 * @apiName ${classNameLower}_save
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription ${table.tableAlias}自定义查询.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号	
	 * @apiParam {String} user  											登录名
	 * 
	 <#list table.columns as column>
	 * @apiParam {String} ${column.columnNameLower}         				${column.columnAlias}
	 </#list>
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					生成的主键信息
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse CreateError
	 */
	@ApiOperation("新增")
	@PostMapping
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ${classNameLower}_save( @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_${className}Model data) {
		return kssService.${classNameLower}_save(data);
	}
	
	

	/**
	 * @api {patch} /kss/${classNameLower} ${classNameLower}_updateByKey
	 * @apiVersion 0.4.0
	 * @apiName ${classNameLower}_updateByKey
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription ${table.tableAlias}数据更新.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *  
	 <#list table.columns as column>
	 * @apiParam {String} ${column.columnNameLower}         				${column.columnAlias}
	 </#list>
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					生成的主键信息
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse UpdateError
	 */
	@ApiOperation("根据id更新")
	@PutMapping(path = {"/{id:.+}"})
	@HystrixCommand
	@ApiResponses({@ApiResponse(code = 200, message = "查询成功"), @ApiResponse(code = 401, message = "未授权"),
			@ApiResponse(code = 403, message = "无权限"), @ApiResponse(code = 404, message = "数据不存在")})
	@OpenAPI
	public ResponseMessage<String> ${classNameLower}_updateByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user,@RequestBody Kss_${className}Model data) {
		return kssService.${classNameLower}_updateByKey(id, data);
	}	

	/**
	 * @api {get} /kss/${classNameLower}/{id} ${classNameLower}_getByKey
	 * @apiVersion 0.4.0
	 * @apiName ${classNameLower}_getByKey
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription ${table.tableAlias}根据key获取信息.
	 *
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 *
	 <#list table.columns as column>
	 * @apiSuccess {String} ${column.columnNameLower}         				${column.columnAlias}
	 </#list>
	 *
	 * @apiUse QueryError
	 */
	@OpenAPI
	public ResponseMessage<Kss_${className}Model> ${classNameLower}_getByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.${classNameLower}_getByKey(id);
	}
	
	
	/**
	 * @api {delete} /kss/${classNameLower}/{id} ${classNameLower}_deleteByKey
	 * @apiVersion 0.4.0
	 * @apiName ${classNameLower}_deleteByKey
	 * @apiGroup g_kss
	 * @apiPermission user
	 *
	 * @apiDescription ${table.tableAlias}数据删除.
	 *
	 * @apiParam {String} id 												数据编号
	 * @apiParam {String} appcode 											应用代码
	 * @apiParam {String} jsbh 												监所编号
	 * @apiParam {String} user 												登录名
	 * 
	 <#list table.columns as column>
	 * @apiParam {String} ${column.columnNameLower}         				${column.columnAlias}
	 </#list>
	 *
	 * @apiSuccess {String} message         				成功信息
	 * @apiSuccess {String} result         					删除记录数
	 * @apiSuccess {String} status         					请求状态
	 * @apiSuccess {String} timestamp         				时间戳 
	 *
	 * @apiUse UpdateError
	 */
	@OpenAPI
	public ResponseMessage<Integer> ${classNameLower}_deleteByKey(@PathVariable String id, @RequestParam(name = "appcode")String appcode, @RequestParam(name = "jsbh")String jsbh, @RequestParam(name = "user")String user) {
		return kssService.${classNameLower}_deleteByKey(id);
	}
}
