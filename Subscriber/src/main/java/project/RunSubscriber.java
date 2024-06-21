package project;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class RunSubscriber
{
    public static void main(String args[]) throws Exception 
    {
        CamelContext context = new DefaultCamelContext();
        EmployeeManager em = new EmployeeManager();

        ConnectionFactory connectionFactory = 
         new ActiveMQConnectionFactory("tcp://localhost:61616");
        
        context.addComponent("jms",
            JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        context.addRoutes
        (new RouteBuilder() 
        {
            public void configure() 
            {
                from("jms:topic:TOPIC_JudeShaw").
                log("SUBSCRIBER RECEIVED: jms JudeShaw queue: ${body} from file: ${header.CamelFileNameOnly}")
                .process(new Processor()
                      {
                         public void process(Exchange e) throws Exception 
                         {
                            String[] line = e.getIn().getBody(String.class).split(", ");
                            String taskName = line[0].substring(13).strip();
                            String taskDescription = line[1].substring(18).strip();
                            String taskType = line[2].substring(11).strip();
                            String taskProject = line[3].substring(14).strip();
                            
                            Task t = new Task(taskName, taskDescription, taskType);
                            Employee emp = em.getEmpl("Jude Shaw");
                            
                            emp.executeTask(t);
                            e.getIn().setHeader("TaskProject", taskProject);
                            
                            StringBuilder outputBody = new StringBuilder();
                            outputBody = outputBody.append("Task name: ").append(taskName).append("|");
                            outputBody = outputBody.append("Task description: ").append(taskDescription).append("|");
                            outputBody = outputBody.append("Task project: ").append(taskProject).append("|");
                            outputBody = outputBody.append("COMPLETED");
                            
                            e.getIn().setBody(outputBody.toString());
                         }
                      })
                .to("jms:queue:All_Tasks");
                
                from("jms:topic:TOPIC_MarcusKane").
                log("SUBSCRIBER RECEIVED: jms MarcusKane queue: ${body} from file: ${header.CamelFileNameOnly}")
                .process(new Processor()
                      {
                         public void process(Exchange e) throws Exception 
                         {
                            String[] line = e.getIn().getBody(String.class).split(", ");
                            String taskName = line[0].substring(13).strip();
                            String taskDescription = line[1].substring(18).strip();
                            String taskType = line[2].substring(11).strip();
                            String taskProject = line[3].substring(14).strip();
                            
                            Task t = new Task(taskName, taskDescription, taskType);
                            Employee emp = em.getEmpl("Marcus Kane");
                            
                            emp.executeTask(t);
                            e.getIn().setHeader("TaskProject", taskProject);
                            
                            StringBuilder outputBody = new StringBuilder();
                            outputBody = outputBody.append("Task name: ").append(taskName).append("|");
                            outputBody = outputBody.append("Task description: ").append(taskDescription).append("|");
                            outputBody = outputBody.append("Task project: ").append(taskProject).append("|");
                            outputBody = outputBody.append("COMPLETED");
                            
                            e.getIn().setBody(outputBody.toString());
                         }
                      })
                .to("jms:queue:All_Tasks");
                
                from("jms:topic:TOPIC_LukeBellingham").
                log("SUBSCRIBER RECEIVED: jms LukeBellingham queue: ${body} from file: ${header.CamelFileNameOnly}")
                .process(new Processor()
                      {
                         public void process(Exchange e) throws Exception 
                         {
                            String[] line = e.getIn().getBody(String.class).split(", ");
                            String taskName = line[0].substring(13).strip();
                            String taskDescription = line[1].substring(18).strip();
                            String taskType = line[2].substring(11).strip();
                            String taskProject = line[3].substring(14).strip();
                            
                            Task t = new Task(taskName, taskDescription, taskType);
                            Employee emp = em.getEmpl("Luke Bellingham");
                            
                            emp.executeTask(t);
                            e.getIn().setHeader("TaskProject", taskProject);
                            
                            StringBuilder outputBody = new StringBuilder();
                            outputBody = outputBody.append("Task name: ").append(taskName).append("|");
                            outputBody = outputBody.append("Task description: ").append(taskDescription).append("|");
                            outputBody = outputBody.append("Task project: ").append(taskProject).append("|");
                            outputBody = outputBody.append("COMPLETED");
                            
                            e.getIn().setBody(outputBody.toString());
                         }
                      })
                .to("jms:queue:All_Tasks");
                
                from("jms:topic:TOPIC_TrentJames").
                log("SUBSCRIBER RECEIVED: jms TrentJames queue: ${body} from file: ${header.CamelFileNameOnly}")
                .process(new Processor()
                      {
                         public void process(Exchange e) throws Exception 
                         {
                            String[] line = e.getIn().getBody(String.class).split(", ");
                            String taskName = line[0].substring(13).strip();
                            String taskDescription = line[1].substring(18).strip();
                            String taskType = line[2].substring(11).strip();
                            String taskProject = line[3].substring(14).strip();
                            
                            Task t = new Task(taskName, taskDescription, taskType);
                            Employee emp = em.getEmpl("Trent James");
                            
                            emp.executeTask(t);
                            e.getIn().setHeader("TaskProject", taskProject);
                            
                            StringBuilder outputBody = new StringBuilder();
                            outputBody = outputBody.append("Task name: ").append(taskName).append("|");
                            outputBody = outputBody.append("Task description: ").append(taskDescription).append("|");
                            outputBody = outputBody.append("Task project: ").append(taskProject).append("|");
                            outputBody = outputBody.append("COMPLETED");
                            
                            e.getIn().setBody(outputBody.toString());
                         }
                      })
                .to("jms:queue:All_Tasks");
                
                from("jms:topic:TOPIC_HarryFoden").
                log("SUBSCRIBER RECEIVED: jms HarryFoden queue: ${body} from file: ${header.CamelFileNameOnly}")
                .process(new Processor()
                      {
                         public void process(Exchange e) throws Exception 
                         {
                            String[] line = e.getIn().getBody(String.class).split(", ");
                            String taskName = line[0].substring(13).strip();
                            String taskDescription = line[1].substring(18).strip();
                            String taskType = line[2].substring(11).strip();
                            String taskProject = line[3].substring(14).strip();
                            
                            Task t = new Task(taskName, taskDescription, taskType);
                            Employee emp = em.getEmpl("Harry Foden");
                            
                            emp.executeTask(t);
                            e.getIn().setHeader("TaskProject", taskProject);
                            
                            StringBuilder outputBody = new StringBuilder();
                            outputBody = outputBody.append("Task name: ").append(taskName).append("|");
                            outputBody = outputBody.append("Task description: ").append(taskDescription).append("|");
                            outputBody = outputBody.append("Task project: ").append(taskProject).append("|");
                            outputBody = outputBody.append("COMPLETED");
                            
                            e.getIn().setBody(outputBody.toString());
                         }
                      })
                .to("jms:queue:All_Tasks");
            }
        }
        );

        context.start();
        Thread.sleep(100000);
        context.stop();
    }
}