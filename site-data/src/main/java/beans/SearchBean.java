package beans;

public class SearchBean {
    private String hMenu;

    private String hSubMenu;

    private String browseCategory;

    private String browseCategoryFilter;

    private String storing;

    private int itemLocation;

    public String gethMenu() {
        return hMenu;
    }

    public SearchBean sethMenu(String hMenu) {
        this.hMenu = hMenu;
        return this;
    }

    public String gethSubMenu() {
        return hSubMenu;
    }

    public SearchBean sethSubMenu(String hSubMenu) {
        this.hSubMenu = hSubMenu;
        return this;
    }

    public String getbrowseCategory() {
        return browseCategory;
    }

    public SearchBean setbrowseCategory(String browseCategory) {
        this.browseCategory = browseCategory;
        return this;
    }

    public String getbrowseCategoryFilter() {
        return browseCategoryFilter;
    }

    public SearchBean setbrowseCategoryFilter(String browseCategoryFilter) {
        this.browseCategoryFilter = browseCategoryFilter;
        return this;
    }

    public String getstoring() {
        return storing;
    }

    public SearchBean setstoring(String storing) {
        this.storing = storing;
        return this;
    }

    public int getitemLocation() {
        return itemLocation;
    }

    public SearchBean setitemLocation(int itemLocation) {
        this.itemLocation = itemLocation;
        return this;
    }

}