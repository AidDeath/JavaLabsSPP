package SPPLab4;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import SPPLab.Order;
import SPPLab.OrderReader;

public class SPPLab4 {

	public SPPLab4() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Set<Order> ordersSet = new TreeSet<Order>();
		ordersSet.addAll(OrderReader.ReadFromXML("src\\FilesForLoad\\WarrantyOrders.xml"));
		// cортируется при добавлении элементов, используя интерфейс Comparable реализованный суперклассом

		List<Order> ordersList = OrderReader.ReadFromFile("src\\FilesForLoad\\ServiceOrders.txt");
		Collections.sort(ordersList, Comparator.comparingInt(Order::getOrderNo));
		// Сортировка с использованием Comparator

		Map<Order, Integer> ordersMap = new HashMap<>();
		for (Order order : OrderReader.RandomFill(10))
			ordersMap.put(order, order.OrderNo);
		

		System.out.println("Коллекция Set");
		Iterator<Order> treeIterator = ordersSet.iterator();
		while (treeIterator.hasNext())
			System.out.println(treeIterator.next().ToString());

		System.out.println("Коллекция List");
		Iterator<Order> listIterator = ordersList.iterator();
		while (listIterator.hasNext())
			System.out.println(listIterator.next().ToString());

		TreeMap<Order, Integer> ordersTreeMap = new TreeMap<Order,Integer>();
		ordersTreeMap.putAll(ordersMap); 
		//Сортировка при помощи интерфейса Comparable и класса TreeMap
		System.out.println("Коллекция Map");
		Iterator<Map.Entry<Order, Integer>> mapIterator = ordersTreeMap.entrySet().iterator();
		while (mapIterator.hasNext()) {
			System.out.println( mapIterator.next().getKey().ToString());
		}

	}
}
