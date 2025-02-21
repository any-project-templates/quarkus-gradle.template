package core.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import core.domain.ping.PingService;

@Path("api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PingApi {
    private final PingService pingService;

    public PingApi(PingService pingService) {
        this.pingService = pingService;
    }

    @GET
    @Path("/ping")
    public PingResponse ping() {
        return new PingResponse(pingService.ping());
    }

    record PingResponse(String value) {
    }
}
