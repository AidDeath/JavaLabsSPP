package SPPLab;

public class Employee {
	int EmployeeCode;
	public String EmployeeName;
	public String EmployeePosition;
	
	public Employee() {
		
	};
	
	public Employee(String employeeName, String employeePosition) {
		this.EmployeeName = employeeName; 
		this.EmployeePosition = employeePosition;
	}
}
