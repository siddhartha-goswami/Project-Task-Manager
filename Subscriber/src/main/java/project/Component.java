package project;

public abstract class Component
{
   public String name;
   
   public Component(String name)
   {
      this.name = name;
   }
   
   abstract public void AddComponent(Component c);
   abstract public void RemoveComponent(Component c);
   abstract public PmIterator createIterator();
   
   public String getName()
   {
      return this.name;
   }
}