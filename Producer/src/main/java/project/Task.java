package project;

public class Task extends Component
{
   protected boolean taskCompleted;
   protected String assignedEmpl;
   public String taskType;
   public String taskDescription;
   public String projectName;
   
   public Task(String name, String td, String type, String emplName)
   {
      super(name);
      this.taskType = type;
      this.taskDescription = td;
      this.projectName = "None";
      this.assignedEmpl = emplName;
      this.taskCompleted = false;
   }
   
   @Override
   public void AddComponent(Component c)
   {
       System.out.println("Cannot add to a task\n");
   }

   @Override
   public void RemoveComponent(Component c)
   {
       System.out.println("Cannot remove from a task\n");
   }
   
   @Override
   public PmIterator createIterator()
   {
       PmIterator it = new NullIterator();
       return it;
   }
}
