package com.pearson.qa.notifications.ziggyfw.validators;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;

import java.net.HttpURLConnection;

public class NotificationsUserEventsReadValidator<T extends NotificationsUserEventsReadContext> implements Validator<T>
{

    @Override
    public boolean canValidate(T context)
    {
        if((context.getReturnedResponseBody()== null) && (context.getReturnedResponseBody().isEmpty()))
            return false;
        if(context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_OK)
            return false;

        return true;
    }

    //expectedNotificationEvent not validated because of its dynamically created content (eventModel).
    @Override
    public void validate(T context) throws ValidationException
    {
    	NotificationUserEventsCollection returnedNotificationsUserEvent = context.getReturnedNotificationsUserEvent();
        returnedNotificationsUserEvent.validateState();
    }
}
