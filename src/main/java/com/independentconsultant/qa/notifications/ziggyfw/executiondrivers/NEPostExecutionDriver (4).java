package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;

import java.util.List;

public class NotificationsUserEventsReadExecutionDriver extends ExecutionDriver<NotificationsUserEventsReadContext>
{
    @Inject
    public NotificationsUserEventsReadExecutionDriver(List<Executor<NotificationsUserEventsReadContext>> executors)
    {
        super(executors);
    }
}
