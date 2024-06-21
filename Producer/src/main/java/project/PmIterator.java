package project;

public abstract class PmIterator
{
   protected int currentPosition = 0;
   
   public abstract Component Next();
   public abstract Boolean isDone();
}
