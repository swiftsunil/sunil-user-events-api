package com.pearson.qa.notifications.ziggyfw.validators;

import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventReadContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 6:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventReadValidator<T extends NotificationsEventReadContext> implements Validator<T>
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
        NotificationEvents returnedNotificationEvent = context.getReturnedNotificationEvent();
        returnedNotificationEvent.validateState();
    }
}
