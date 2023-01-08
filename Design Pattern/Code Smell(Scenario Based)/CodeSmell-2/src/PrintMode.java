public abstract class PrintMode {
    private int numberOfPages;
    private int pageSize;
    private String orientation;
    private String colorIntensity;
    private String costPerPage;

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getColorIntensity() {
        return colorIntensity;
    }

    public void setColorIntensity(String colorIntensity) {
        this.colorIntensity = colorIntensity;
    }

    public String getCostPerPage() {
        return costPerPage;
    }

    public void setCostPerPage(String costPerPage) {
        this.costPerPage = costPerPage;
    }

    //Refused Bequest
    public abstract void configure();
}
