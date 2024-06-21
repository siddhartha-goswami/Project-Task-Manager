package project;

public class PythonStrategy extends CodeStrategy
{
   @Override
   public void execTask(Task t)
   {
      if(t.taskType == "Frontend")
      {
         System.out.println(t.name + " using HTML, CSS and JS");
      }
      else if(t.taskType == "Backend")
      {
         System.out.println(t.name + " using Python Django");
      }
      else 
      {
         System.out.println(t.name);
      }
   }
}
