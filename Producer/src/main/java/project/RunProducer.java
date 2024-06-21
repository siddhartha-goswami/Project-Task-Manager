package project;

public class RunProducer
{
   public static void main(String[] args) throws Exception
   {
      UserInterface ui = new UserInterface();
      
      Project p1 = ui.createProject("P1","E-Commerce Website", 3);
      Task p1t1 = ui.createTask("P1T1", "Designing login page and UI","Frontend", "Jude Shaw");
      Task p1t2 = ui.createTask("P1T2", "Implementing REST API", "Backend", "Luke Bellingham");
      Task p1t3 = ui.createTask("P1T3", "Integrating payment gateway", "Backend", "Trent James");
      
      ui.addTasksToProject(p1, p1t1, p1t2, p1t3);
      
      Project p2 = ui.createProject("P2","Online Learning Platform", 3);
      Task p2t1 = ui.createTask("P2T1", "Developing user profiles page","Frontend", "Marcus Kane");
      Task p2t2 = ui.createTask("P2T2", "Categorize content for navigation", "Backend", "Harry Foden");
      Task p2t3 = ui.createTask("P2T3", "Implementing user analytics feature", "Backend", "Luke Bellingham");
      
      ui.addTasksToProject(p2, p2t1, p2t2, p2t3);
      
      Task t1 = ui.createTask("T1", "Feature testing all projects", "General", "Trent James");
      Task t2 =  ui.createTask("T2", "Generating project reports", "General", "Marcus Kane");
      Task t3 = ui.createTask("T3", "Deploying projects to cloud", "General", "Jude Shaw");
      
      ui.addToCentralRepository(p1, p2, t1, t2, t3);
      
      ui.executeRepository();
   }
}
