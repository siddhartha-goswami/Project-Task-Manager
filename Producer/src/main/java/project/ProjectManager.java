package project;

import java.util.ArrayList;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ProjectManager
{
   public Project centralRepository;
   
   private ProjectManager()
   {
      centralRepository = new Project("P0","Central repository", 1);
   }
   
   public static ProjectManager getInstanceProjectManager()
   {
      if(instance == null)
      {
         instance = new ProjectManager();
      }
      
      return instance;
   }
   
   public void execRepository() throws Exception
   {
      CompositeIterator ci = centralRepository.createIterator();
      
      while(true)
      {
         Component c = ci.Next();
         
         if(c instanceof Task)
         {
            Task tc = (Task)c;
            String folderString = "data/inbox/";
            String filenameString = tc.name;
            String filepathString = folderString + filenameString + ".csv";
            
            String[] fileContentStrings = {"Task name: "+tc.name,"Task description: "+tc.taskDescription,
                  "Task type: "+tc.taskType, "Task project: "+tc.projectName, "Assigned to: "+tc.assignedEmpl};
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepathString)))
            {
               StringBuilder linebuild = new StringBuilder();
               
               for(int i = 0; i < fileContentStrings.length; i++)
               {
                  linebuild.append(fileContentStrings[i]);
                  if(i < fileContentStrings.length - 1)
                  {
                     linebuild.append(",");
                  }
               }
               
               bw.write(linebuild.toString());
               bw.write("\n");
            }
            
            catch (IOException e) 
            {
               e.printStackTrace();
            }
         }
         
         else if(c instanceof Project)
         {
            Project cpro = (Project)c;
            String folderString = "data/inbox/";
            String filenameString = cpro.name;
            String filepathString = folderString + filenameString + ".csv";
            
            ArrayList<String> taskList = new ArrayList<String>();
            CompositeIterator cpi = cpro.createIterator(); 
            
            while(true)
            {
               Component projTaskComp = cpi.Next(); 
               if(projTaskComp instanceof Task)
               {
                  Task projTask = (Task)projTaskComp; 
                  StringBuilder taskInfo = new StringBuilder();
                  
                  taskInfo.append("Task name: "+projTask.name);
                  taskInfo.append(",");
                  taskInfo.append("Task description: "+projTask.taskDescription);
                  taskInfo.append(",");
                  taskInfo.append("Task type: "+projTask.taskType);
                  taskInfo.append(",");
                  taskInfo.append("Task project: "+projTask.projectName);
                  taskInfo.append(",");
                  taskInfo.append("Assigned to: "+projTask.assignedEmpl);
                  
                  taskList.add(taskInfo.toString());
               }
               
               if(cpi.isDone())
               {
                  break;
               }
            }
            
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filepathString)))
            {
               for(String line : taskList)
               {
                  bw.write(line);
                  bw.write("\n");
               }
            }
            
            catch (IOException e) 
            {
               e.printStackTrace();
            }
         }
         
         if(ci.isDone())
         {
            break;
         }
      }
      
      CamelContext context = new DefaultCamelContext();

      ConnectionFactory connectionFactory = 
       new ActiveMQConnectionFactory("tcp://localhost:61616");
      
      context.addComponent("jms",
          JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
      
      context.addRoutes
      (new RouteBuilder() 
      {
          public void configure() 
          {
              from("file:data/inbox?noop=true&recursive=true").
              setHeader("FileHeader").simple("${file:name}").
              log("RETRIEVED: ${file:name}").unmarshal().
              csv().split(body().tokenize("\n"))
              .to("jms:queue:Producer")
              .to("jms:queue:AssignmentRequestQueue");
              
              from("jms:queue:AssignmentRequestQueue")
              .process(new Processor()
               {
                  @Override
                  public void process(Exchange e) throws Exception
                  {
                     String outputBody = "Created a new assignment " + 
                  e.getIn().getHeader("FileHeader", String.class).substring(0, 
                        e.getIn().getHeader("FileHeader", String.class).length() - 4);
                     e.getIn().setBody(outputBody);
                  }
               })
              .to("jms:queue:AssignmentConfirmationQueue");
          }
      }
      );

      context.start();
      Thread.sleep(20000);
      context.stop();
   }
   
   private static ProjectManager instance = null;
}