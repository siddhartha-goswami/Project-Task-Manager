package project;

public class Employee
{
   public String name;
   
   public Employee(String name)
   {
      this.name = name;
   }
   
   public void executeTask(Task t)
   {
      CodeStrategy strategy1 = new MERNStrategy();
      CodeStrategy strategy2 = new PythonStrategy();
      
      int temp = (Math.random() <= 0.5)? 1:2;
      
      if(temp == 1)
      {
         strategy1.execTask(t);
      }
      
      else if(temp == 2)
      {
         strategy2.execTask(t);
      }
   }
}