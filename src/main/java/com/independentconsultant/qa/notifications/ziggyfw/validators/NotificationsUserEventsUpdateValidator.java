package com.pearson.qa.notifications.ziggyfw.validators;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsUpdateContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsReadExecutionDriver;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 12:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsUpdateValidator<T extends NotificationsUserEventsUpdateContext> implements Validator<T>
{
    private final NotificationsUserEventsReadExecutionDriver notificationsUserEventsReadExecutionDriver;
    private final NotificationsContextFactory notificationsContextFactory;

    @Inject
    public NotificationsUserEventsUpdateValidator(NotificationsUserEventsReadExecutionDriver notificationsUserEventsReadExecutionDriver, NotificationsContextFactory notificationsContextFactory)
    {
        this.notificationsUserEventsReadExecutionDriver = notificationsUserEventsReadExecutionDriver;
        this.notificationsContextFactory = notificationsContextFactory;
    }

    @Override
    public boolean canValidate(T context)
    {
        if((context.getReturnedResponseBody() == null) && (context.getReturnedResponseBody().isEmpty()))
            return false;
        if(context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_OK)
            return false;

        return true;
    }

    @Override
    public void validate(T context) throws ValidationException
    {
    }

}

