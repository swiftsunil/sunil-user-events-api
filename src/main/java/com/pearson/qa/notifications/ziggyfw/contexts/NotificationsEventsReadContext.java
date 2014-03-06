package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.common.contexts.NotificationsListContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import org.apache.http.client.methods.HttpGet;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsReadContext extends NotificationsListContext<NotificationEvents>
{
    private List<NotificationEvents> expectedNotificationEvents;
    private List<NotificationEvents> returnedNotificationEvents;


    public NotificationsEventsReadContext() throws Exception
    {
        super();
        setExpectedHttpResponseCode(HttpURLConnection.HTTP_OK);
        setRequestMethod(HttpGet.METHOD_NAME);
    }

    @Inject
    public NotificationsEventsReadContext(@Assisted List<NotificationEvents> expectedNotificationEvents) throws Exception
    {
        this();
        setExpectedNotificationEvents(expectedNotificationEvents);
    }


    public List<NotificationEvents> getExpectedNotificationEvents()
    {
        return expectedNotificationEvents;
    }

    public void setExpectedNotificationEvents(List<NotificationEvents> expectedNotificationEvents)
    {
        this.expectedNotificationEvents = expectedNotificationEvents;
    }

    public List<NotificationEvents> getReturnedNotificationEvents()
    {
        return returnedNotificationEvents;
    }

    public void setReturnedNotificationEvents(List<NotificationEvents> returnedNotificationEvents)
    {
        this.returnedNotificationEvents = returnedNotificationEvents;
    }

}
