package com.pearson.qa.notifications.ziggyfw.validators;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventReadExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsReadExecutionDriver;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 7:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsPostValidator<T extends NotificationsUserEventsPostContext> implements Validator<T>
{
    private final NotificationsUserEventsReadExecutionDriver notificationsUserEventsReadExecutionDriver;
    private final NotificationsContextFactory notificationsContextFactory;

    @Inject
    public NotificationsUserEventsPostValidator(NotificationsUserEventsReadExecutionDriver notificationsUserEventsReadExecutionDriver, NotificationsContextFactory notificationsContextFactory)
    {
        this.notificationsUserEventsReadExecutionDriver = notificationsUserEventsReadExecutionDriver;
        this.notificationsContextFactory = notificationsContextFactory;
    }

    @Override
    public boolean canValidate(T context)
    {
        if((context.getReturnedResponseBody() == null) && (context.getReturnedResponseBody().isEmpty()))
            return false;
        if(context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_CREATED)
            return false;
        else
            return true;
    }

    @Override
    public void validate(T context) throws ValidationException
    {
        try
        {
            NotificationsUserEventsReadContext notificationsUserEventsReadContext = notificationsContextFactory.getCreateNotificationsUserEventsReadContext(context.getReturnedNotificationsUserEventsCollection());
            notificationsUserEventsReadContext.setAuthorizationToken(context.getAuthorizationToken());
            notificationsUserEventsReadContext.setUserEventId(context.getReturnedNotificationsUserEventsCollection().getId());
            notificationsUserEventsReadContext.setExpectedNotificationsUserEvent(context.getReturnedNotificationsUserEventsCollection());
            notificationsUserEventsReadExecutionDriver.execute(notificationsUserEventsReadContext);
        }
        catch (Exception e)
        {
            throw new ValidationException(String.format("%s performs a %s, which failed.", getClass().getCanonicalName(), NotificationsUserEventsReadExecutionDriver.class.getCanonicalName()), e);
        }
    }
}
