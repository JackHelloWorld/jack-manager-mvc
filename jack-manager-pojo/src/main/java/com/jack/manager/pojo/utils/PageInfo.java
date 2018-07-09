package com.jack.manager.pojo.utils;

import java.util.ArrayList;
import java.util.List;

import com.jack.manager.common.config.Const;

/**
 * 页--基类
 * @author liuJack
 *
 */
public class PageInfo {
	
	/**页大小*/
	private Integer pageSize;
	
	/**当前页*/
	private Integer currentPage;
	
	/**总页数*/
	@SuppressWarnings("unused")
	private Integer totalPage;
	
	/*** 总条数*/
	private Long total;
	
	/**总条数 */
	private List<?> rows;

	public PageInfo() {
		
	}
	
	public PageInfo(Integer pageSize,Integer pageNumber){
		this.pageSize=pageSize;
		this.currentPage = pageNumber;
	}
	
	public Integer getPageSize() {
		return pageSize==null?Const.Page.pageSize:pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage==null?Const.Page.currentPage:currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Long getTotalPage() {
		if(getPageSize() == 0)
			return 0L;
		Long totalPage = getTotal()/getPageSize();
		return getTotal()%getPageSize()==0?totalPage:totalPage+1;
	}

	public Long getTotal() {
		return total==null?0:total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@SuppressWarnings("rawtypes")
	public List getRows() {
		return rows;
	}

	public <T> void setRows(List<T> rows) {
		this.rows = rows==null?new ArrayList<T>():rows;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findObjListArr(){
		return (List<Object[]>) this.rows;
	}
	
}
