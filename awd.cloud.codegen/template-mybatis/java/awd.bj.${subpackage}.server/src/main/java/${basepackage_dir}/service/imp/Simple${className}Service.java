<#assign className=table.className>
<#assign classNameLower=className?uncap_first>
package ${basepackage}.service.imp;
import awd.framework.common.entity.PagerResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import awd.framework.common.entity.Entity;
import awd.bj.jls.servers.utils.CacheUtils;
import awd.framework.common.controller.message.ResponseMessage;
import awd.framework.common.core.IDGenerator;
import awd.framework.common.datasource.api.annotation.UseDataSource;
import awd.framework.common.service.simple.GenericEntityService;
import awd.framework.common.utils.StringUtils;
import ${basepackage}.entity.${className}Entity;
import ${basepackage}.dao.${className}Dao;
import ${basepackage}.service.${className}Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Service("${classNameLower}Service")
public class Simple${className}Service extends GenericEntityService<${className}Entity,String>implements ${className}Service{

        @Autowired
        private ${className}Dao ${classNameLower}Dao;

        @Override
        protected IDGenerator<String> getIDGenerator(${className}Entity entity){
                String jsbh=entity.getJsbh();
                return()->{
                return getSEQUID(jsbh);
            };
        }

        @Override
        public ${className}Dao getDao(){
                return ${classNameLower}Dao;
        }
        @Override
        @UseDataSource("write_ds")
        public int deleteByPk(String pk){
            return super.deleteByPk(pk);
        }

        @Override
        @UseDataSource("write_ds")
        public String getSEQUID(String jsbh){
        	return super.getSEQUID(jsbh);
        }

        @Override
        @UseDataSource("write_ds")
        public String insert(${className}Entity entity){
        	entity.setCreatetime(Calendar.getInstance().getTime());
        	return super.insert(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public String saveOrUpdate(${className}Entity entity){
        	return super.saveOrUpdate(entity);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public List<${className}Entity> selectByPk(List<String> id){
        	return super.selectByPk(id);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public ${className}Entity selectByPk(String pk){
        	return super.selectByPk(pk);
        }

        @Override
        @UseDataSource("read_ds")
        @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
        public PagerResult<${className}Entity> selectPager(Entity arg0){
        	return super.selectPager(arg0);
        }

        @Override
        @UseDataSource("write_ds")
        protected int updateByPk(${className}Entity entity){
        	return super.updateByPk(entity);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(List<${className}Entity> data){
        	return super.updateByPk(data);
        }

        @Override
        @UseDataSource("write_ds")
        public int updateByPk(String pk,${className}Entity entity){
        	entity.setUpdatetime(Calendar.getInstance().getTime());
        	return super.updateByPk(pk,entity);
        }
        
    	/**
    	 * 循环查找表字段是否含有rybh，有的话就自动生成jbxxList 方法，没有就不生成
    	 */
    	<#list table.columns as column>
    	<#if column.columnNameLower == "rybh">
        
    	@Override
    	@UseDataSource("read_ds")
    	@Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    	public ResponseMessage<PagerResult<Map<String, Object>>> jbxxlist(Entity param) {
    		PagerResult page = new PagerResult<List<Map<String, Object>>>();
    		List<Map<String, Object>> list = getDao().jbxxlist(param);
    		list.forEach(x -> {
    			if (!StringUtils.isNullOrEmpty(x.get("xb"))) {
    				x.put("xbString", CacheUtils.get().getDictionary("XB", x.get("xb").toString()));
    			}
    			if (!StringUtils.isNullOrEmpty(x.get("hjd"))) {
    				x.put("hjdString", CacheUtils.get().getDictionary("XZQH", x.get("hjd").toString()));
    			}
    			if (!StringUtils.isNullOrEmpty(x.get("ay"))) {
    				x.put("ayString", CacheUtils.get().getDictionary("JLSAJLB", x.get("ay").toString()));
    			}
    			if (!StringUtils.isNullOrEmpty(x.get("bahj"))) {
    				x.put("bahjString", CacheUtils.get().getDictionary("BAJD", x.get("bahj").toString()));
    			}
    			if (!StringUtils.isNullOrEmpty(x.get("rsrq"))) {
    				x.put("rsrqString", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(x.get("rsrq")));
    			}
    			if (!StringUtils.isNullOrEmpty(x.get("cssj"))) {
    				x.put("cssjString", new SimpleDateFormat("yyyy-MM-dd").format(x.get("cssj")));
    			}

    		});

    		page.setData(list);
    		page.setTotal(getDao().jbxxcount(param));
    		return ResponseMessage.ok(page);
    	}
    	
    	</#if>
    	</#list>

}
