package com.pearson.qa.notifications.ziggyfw.validators;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventReadExecutionDriver;

import java.net.HttpURLConnection;
/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsPostValidator<T extends NotificationsEventsPostContext> implements Validator<T>
{
    private final NotificationsContextFactory notificationsContextFactory;
    private final NotificationsEventReadExecutionDriver notificationsEventReadExecutionDriver;

    @Inject
    public NotificationsEventsPostValidator(NotificationsContextFactory notificationsContextFactory, NotificationsEventReadExecutionDriver notificationsEventReadExecutionDriver)
    {
        this.notificationsContextFactory = notificationsContextFactory;
        this.notificationsEventReadExecutionDriver = notificationsEventReadExecutionDriver;
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
            NotificationsEventReadContext notificationsEventReadContext = notificationsContextFactory.getCreateNotificationsEventReadContext(context.getReturnedNotificationEvent());
            notificationsEventReadContext.setAuthorizationToken(context.getAuthorizationToken());
            notificationsEventReadContext.setEventId(context.getReturnedNotificationEvent().getId());
            notificationsEventReadExecutionDriver.execute(notificationsEventReadContext);
        }
        catch (Exception e)
        {
            throw new ValidationException(String.format("%s performs a %s, which failed.", getClass().getCanonicalName(), NotificationsEventReadExecutionDriver.class.getCanonicalName()), e);
        }
    }
}

