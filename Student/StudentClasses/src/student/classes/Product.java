package student.classes;

public class Product {
    private final double VAT_PERCENTAGE = 0.25;
    private String name;
    private String description = "";
    private double netPrice;
    private int unitsInStock;
    private int stockReorderThreshold;


    public Product() {
        this.name = "Untitled";
        this.netPrice = 0.0;
        this.unitsInStock = 0;
        this.stockReorderThreshold = 0;
    }

    public Product(String name, double netPrice, int unitsInStock, int stockReorderThreshold) {
        this.name = name;
        this.netPrice = netPrice;
        this.unitsInStock = unitsInStock;
        this.stockReorderThreshold = stockReorderThreshold;
    }

    public boolean isAvailable() {
        if (this.unitsInStock > 0) {
            return true;
        }
        else {
            System.out.println("Product not available");
            return false;
        }
    }
    public boolean isAvailable(int quantity) {
        if (this.unitsInStock >= quantity) {
            return true;
        }
        else {
            System.out.printf("%s product%s not available.\n", quantity, quantity == 1 ? "" : "s" );
            return false;
        }
    }

    public double getSalesTax() {
        double salesTax = 0;
        if (this.netPrice > 0) {
            salesTax = this.netPrice * this.VAT_PERCENTAGE;
        }
        return salesTax;
    }

    public double getGrossPrice() {
        return this.netPrice + getSalesTax();
    }

    public double changeNetPrice(double newNetPrice) {
        this.netPrice = newNetPrice;
        return this.netPrice;
    }

    public boolean sellProduct(int quantity) {
        if (isAvailable(quantity)) {
            this.unitsInStock -= quantity;
            System.out.printf("Stock reduced by %s %s\n", quantity, (quantity > 1 ? "units" : "unit"));
            return true;
        }
        else {
            return false;
        }
    }

    public boolean needToReorder() {
        return (this.unitsInStock < this.stockReorderThreshold);
    }

    /*
     * General
     */
    @Override
    public String toString() {
        return String.format("%s has a price of %.2f kr", this.name, this.getGrossPrice());
    }


    /*
     * Getters and setters
     */

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public int getStockReorderThreshold() {
        return stockReorderThreshold;
    }

}
