package SPPLab5;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import SPPLab.Order;
import SPPLab.OrderReader;


public class SPPLab5 {

	public SPPLab5() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		String TxtPath = "src\\FilesForLoad\\ServiceOrdersThr.txt";
		Thread thread1 = new Thread(() ->{
			System.out.println("Поток записи в файл ЗАПУЩЕН");
			List<Order> randOrders = OrderReader.RandomFill(1000);
			try {
				BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(TxtPath));
				//запись в файл рандомных заказов
				System.out.println("Запись в файл НАЧАТА");
				for (Order ord :randOrders) {
					bw.write(ord.ToTextFile() + "\n");
				}
				System.out.println("Запись в файл ЗАВЕРШЕНА");
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Поток записи в файл ЗАВЕРШЕН");
			

		});
		
		Thread thread2 = new Thread(() ->{
			System.out.println("Поток чтения из файла ЗАПУЩЕН");
			if (thread1.isAlive()) {
				System.out.println("Чтение ПРИОСТАНОВЛЕНО, активен поток записи");
				try {
					thread1.join();
					System.out.println("Чтение ВОЗОБНОВЛЕНО, обнаружено завершение потока записи");
					 OrderReader.ReadFromFile(TxtPath);
			System.out.println("Чтение ЗАВЕРШЕНО");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			System.out.println("Поток чтения из файла ЗАВЕРШЕН");
		//чтение из файла рандомных заказов
		});
			
		
		//thread1.start();
		
		thread2.start();
		thread1.start();
		try {
			thread1.join();
			thread2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
