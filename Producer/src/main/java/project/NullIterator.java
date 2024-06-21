package project;

public class NullIterator extends PmIterator
{
   public Component Next()
   {
       return null;
   }
   
   public Boolean isDone()
   {
       return true;
   }
}