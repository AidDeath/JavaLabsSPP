package SPPLab6;

import java.io.*;
import java.net.Socket;

public class Client {
	
	private static String ipAddr = "localhost";
	private static int port = 8080;
	private Socket socket;
	private BufferedReader in; // поток чтения из сокета
	private BufferedWriter out; // поток записи в сокет
	private BufferedReader userInput; // поток чтения с консоли

	public static void main(String[] args) {  
		//Точка входа клиентского приложения
		new Client(ipAddr, port);
	}
	

	public Client(String addr, int port) {
		try {
			this.socket = new Socket(addr, port);
		} catch (IOException e) {
			System.err.println("Не удалось подключиться к серверу!");
		}
		try {
			// потоки чтения из сокета / записи в сокет, и чтения с консоли
			userInput = new BufferedReader(new InputStreamReader(System.in));
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

			new ReadFromServer().start(); // Поток читает сообщения сервера и выводит в консоль
			new WriteToServer().start(); // Поток, читающий консольный ввод и отправляющий сообщеня на сервер
		} catch (IOException e) {
			Client.this.shutDownClient();
		}
	}

	// закрытие сокета
	private void shutDownClient() {
		try {
			if (!socket.isClosed()) {
				socket.close();
				in.close();
				out.close();
			}
		} catch (IOException ignored) {
		}
	}

	private class ReadFromServer extends Thread {
		@Override
		public void run() {
			try {
				while (true)
					System.out.println(in.readLine()); // читаем сообщения сервера и выводим в консоль
			} catch (IOException ignored) {
			}
		}
	}

	public class WriteToServer extends Thread {
		@Override
		public void run() {
			while (true) {
				try {
					out.write(userInput.readLine() + "\n"); // отправляем сообщение на сервер из консоли
					out.flush();
				} catch (IOException ignored) {
				}
			}
		}
	}
}
