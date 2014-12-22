package example.akka.cluster.node;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

public class UppercaseActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof String) {
            String string = (String) message;
            String uppercased = string.toUpperCase();

            log.info("Transforming {} into {}", string, uppercased);

            getSender().tell(uppercased, getSelf());
        }
    }
}
