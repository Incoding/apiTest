package com.javaapi.test.projects.flowable.first;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by user on 2019/2/13
 */
public class HolidayRequest {

    private ProcessEngine processEngine;

//    @Before
//    public void before(){
//        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
//                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
//                .setJdbcUsername("sa")
//                .setJdbcPassword("")
//                .setJdbcDriver("org.h2.Driver")
//                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
//        processEngine = cfg.buildProcessEngine();
//    }
//
//
//    @Test
//    public void test(){
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        Deployment deployment = repositoryService.createDeployment()
//                                                 .addClasspathResource("flowable/holiday-request.bpmn20.xml")
//                                                 .deploy();
//
//        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
//                                                               .deploymentId(deployment.getId())
//                                                               .singleResult();
//        System.out.println("Found process definition : " + processDefinition.getName());
//
//    }
//
//
//    @Test
//    public void testInput(){
//        Scanner scanner= new Scanner(System.in);
//
//        System.out.println("Who are you?");
//        String employee = scanner.nextLine();
//
//        System.out.println("How many holidays do you want to request?");
//        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());
//
//        System.out.println("Why do you need them?");
//        String description = scanner.nextLine();
//
//        RuntimeService runtimeService = processEngine.getRuntimeService();
//
//        Map<String, Object> variables = new HashMap<String, Object>();
//        variables.put("employee", employee);
//        variables.put("nrOfHolidays", nrOfHolidays);
//        variables.put("description", description);
//        ProcessInstance processInstance =
//                runtimeService.startProcessInstanceByKey("holidayRequest", variables);
//    }

    /**
     *  ProcessEngine and the services objects are thread safe,
     * @param args
     */
    public static void main(String[] args) {
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        String resource = "flowable/holiday-request.bpmn20.xml";

        Deployment deployment = repositoryService.createDeployment()
                                                 .addClasspathResource(resource)
                                                 .deploy();

        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                                                               .deploymentId(deployment.getId())
                                                               .singleResult();
        System.out.println("Found process definition : " + processDefinition.getName());


        Scanner scanner= new Scanner(System.in);

        System.out.println("Who are you?");
        String employee = scanner.nextLine();

        System.out.println("How many holidays do you want to request?");
        Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

        System.out.println("Why do you need them?");
        String description = scanner.nextLine();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 申请
        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("employee", employee);
        variables.put("nrOfHolidays", nrOfHolidays);
        variables.put("description", description);
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("holidayRequest", variables);

        // 审核列表
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
        System.out.println("You have " + tasks.size() + " tasks:");
        for (int i=0; i<tasks.size(); i++) {
            System.out.println((i+1) + ") " + tasks.get(i).getName());
        }
        // 审核详情
        System.out.println("Which task would you like to complete?");
        int taskIndex = Integer.valueOf(scanner.nextLine());
        Task task = tasks.get(taskIndex - 1);
        Map<String, Object> processVariables = taskService.getVariables(task.getId());
        System.out.println(processVariables.get("employee") + " wants " +
                processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");
        // 审批意见
        boolean approved = scanner.nextLine().toLowerCase().equals("y");
        variables = new HashMap<String, Object>();
        variables.put("approved", approved);
        taskService.complete(task.getId(), variables);

        // 审批历史
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities =
                historyService.createHistoricActivityInstanceQuery()
                              .processInstanceId(processInstance.getId())
                              .finished()
                              .orderByHistoricActivityInstanceEndTime().asc()
                              .list();

        for (HistoricActivityInstance activity : activities) {
            System.out.println("type "+activity.getActivityType()+" "+activity.getActivityId() + " took "
                    + activity.getDurationInMillis() + " milliseconds");
        }
    }
}
