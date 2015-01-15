package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsUpdateContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsUpdateExecutionDriver extends ExecutionDriver<NotificationsUserEventsUpdateContext>
{
    @Inject
    public NotificationsUserEventsUpdateExecutionDriver(List<Executor<NotificationsUserEventsUpdateContext>> executors)
    {
        super(executors);
    }

}
