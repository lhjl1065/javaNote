package cn.lhjl.domain;

import java.util.List;

public class PageBean<T> {
    private int rows;//每页显示的记录数
    private int currentPage;//当前页码
    private int totalCount;//总记录数
    private int totalPage;//总页数
    private List<T> list;//当前页的记录数据

    public PageBean(int rows, int currentPage, int totalCount, int totalPage, List<T> list) {
        this.rows = rows;
        this.currentPage = currentPage;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
    }

    public PageBean() {
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "rows=" + rows +
                ", currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                '}';
    }
}
