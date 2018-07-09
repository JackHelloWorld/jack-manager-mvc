package com.jack.manager.pojo.utils;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Column;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

public class PageUtils {
	

	public static final PageInfo pageToPageInfo(Page<?> page){
		if(page == null)
			return null;
		PageInfo pageInfo = new PageInfo(page.getPages(), page.getPageNum());
		pageInfo.setRows(page.getResult());
		pageInfo.setTotal(page.getTotal());
		return pageInfo;
	}
	
	public static final PageInfo pageQuery(Integer pageNumber,Integer pageSize,PageRun run){
		PageHelper.startPage(pageNumber, pageSize);
		Page<?> page = run.run();
		return pageToPageInfo(page);
	}
	
	public static final <T> T mapResultToPojo(Map<String, Object> result,Class<T> pojoType) throws Exception{
		
		if(result == null) return null;
		
		Field[] fields = pojoType.getDeclaredFields();
		
		T r = pojoType.newInstance();
		
		for (Field field : fields) {
			field.setAccessible(true);
			Column column = field.getAnnotation(Column.class);
			if(column != null){
				String name = column.name();
				if(result.get(name) != null)
					field.set(r, result.get(name));
				continue;
			}
			Object value = result.get(field.getName());
			if(value != null)
				field.set(r, value);
		}
		
		return r;
		
		
	}

}
