package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsPostContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
@Singleton
public class NotificationsEventsPostExecutionDriver extends ExecutionDriver<NotificationsEventsPostContext>
{
    @Inject
    public NotificationsEventsPostExecutionDriver(List<Executor<NotificationsEventsPostContext>> executors)
    {
        super (executors);
    }
}
