package model;

import java.util.Date;

public class Category extends Brand {

	private int category_id;
    private String category_name;

    public Category() {
    }

    public Category(String category_name, int id, String brand_name, String country_name, Date date, String user) {
        super(brand_name, country_name, date, user);
        this.category_id = id;
        this.category_name = category_name;
    }
    
    public Category(int id, int brand_id, Date date, String user) {
        super(brand_id, date, user);
        this.category_id = id;
    }
    
    public Category(String category_name, int id, Date date, String user) {
        super(date, user);
        this.category_id = id;
        this.category_name = category_name;
    }

    public Category(String category_name, Date date, String user) {
        super(date, user);
        this.category_name = category_name;
    }
    
    public int getCategory_id() {
		return category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	@Override
    public String toString() {
        return category_id + ";" + category_name + ";" + getDate() + ";" + getUser();
    }

}
