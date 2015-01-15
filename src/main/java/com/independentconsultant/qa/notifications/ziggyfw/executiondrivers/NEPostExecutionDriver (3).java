package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsPostContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 7:09 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class NotificationsUserEventsPostExecutionDriver extends ExecutionDriver<NotificationsUserEventsPostContext>
{
    @Inject
    public NotificationsUserEventsPostExecutionDriver(List<Executor<NotificationsUserEventsPostContext>> executors)
    {
        super(executors);
    }
}
