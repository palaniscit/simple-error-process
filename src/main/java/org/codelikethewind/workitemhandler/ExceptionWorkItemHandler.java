package org.codelikethewind.workitemhandler;

import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class ExceptionWorkItemHandler implements WorkItemHandler {

	public static String exceptionParameterName = "jbpm.workitem.exception";


	@Override
	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		manager.abortWorkItem(workItem.getId());
		
	}

	@Override
	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {

    	WorkItem exceptionWorkItem = (WorkItem) workItem.getParameter("exceptionWorkItem");

		handleException(exceptionWorkItem);
		manager.completeWorkItem(workItem.getId(), null);	
		
	}

	public void handleException(WorkItem workItem) {
    	System.out.println( "Handling exception caused by work item '" + workItem.getName() + "' (id: " + workItem.getId() + ")");
        
    	Map<String, Object> params = workItem.getParameters();
    	Throwable throwable = (Throwable) params.get(exceptionParameterName);
    	throwable.printStackTrace();
  }

}
