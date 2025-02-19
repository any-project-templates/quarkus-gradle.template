package gateway.configuration;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import temporal.workflow.CreateTaskWorkflow;
import temporal.workflow.ExecuteTaskWorkflow;

@Dependent
public class TemporalConfiguration {
    @ApplicationScoped
    public WorkflowClient workflowClient(
            @ConfigProperty(name = "temporal.host") String temporalHost
    ) {
        var service = WorkflowServiceStubs.newServiceStubs(
                WorkflowServiceStubsOptions.newBuilder().setTarget(temporalHost).build()
        );

        return WorkflowClient.newInstance(service);
    }

    @ApplicationScoped
    public CreateTaskWorkflow createTaskWorkflow(
            WorkflowClient client
    ) {
        var options = WorkflowOptions.newBuilder()
                .setWorkflowId("CREATE_WORKFLOW_ID")
                .setTaskQueue("CREATE_QUEUE_NAME")
                .build();

        return client.newWorkflowStub(CreateTaskWorkflow.class, options);
    }

    @ApplicationScoped
    public ExecuteTaskWorkflow executeTaskWorkflow(WorkflowClient client) {
        var options = WorkflowOptions.newBuilder()
                .setWorkflowId("EXECUTE_WORKFLOW_ID")
                .setTaskQueue("EXECUTE_QUEUE_NAME")
                .build();

        return client.newWorkflowStub(ExecuteTaskWorkflow.class, options);
    }
}
