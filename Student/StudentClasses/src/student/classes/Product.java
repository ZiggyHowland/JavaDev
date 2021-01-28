package student.classes;

public class Product {
    public static final double VAT_PERCENTAGE = 0.25; // Shared constant value for all product objects
    private final int id;
    private String name;
    private String description;
    private double netPrice;
    private int unitsInStock;
    private boolean deprecated;
    private final int stockReorderThreshold; // Constant value (after first init), not shared among product objects
    private static int nextId = 1432; // Shared value accross objects/intances (not constant)


    /*
     GENERAL QUESTIONS:
     #1: I guess we have situations where we want static final variables to be public. Any good examples?
     #2: I made the above VAT_PERCENTAGE variable public just to test accessing it from main. Is this ok to do, or should this as well be kept a secret and access only through a method?
     #3: Static finals (like VAT_PERCENTAGE) must be added a value in the declaration, and not in the constructor. Right?
     */


    {
        this.id = nextId++;
        this.deprecated = false;
    }
    /*
     Seems like best practice for Instance Initialization Blocks (IIBs) are initializing instance variables, and to keep constructors clean related
     Source: https://www.geeksforgeeks.org/instance-initialization-block-iib-java/
     An argument of using it is to make sure code is always run, irrespective of which constructor is run
     Source: https://www.quora.com/What-is-the-purpose-of-the-instance-block-in-java
     */


    public Product(String name) {
        // Used this to test the constructor chaining
        this(name, 0.0, 0, 0);
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
        return this.netPrice > 0 ? this.netPrice * VAT_PERCENTAGE : 0;
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

    // ANDY: No need for parens.
    // I know, but I think it makes it more readable and allready at first ( indicates it has something to be calculated before return... (good or bad thought)
    public boolean needToReorder() {
        return (this.unitsInStock < this.stockReorderThreshold);
    }



    /*
     * General
     */
    @Override
    public String toString() {
        return String.format("PID %d: %s has a price of %.2f kr", this.id, this.name, this.getGrossPrice());
    }




    /*
     * Getters and setters
     */

    // Added using IntelliJ (Alt+Insert) just to verify your comment about "is" boolean getters
    public boolean isDeprecated() {
        return deprecated;
    }
    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public int getStockReorderThreshold() {
        return stockReorderThreshold;
    }
}
