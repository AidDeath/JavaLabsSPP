package SPPLab;

public class Vehicle {
	int VehicleCode;
	public String VehicleBrand;
	public String VehicleModel;
	
	public Vehicle() {
		
	};
	
	public Vehicle(String vehicleBrand) {
		VehicleBrand = vehicleBrand;
	}
	
	public Vehicle(String vehicleBrand, String vehicleModel) {
		this(vehicleBrand);
		VehicleModel = vehicleModel;
	}
} 
