package com.pearson.qa.notifications.ziggyfw.validators;

import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadListContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;

import java.net.HttpURLConnection;

public class NotificationsUserEventsReadListValidator<T extends NotificationsUserEventsReadListContext> implements Validator<T>
{

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
        for( NotificationUserEventsCollection notificationEventsCollection : context.getExpectedNotificationUserEvents())
        {
            notificationEventsCollection.validateState();
        }
    }
}
