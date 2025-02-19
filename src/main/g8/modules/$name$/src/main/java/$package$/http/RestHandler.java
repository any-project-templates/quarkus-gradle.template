package gateway.http;

import io.temporal.client.WorkflowClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import temporal.workflow.CreateTaskWorkflow;

@Path("api/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestHandler {
    private final CreateTaskWorkflow createTaskWorkflow;

    public RestHandler(
            CreateTaskWorkflow createTaskWorkflow
    ) {
        this.createTaskWorkflow = createTaskWorkflow;
    }

    @POST
    @Path("/tasks")
    public TaskCreatedResponse createTask(@RequestBody NewTask task) {
        // async task creation
        WorkflowClient.execute(createTaskWorkflow::create, task.id);
        // blocking and require at least one alive worker
//        createTaskWorkflow.create(task.id);
        return new TaskCreatedResponse(task.id);
    }

    @GET
    @Path("/tasks/{id}")
    public TaskStateResponse getTaskById(@PathParam("id") long id) {
        return null;
    }

    record TaskCreatedResponse(long id) {}

    record TaskStateResponse() {}

    record NewTask(long id) {}
}
