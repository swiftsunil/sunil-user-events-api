package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import org.apache.http.client.methods.HttpPut;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 11:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsUpdateContext extends Context
{
    private NotificationUserEventsCollection modifiedNotificationsUserEventCollection;
    private NotificationUserEventsCollection returnedNotificationsUserEventCollection;

    public NotificationsUserEventsUpdateContext() throws Exception
    {
        super();
        setModifiedNotificationsUserEventCollection(new NotificationUserEventsCollection());
        setReturnedNotificationsUserEventCollection(new NotificationUserEventsCollection());
        setExpectedHttpResponseCode(HttpURLConnection.HTTP_OK);
        setRequestMethod(HttpPut.METHOD_NAME);
    }

    public NotificationsUserEventsUpdateContext(@Assisted NotificationUserEventsCollection notificationUserEventsCollection) throws Exception
    {
        this();
        setModifiedNotificationsUserEventCollection(notificationUserEventsCollection);
        setReturnedNotificationsUserEventCollection(new NotificationUserEventsCollection(notificationUserEventsCollection));
    }

    public NotificationUserEventsCollection getModifiedNotificationsUserEventCollection()
    {
        return modifiedNotificationsUserEventCollection;
    }

    public void setModifiedNotificationsUserEventCollection(NotificationUserEventsCollection modifiedNotificationsUserEventCollection)
    {
        this.modifiedNotificationsUserEventCollection = modifiedNotificationsUserEventCollection;
    }

    public NotificationUserEventsCollection getReturnedNotificationsUserEventCollection()
    {
        return returnedNotificationsUserEventCollection;
    }

    public void setReturnedNotificationsUserEventCollection(NotificationUserEventsCollection returnedNotificationsUserEventCollection)
    {
        this.returnedNotificationsUserEventCollection = returnedNotificationsUserEventCollection;
    }

}
