package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import org.apache.http.client.methods.HttpGet;

import java.net.HttpURLConnection;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 6:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventReadContext extends Context
{
    private NotificationEvents returnedNotificationEvent;
    private NotificationEvents expectedNotificationEvent;
    private String eventId;

    public NotificationsEventReadContext() throws Exception
    {
        super();
        setExpectedHttpResponseCode(HttpURLConnection.HTTP_OK);
        setRequestMethod(HttpGet.METHOD_NAME);
    }

    @Inject
    public NotificationsEventReadContext(@Assisted NotificationEvents notificationEvent) throws Exception
    {
        this();
        setExpectedNotificationEvent(notificationEvent);
    }

    public NotificationEvents getReturnedNotificationEvent()
    {
        return returnedNotificationEvent;
    }

    public void setReturnedNotificationEvent(NotificationEvents returnedNotificationEvent)
    {
        this.returnedNotificationEvent = returnedNotificationEvent;
    }

    public NotificationEvents getExpectedNotificationEvent()
    {
        return expectedNotificationEvent;
    }

    public void setExpectedNotificationEvent(NotificationEvents expectedNotificationEvent)
    {
        this.expectedNotificationEvent = expectedNotificationEvent;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId)
    {
        this.eventId = eventId;
    }

}