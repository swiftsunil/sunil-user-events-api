package com.pearson.qa.notifications.ziggyfw.contexts;

import java.net.HttpURLConnection;
import org.apache.http.client.methods.HttpGet;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;


public class NotificationsUserEventsReadContext extends Context
{
    private NotificationUserEventsCollection returnedNotificationsUserEvent;
    private NotificationUserEventsCollection expectedNotificationsUserEvent;
    private String userEventId;

    public NotificationsUserEventsReadContext() throws Exception
    {
        super();
        setExpectedHttpResponseCode(HttpURLConnection.HTTP_OK);
        setRequestMethod(HttpGet.METHOD_NAME);
    }

    @Inject
    public NotificationsUserEventsReadContext(@Assisted NotificationUserEventsCollection notificationEventsCollection) throws Exception
    {
        this();
        setExpectedNotificationsUserEvent(notificationEventsCollection);
    }

    public NotificationUserEventsCollection getReturnedNotificationsUserEvent()
    {
        return returnedNotificationsUserEvent;
    }

    public void setReturnedNotificationsUserEvent(NotificationUserEventsCollection notificationEventsCollection)
    {
        this.returnedNotificationsUserEvent = notificationEventsCollection;
    }

    public NotificationUserEventsCollection getExpectedNotificationsUserEvent()
    {
        return expectedNotificationsUserEvent;
    }
 
    public void setExpectedNotificationsUserEvent(NotificationUserEventsCollection expectedNotificationsUserEvent)
    {
        this.expectedNotificationsUserEvent = expectedNotificationsUserEvent;
    }

    public String getUserEventId()
    {
        return userEventId;
    }

    public void setUserEventId(String userEventId)
    {
        this.userEventId = userEventId;
    }

}