package SPPLab6;

import java.io.*;
import java.net.Socket;
import java.util.List;

import SPPLab.Order;
import SPPLab.OrderReader;
import SPPLab.ServiceOrder;

public class ServerLoad extends Thread {

	private static List<Order> OrdersList;

	static {//Коллекция с заказами, общая для каждого потока
		OrdersList = OrderReader.ReadFromFile("src\\FilesForLoad\\ServiceOrders.txt");
		// Сразу запишем заказы из файла, чтоб не было пусто
	}

	//private final Socket socket;
	private final BufferedReader in;
	private final BufferedWriter out;

	public ServerLoad(Socket socket) throws IOException {
		//this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Входящий поток от сокета
		this.out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); // Исходящий поток в сокет
		start();
	}

	@Override
	public void run() {
		String word;
		try {
			while (true) {
				messageToSocket("\nВыберте действие:\n" + "1: Показать все заказы \n" + "2: Внести новый заказ\n");
				word = in.readLine();
				// System.out.println("DEBUG: "+ word);
				if (word != null) {
					try {
						clientRequest("" + word.charAt(word.length() - 1));
					} catch (Exception ignored) {
						clientRequest("-");
					} // Незаполненные номер заказа
					// и сумма заказа так же являются неверным вводом

				}
			}
		} catch (IOException ignored) {}
	}

	// Отправка сообщения на сокет
	private void messageToSocket(String msg) {
		try {
			out.write(msg + "\n");
			out.flush();
		} catch (IOException ignored) {	}

	}

	void clientRequest(String s) throws IOException {
		switch (s) {
		case "1":
			messageToSocket("Список заказов на сервере:\n");
			for (Order ord : OrdersList) {
				messageToSocket(ord.ToString());
			}
			messageToSocket(" **** ----- **** \n");
			break;
		case "2":
			String input;
			messageToSocket("Введите номер заказа: ");
			input = in.readLine();
			int orderNo = Integer.parseInt(input.substring(input.lastIndexOf(' ') + 1));
			messageToSocket("Введите марку авто: ");
			input = in.readLine();
			String veihcleBrand = input.substring(input.lastIndexOf(' ') + 1);
			messageToSocket("Введите модель авто: ");
			input = in.readLine();
			String veihcleModel = input.substring(input.lastIndexOf(' ') + 1);
			messageToSocket("Введите ФИО работника: ");
			input = in.readLine();
			String employeeName = input.substring(input.lastIndexOf(' ') + 1);
			messageToSocket("Введите должность работника: ");
			input = in.readLine();
			String employeePos = input.substring(input.lastIndexOf(' ') + 1);

			messageToSocket("Введите сумму заказа: ");
			input = in.readLine();
			float cost = Float.parseFloat(input.substring(input.lastIndexOf(' ') + 1));
			// age = Integer.parseInt(input.substring(input.lastIndexOf(' ') + 1));
			OrdersList.add(new ServiceOrder(orderNo, veihcleBrand, veihcleModel, employeeName, employeePos, cost));

			messageToSocket("Заказ добавлен");
			break;
		default:
			messageToSocket("Неверный ввод!\n");
			break;
		}
	}
}