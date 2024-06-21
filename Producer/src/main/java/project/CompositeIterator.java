package project;

import java.util.ArrayList;

public class CompositeIterator extends PmIterator
{
   public int currentPos;
   public ArrayList<Component> projectTaskList;
   
   public CompositeIterator(Project p)
   {
      this.projectTaskList = p.tasksProjects;
      this.currentPos = 1;
   }
   
   public Component Next()
   {
       currentPos++;
       return projectTaskList.get(currentPos - 2);
   }
   
   public Boolean isDone()
   {
      if(currentPos > projectTaskList.size())
      {
          return true;
      }

      else
      {
          return false;
      }
   }
}
