package SPPLab;

public class ServiceOrder extends Order {

	public float Cost;

	public ServiceOrder() {
		// TODO Auto-generated constructor stub
		this.Employee = new Employee(); this.Vehicle = new Vehicle();
	}

	public ServiceOrder(String[] splittedStringFromFile) {

		this(Integer.parseInt(splittedStringFromFile[0]), splittedStringFromFile[1], splittedStringFromFile[2],
				splittedStringFromFile[3], splittedStringFromFile[4], Float.parseFloat(splittedStringFromFile[5]));
	}

	public ServiceOrder(int orderNo, String vehicleBrand, String vehicleModel, String employeeName, String employeePos,
			float cost) {
		OrderNo = orderNo;
		this.Vehicle = new Vehicle(vehicleBrand, vehicleModel);
		this.Employee = new Employee(employeeName, employeePos);
		Cost = cost;
	}

	@Override
	public String ToString() {
		// TODO Auto-generated method stub
		return new String("Заказ № :" + this.OrderNo + " | Автомобиль: " + this.Vehicle.VehicleBrand + "  "
				+ this.Vehicle.VehicleModel + " | Работник: " + this.Employee.EmployeeName + "("+this.Employee.EmployeePosition +")" + " | Сумма по заказу: " + this.Cost);

	}
	
	public String ToTextFile() {
		return this.OrderNo + "~"+ this.Vehicle.VehicleBrand + "~" + this.Vehicle.VehicleBrand + "~" +
				this.Employee.EmployeeName + "~" + this.Employee.EmployeePosition + "~" + this.Cost;
	}

	@Override
	public void InputFromConsole() {
		// TODO Auto-generated method stub

	}

	@Override
	public int getOrderNo() {
		// TODO Auto-generated method stub
		return OrderNo;
	}


}
