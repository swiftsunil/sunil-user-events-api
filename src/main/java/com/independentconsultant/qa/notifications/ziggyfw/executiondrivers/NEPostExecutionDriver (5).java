package com.pearson.qa.notifications.ziggyfw.executiondrivers;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executiondrivers.ExecutionDriver;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadListContext;

import java.util.List;

public class NotificationsUserEventsReadListExecutionDriver extends ExecutionDriver<NotificationsUserEventsReadListContext>
{
    @Inject
    public NotificationsUserEventsReadListExecutionDriver(List<Executor<NotificationsUserEventsReadListContext>> executors)
    {
        super(executors);
    }
}
