package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import org.apache.http.client.methods.HttpPost;

import java.net.HttpURLConnection;

public class NotificationsEventsPostContext extends Context
{
    private NotificationEvents newNotificationEvent;
    private NotificationEvents returnedNotificationEvent;

    public NotificationsEventsPostContext() throws Exception
    {
        super();
        setNewNotificationEvent(new NotificationEvents());
        setReturnedNotificationEvent(new NotificationEvents());

        setExpectedHttpResponseCode(HttpURLConnection.HTTP_CREATED);
        setRequestMethod(HttpPost.METHOD_NAME);
    }

    public NotificationsEventsPostContext(@Assisted NotificationEvents newNotificationEvent) throws Exception
    {
        this();
        setNewNotificationEvent(newNotificationEvent);
        setReturnedNotificationEvent(new NotificationEvents(newNotificationEvent));
    }

    public NotificationEvents getNewNotificationEvent()
    {
        return newNotificationEvent;
    }

    public void setNewNotificationEvent(NotificationEvents newNotificationEvent)
    {
        this.newNotificationEvent = newNotificationEvent;
    }

    public NotificationEvents getReturnedNotificationEvent()
    {
        return returnedNotificationEvent;
    }

    public void setReturnedNotificationEvent(NotificationEvents returnedNotificationEvent)
    {
        this.returnedNotificationEvent = returnedNotificationEvent;
    }
}
