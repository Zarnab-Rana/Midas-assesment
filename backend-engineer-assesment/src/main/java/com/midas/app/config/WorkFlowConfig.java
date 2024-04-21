package com.midas.app.config;

import com.midas.app.activities.AccountActivityImpl;
import com.midas.app.patmentgateway.service.CustomerService;
import com.midas.app.repositories.AccountRepository;
import com.midas.app.workflows.*;
import io.temporal.client.WorkflowClient;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class WorkFlowConfig {

  private final WorkflowClient workflowClient;

  private final AccountRepository accountRepository;

  private final CustomerService customerService;

  public void startCreateAccountWorker() {
    WorkerFactory workerFactory = WorkerFactory.newInstance(workflowClient);
    Worker worker = workerFactory.newWorker(CreateAccountWorkflow.QUEUE_NAME);
    worker.registerWorkflowImplementationTypes(CreateAccountWorkFlowImpl.class);
    worker.registerActivitiesImplementations(
        new AccountActivityImpl(accountRepository, customerService));
    workerFactory.start();
  }

  public void startUpdateAccountWorker() {
    WorkerFactory workerFactory = WorkerFactory.newInstance(workflowClient);
    Worker worker = workerFactory.newWorker(UpdateAccountWorkflow.QUEUE_NAME);
    worker.registerWorkflowImplementationTypes(UpdateAccountWorkFlowImpl.class);
    worker.registerActivitiesImplementations(
        new AccountActivityImpl(accountRepository, customerService));
    workerFactory.start();
  }
}
