package project;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import javax.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.component.jms.JmsComponent;

public class RunAggregator
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
              
              from("jms:queue:All_Tasks")
              .choice()
              .when(header("TaskProject").isEqualTo("None"))
              .log("Sending ${body}")
              .to("jms:queue:AggregatedProjectMessages")
              .otherwise()
              .aggregate(header("TaskProject"), new MyAggregationStrategy())
              .completionTimeout(10000)
              .log("Sending ${body}")
              .to("jms:queue:AggregatedProjectMessages")
              .end();
          }
      }
      );

      context.start();
      Thread.sleep(60000);
      context.stop();
   }
}
