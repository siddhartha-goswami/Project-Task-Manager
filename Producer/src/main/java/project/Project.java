package project;

import java.util.ArrayList;

public class Project extends Component
{
   protected ArrayList<Component> tasksProjects;
   protected int numTasks;
   protected boolean projectCompleted;
   
   public Project(String name, String description, int n)
   {
      super(name);
      this.tasksProjects = new ArrayList<Component>();
      this.numTasks = n;
   }
   
   @Override
   public void AddComponent(Component c)
   {
       tasksProjects.add(c);
   }

   @Override
   public void RemoveComponent(Component c)
   {
       for(Component comp : tasksProjects)
       {
          if(comp.equals(c))
          {
             tasksProjects.remove(comp);
          }
       }
   }
   
   @Override
   public CompositeIterator createIterator()
   {
      CompositeIterator it = new CompositeIterator(this);
      return it;
   }
}