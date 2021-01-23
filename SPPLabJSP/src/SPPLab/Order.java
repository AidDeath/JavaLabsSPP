package SPPLab;

public abstract class Order implements IOrder, Comparable<Order>  {
	public int OrderNo;
	public int VehicleCode;
	public Vehicle Vehicle;
	public int CustomerCode;
	public int EmployeeCode;
	public Employee Employee;
	
	public abstract String ToString();
	public abstract String ToTextFile();
	
	public abstract int getOrderNo();
	
	@Override
	public int compareTo(Order o) {

		return ((Integer) OrderNo).compareTo(o.OrderNo);
	}
	
}
