package cn.lhjl.domain;

public class QueryVo  {
    private User user;
    private int rows;
    private int currentPage;
    private int currentIndex;

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                ", rows=" + rows +
                ", currentPage=" + currentPage +
                ", currentIndex=" + currentIndex +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }
}
