package core.domain.ping;

import jakarta.enterprise.context.ApplicationScoped;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PingService {
    private final Logger logger;

    public PingService(
            Logger logger
    ) {
        this.logger = logger;
    }

    public String ping() {
        return "pong";
    }
}
