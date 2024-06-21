package project;

public class MERNStrategy extends CodeStrategy
{
   @Override
   public void execTask(Task t)
   {
      if(t.taskType == "Frontend")
      {
         System.out.println(t.name + " using React.js");
      }
      else if(t.taskType == "Backend")
      {
         System.out.println(t.name + " using Node.js");
      }
      else 
      {
         System.out.println(t.name);
      }
   }
}