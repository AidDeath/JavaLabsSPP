package SPPLab;

public class WarrantyOrder extends Order  {

	public WarrantyOrder() {
		// TODO Auto-generated constructor stub
		this.Employee = new Employee();
		this.Vehicle = new Vehicle();
	}

	public WarrantyOrder(int orderNo, String vehicleBrand) {
		OrderNo = orderNo;
		Vehicle.VehicleBrand = vehicleBrand;
	}

	@Override
	public String ToString() {
		return "Заказ № :" + this.OrderNo + " | Автомобиль: " + this.Vehicle.VehicleBrand + "  "
				+ this.Vehicle.VehicleModel + " | Работник: " + this.Employee.EmployeeName + "("
				+ this.Employee.EmployeePosition + ")" + " | Гарантийный случай";
	}

	@Override
	public void InputFromConsole() {
		// TODO Auto-generated method stub

	}

	@Override
	public String ToTextFile() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getOrderNo() {
		// TODO Auto-generated method stub
		return OrderNo;
	}

}
