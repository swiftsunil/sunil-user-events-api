package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import org.apache.http.client.methods.HttpPost;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 6:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsPostContext extends Context
{
    private NotificationUserEventsCollection newNotificationsUserEventsCollection;
    private NotificationUserEventsCollection returnedNotificationsUserEventsCollection;

    public NotificationsUserEventsPostContext() throws Exception
    {
        super();
        setNewNotificationsUserEventsCollection(new NotificationUserEventsCollection());
        setReturnedNotificationsUserEventsCollection(new NotificationUserEventsCollection());

        setExpectedHttpResponseCode(HttpURLConnection.HTTP_CREATED);
        setRequestMethod(HttpPost.METHOD_NAME);
    }

    public NotificationsUserEventsPostContext(@Assisted NotificationUserEventsCollection newNotificationsUserEventsCollection) throws Exception
    {
        this();
        setNewNotificationsUserEventsCollection(newNotificationsUserEventsCollection);
        setReturnedNotificationsUserEventsCollection(new NotificationUserEventsCollection(newNotificationsUserEventsCollection));
    }

    public NotificationUserEventsCollection getNewNotificationsUserEventsCollection()
    {
        return newNotificationsUserEventsCollection;
    }

    public void setNewNotificationsUserEventsCollection(NotificationUserEventsCollection newNotificationsUserEventsCollection)
    {
        this.newNotificationsUserEventsCollection = newNotificationsUserEventsCollection;
    }

    public NotificationUserEventsCollection getReturnedNotificationsUserEventsCollection()
    {
        return returnedNotificationsUserEventsCollection;
    }

    public void setReturnedNotificationsUserEventsCollection(NotificationUserEventsCollection returnedNotificationsUserEventsCollection)
    {
        this.returnedNotificationsUserEventsCollection = returnedNotificationsUserEventsCollection;
    }

}
