package SPPLab;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class OrderReader {
	static Order order;

	public static Order ReadFromConsole() throws OrderException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int type;

		while (true) {
			System.out.println("Выберите тип заказа:\n0 - Сервисный\n1 - Гарантийный");
			switch (scanner.nextInt()) {
			case 0:
				type = 0;
				break;
			case 1:
				type = 1;
				break;
			default:
				type = -1;
				System.out.println("Неверный ввод.");
			}
			if (type != -1)
				break;
		}
		
	try {
		switch (type) {
		case 1: {
			System.out.println("Ввод гарантийного заказа");
			WarrantyOrder warr = new WarrantyOrder();
			System.out.print("Введите номер заказа: ");
			warr.OrderNo = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Введите марку автомобиля: ");
			String b = scanner.nextLine();
			System.out.println("Введите модель автомобиля: ");
			// String m = scanner.nextLine();
			warr.Vehicle = new Vehicle(b, scanner.nextLine());

			System.out.println("Введите ФИО работника, который выполнял заказ: ");
			String n = scanner.nextLine();
			System.out.println("Должность работника:");
			// String p = scanner.nextLine();
			warr.Employee = new Employee(n, scanner.nextLine());

			order = warr;
			break;
		}
		case 0: {
			System.out.println("Ввод сервисного заказа");
			ServiceOrder serv = new ServiceOrder();
			System.out.print("Введите номер заказа: ");
			serv.OrderNo = scanner.nextInt();
			scanner.nextLine();

			System.out.print("Введите марку автомобиля: ");
			String b = scanner.nextLine();
			System.out.println("Введите модель автомобиля");
			// String m = scanner.nextLine();
			serv.Vehicle = new Vehicle(b, scanner.nextLine());

			System.out.println("Введите ФИО работника, который выполнял заказ: ");
			String n = scanner.nextLine();
			System.out.println("Должность работника:");
			// String p = scanner.nextLine();
			serv.Employee = new Employee(n, scanner.nextLine());

			System.out.print("Сумма по заказу: ");
			serv.Cost = scanner.nextFloat();
			order = serv;
			break;
		}
		}
	}
	catch (InputMismatchException e) {
        throw new OrderException("Ошибка при вводе данных о заказе!", e);
    }

		// scanner.close();
		return order;
	}

	public static ArrayList<Order> ReadFromFile(String TxtPath) {
		ArrayList<Order> orderList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(TxtPath));

			for (int i = 0; i < Files.lines(Paths.get(TxtPath)).count(); i++) {

				String st = br.readLine();
				String[] splited = st.split("~");

				orderList.add(new ServiceOrder(splited));

			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return orderList;

	}

	public static ArrayList<Order> ReadFromXML(String XmlPath) {
		ArrayList<Order> orderList = new ArrayList<>();
		try {
			BufferedReader br = new BufferedReader(new java.io.FileReader(XmlPath));
			// Разбор XML без парсера
			String buff;
			Order order = new WarrantyOrder();
			do {
				buff = br.readLine();

				if (buff.matches(".*<WarrantyOrder>.*"))
					order = new WarrantyOrder();
				if (buff.matches(".*<OrderNo>.*</OrderNo>.*"))
					order.OrderNo = Integer.parseInt(buff.substring(buff.indexOf(">") + 1, buff.indexOf("</")));

				if (buff.matches(".*<Vehicle>.*"))
					order.Vehicle = new Vehicle();
				if (buff.matches(".*<VehicleBrand>.*</VehicleBrand>.*"))
					order.Vehicle.VehicleBrand = buff.substring(buff.indexOf(">") + 1, buff.indexOf("</"));
				if (buff.matches(".*<VehicleModel>.*</VehicleModel>.*"))
					order.Vehicle.VehicleModel = buff.substring(buff.indexOf(">") + 1, buff.indexOf("</"));

				if (buff.matches(".*<Employee>.*"))
					order.Employee = new Employee();
				if (buff.matches(".*<EmployeeName>.*</EmployeeName>.*"))
					order.Employee.EmployeeName = buff.substring(buff.indexOf(">") + 1, buff.indexOf("</"));
				if (buff.matches(".*<EmployeePosition>.*</EmployeePosition>.*"))
					order.Employee.EmployeePosition = buff.substring(buff.indexOf(">") + 1, buff.indexOf("</"));

				if (buff.matches(".*</WarrantyOrder>.*"))
					orderList.add(order);

			} while (!buff.matches(".*</WarrantyOrders>.*"));
			
			br.close();

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			// e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return orderList;
	}

	public static ArrayList<Order> RandomFill(int count) {
		ArrayList<Order> radnomOrders = new ArrayList<Order>();
		Random RANDOM = new Random();
		//int n = RANDOM.nextInt(20);
		
		for (int i = 0; i < count; i++) {
			ServiceOrder order = new ServiceOrder();
			
			order.OrderNo = RANDOM.nextInt(999);
			order.Cost = RANDOM.nextFloat()*10000;
			radnomOrders.add(order);
		}
		return radnomOrders;

	}

}
