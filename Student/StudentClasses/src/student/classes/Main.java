package student.classes;

public class Main {

	public static void main(String[] args) {
		
		Product p1 = new Product("Black coffee", 29, 10, 2);

		p1.setDescription("One cup of plain, normal black coffee");
		System.out.println(p1.toString());
		System.out.printf("Availability of %s: %s\n", p1.getName(), p1.isAvailable() ? " ready to order" : "out of stock");
		System.out.println("---");
		System.out.println("Increase price of coffee");
		p1.changeNetPrice(32.5);
		System.out.println(p1.toString());

		System.out.println("---");
		System.out.println("Trying to sell 11 products");
		p1.sellProduct(11);
		System.out.println("Trying to sell 9 products");
		p1.sellProduct(9);

		System.out.println("---");
		System.out.println("Do we need to reorder?");
		if (p1.needToReorder()) {
			System.out.printf("Yes. Units available is below the threshold of %s\n", p1.getStockReorderThreshold());
		}
		else {
			System.out.printf("No. Units available in stock: %s\n", p1.getUnitsInStock());
		}

		p1 = null;

	}
}
