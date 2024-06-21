package project;

public class UserInterface
{
   private ProjectManager pm;
   
   public UserInterface()
   {
      this.pm = ProjectManager.getInstanceProjectManager();
   }
   
   public Project createProject(String projectName, String description, int num)
   {
      return new Project(projectName, description, num);
   }
   
   public Task createTask(String name, String description, String taskType, String emplName)
   {
      return new Task(name, description, taskType, emplName);
   }
   
   public void addTasksToProject(Project p, Task... t)
   {
      for(Task tempTask : t)
      {
         tempTask.projectName = p.name;
         p.AddComponent(tempTask);
      }
   }
   
   public void addToCentralRepository(Component... comp)
   {
      for(Component c : comp)
      {
         this.pm.centralRepository.AddComponent(c);
      }
   }
   
   public void executeRepository() throws Exception
   {
      this.pm.execRepository();
   }
}