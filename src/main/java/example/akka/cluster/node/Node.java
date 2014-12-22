package example.akka.cluster.node;

import akka.actor.*;
import akka.routing.FromConfig;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * This starts a node in the cluster and optionally simulates incoming messages.
 * Start with parameters [port=?] and [message-sender]. Both parameters are optional.
 * When port is not provided, a random one will be chosen.
 */
public class Node {
    public static void main(String[] args) {

        // Override the configuration of the port when specified as program argument
        int port = (args.length > 0) ? Integer.parseInt(args[0]) : 0;

        Config config = ConfigFactory
                .parseString("akka.remote.netty.tcp.port=" + port)
                .withFallback(ConfigFactory.load());

        // Creating environment
        ActorSystem system = ActorSystem.create("AkkaClusterNode", config);

        // Spread out messages among the nodes in the cluster.
        boolean sendMessages = (args.length > 1 && args[1].equals("message-sender"));
        if (sendMessages) {
            ActorRef uppercaseRouter = system.actorOf(
                    FromConfig.getInstance().props(Props.create(UppercaseActor.class)),
                    "uppercaseRouter");

            // Send a "Tick" message to the uppercaseRouter each second.
            system.scheduler().schedule(Duration.Zero(),
                    Duration.create(1, TimeUnit.SECONDS), uppercaseRouter, "Tick",
                    system.dispatcher(), null);
        }

    }
}
