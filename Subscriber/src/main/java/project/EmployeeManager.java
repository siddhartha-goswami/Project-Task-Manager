package project;

import java.util.ArrayList;

public class EmployeeManager
{
   public ArrayList<Employee> employeeList;
   
   public EmployeeManager()
   {
      employeeList = new ArrayList<Employee>();
      
      employeeList.add(new Employee("Jude Shaw"));
      employeeList.add(new Employee("Marcus Kane"));
      employeeList.add(new Employee("Luke Bellingham"));
      employeeList.add(new Employee("Trent James"));
      employeeList.add(new Employee("Harry Foden"));
   }
   
   public Employee getEmpl(String emplName)
   {
      for(Employee employee : employeeList)
      {
         if(employee.name.equals(emplName))
         {
            return employee;
         }
      }
      
      return null;
   }
}
