package com.fanglai.utils.page;

import java.util.List;

public class Page<T> {
	// 1.每页显示数量(everyPage)
	private int pageSize;
	// 2.当前页(currentPage)
	private int currentPage;
	// 3.总记录数(totalCount)
	private int totalCount;
	// 4.总页数(totalPage)
	private int totalPage;
	// 5.起始点(beginIndex)
	private int beginIndex;
	// 6.是否有上一页(hasPrePage)
	private boolean hasPrePage;
	// 7.是否有下一页(hasNextPage)
	private boolean hasNextPage;
	// 8.数据
	private List<T> data;

	public Page(int pageSize, int totalCount, int totalPage, int currentPage,
			int beginIndex, boolean hasPrePage, boolean hasNextPage) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.totalPage = totalPage;
		this.currentPage = currentPage;
		this.beginIndex = beginIndex;
		this.hasPrePage = hasPrePage;
		this.hasNextPage = hasNextPage;
	}
	
	public Page(int pageSize, int currentPage) {
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.beginIndex = PageUtil.getBeginIndex(pageSize, currentPage);
	}

	// 构造函数，默认
	public Page() {
	}

	// 构造方法，对所有属性进行设置

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getBeginIndex() {
		return beginIndex;
	}

	public void setBeginIndex(int beginIndex) {
		this.beginIndex = beginIndex;
	}

	public boolean isHasPrePage() {
		return hasPrePage;
	}

	public void setHasPrePage(boolean hasPrePage) {
		this.hasPrePage = hasPrePage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
	
}
