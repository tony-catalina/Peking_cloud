package awd.cloud.internet.web.manager.utils;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


public class DefaultQueryParam {
	/**
	 * 把一些默认参数封装进qParam，state、page、rows、sort、order等
	 * @param request
	 * @param qParam
	 */
	public static void addDefaultQueryParam(HttpServletRequest request ,QueryParam qParam,String state) {
		//String state = request.getParameter("state");
		if(state != null) {
			qParam.and("state", TermType.eq, state);
		}else {
			qParam.and("state", TermType.eq, "R2");
		}
		
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		int pageIndex = 0;
		int pageSize = 10;
		try {
			pageIndex = Integer.valueOf(page).intValue() - 1;
			pageSize = Integer.valueOf(rows).intValue();
		} catch (NumberFormatException e) {
			//e.printStackTrace();
			System.err.println("分页参数出错！");
		}finally {
			qParam.setPageIndex(pageIndex);
			qParam.setPageSize(pageSize);
		}
		
        String sortName = request.getParameter("sort");
        if (sortName != null && sortName.indexOf("String") > 0) {
        	sortName = sortName.substring(0, sortName.length() - 6);
        }
        String orderBy = request.getParameter("order");
        
        List<Sort> sorts = new ArrayList<>();
        Sort sort = new Sort();
        if(sortName != null && orderBy != null) {
        	sort.setName(sortName);
        	sort.setOrder(orderBy);
        }else {
        	sort.setName("id");
        	sort.setOrder("desc");
		}
        sorts.add(sort);
        qParam.setSorts(sorts);
	}
}
