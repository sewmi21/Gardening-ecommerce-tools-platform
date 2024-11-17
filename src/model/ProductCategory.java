package model;

public class ProductCategory {

    private int id;
    private String name;
    private String description;
    private int productCount;
    public ProductCategory( int id, String name, String description, int productCount) {
        
        this.id = id;
        this.name = name;
        this.description = description;
        this.productCount = productCount;
    }

    public int getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public String getDescription() { 
        return description; 
    }

    public int getproductCount() { 
        return productCount; 
    }

    public void setProductCount(int productCount) { 
        this.productCount = productCount; 
    }
    
    @Override public String toString() { 
        return name;
    }
}
