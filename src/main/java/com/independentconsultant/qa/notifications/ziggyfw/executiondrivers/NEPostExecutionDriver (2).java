package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsReadContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsReadExecutionDriver extends ExecutionDriver<NotificationsEventsReadContext>
{
    @Inject
    public NotificationsEventsReadExecutionDriver(List<Executor<NotificationsEventsReadContext>> executors)
    {
        super(executors);
    }
}
