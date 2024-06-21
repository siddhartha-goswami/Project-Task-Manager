package project;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class RunSplitter
{
   public static void main(String[] args) throws Exception
   {
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
              from("jms:queue:Producer").
              log("RECEIVED:  jms queue: ${body} from file: ${header.CamelFileNameOnly}").
              split().tokenize("\\],\\s*\\[", true).
              process(new Processor()
                    {
                       public void process(Exchange e) throws Exception 
                       {
                          String body = e.getIn().getBody(String.class);
                          
                          if (!body.startsWith("[[")) 
                          {
                              if(body.startsWith("["))
                              {
                                 body = "[" + body;
                              }
                              
                              else 
                              {
                                 body = "[[" + body;
                              }
                          }
                          if (!body.endsWith("]]")) 
                          {
                             if(body.endsWith("]"))
                             {
                                body = body + "]";
                             }
                             
                             else 
                             {
                                body = body + "]]";
                             }
                          }
                          e.getIn().setBody(body);
                       }
                    }).
              choice()
              .when(body().regex(".*Jude Shaw.*"))
              .to("jms:topic:TOPIC_JudeShaw")
              .when(body().regex(".*Marcus Kane.*"))
              .to("jms:topic:TOPIC_MarcusKane")
              .when(body().regex(".*Luke Bellingham.*"))
              .to("jms:topic:TOPIC_LukeBellingham")
              .when(body().regex(".*Trent James.*"))
              .to("jms:topic:TOPIC_TrentJames")
              .when(body().regex(".*Harry Foden.*"))
              .to("jms:topic:TOPIC_HarryFoden")
              .otherwise()
              .to("jms:topic:TOPIC_InvalidTaskAssignment");
          }
      }
      );

      context.start();
      Thread.sleep(10000);
      context.stop();
   }
}
