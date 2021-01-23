package SPPLab;

import java.util.ArrayList;
import java.util.Scanner;

public class SPPLab {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Order> OrderList = new ArrayList<Order>();

		Scanner scanner = new Scanner(System.in);

		int choise = 999;

		while (choise != 0) {
			System.out.println(
					"Выберите действие:\n1 - Ввести заказ через консоль\n2 - Считать из файла\n3 - Cчитать из xml\n4 - Сгенерировать случайный заказ\n5 - Показать всё\n0 - Выход");
			choise = scanner.nextInt();
			switch (choise) {
			case 1:
				try {
				OrderList.add(OrderReader.ReadFromConsole());
				}
				catch (OrderException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				ArrayList<Order> fileOrders = OrderReader.ReadFromFile("src\\FilesForLoad\\ServiceOrders.txt");
				if (fileOrders.size() != 0) {
					System.out.println("Считано " + fileOrders.size() + " строк данных о заказах");
					OrderList.addAll(fileOrders);
				}
				break;
			case 3:
				ArrayList<Order> xmlOrders = OrderReader.ReadFromXML("src\\FilesForLoad\\WarrantyOrders.xml");
				if (xmlOrders.size() != 0) {
					System.out.println("Считано " + xmlOrders.size() + " строк данных о заказах");
					OrderList.addAll(xmlOrders);
				}
				break;
			case 4: 
				System.out.println("Введите количество случайных заказов:");
				OrderList.addAll(OrderReader.RandomFill(scanner.nextInt()));
				break;
			case 5:
				for (Order order : OrderList) {
					System.out.println(order.ToString());
				}
				break;
			case 0:
				break;
			default:
				System.out.println("Неверный ввод!");
				break;
			}
		}

		
		scanner.close();

	}

}
