package com.pearson.qa.notifications.ziggyfw.contexts;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.pearson.qa.notifications.ziggyfw.common.contexts.NotificationsListContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import org.apache.http.client.methods.HttpGet;

import java.net.HttpURLConnection;
import java.util.List;


public class NotificationsUserEventsReadListContext extends NotificationsListContext<NotificationUserEventsCollection>
{
    private List<NotificationUserEventsCollection> expectedNotificationUserEvents;
    private List<NotificationUserEventsCollection> returnedNotificationUserEvents;
    
    private String recipientId;  
    
  
	public NotificationsUserEventsReadListContext() throws Exception
    {
        super();
        setExpectedHttpResponseCode(HttpURLConnection.HTTP_OK);
        setRequestMethod(HttpGet.METHOD_NAME);
    }

    @Inject
    public NotificationsUserEventsReadListContext(@Assisted List<NotificationUserEventsCollection> expectedNotificationUserEvents) throws Exception
    {
        this();
        setExpectedNotificationUserEvents(expectedNotificationUserEvents);
    }

    
    public String getRecipientId()
    {
  		return recipientId;
  	}

  	public void setRecipientId(String recipientId)
    {
  		this.recipientId = recipientId;
  	}


	public List<NotificationUserEventsCollection> getExpectedNotificationUserEvents()
    {
		return expectedNotificationUserEvents;
	}

	public void setExpectedNotificationUserEvents(List<NotificationUserEventsCollection> expectedNotificationUserEvents)
    {
		this.expectedNotificationUserEvents = expectedNotificationUserEvents;
	}

	public List<NotificationUserEventsCollection> getReturnedNotificationUserEvents()
    {
		return returnedNotificationUserEvents;
	}

	public void setReturnedNotificationUserEvents(List<NotificationUserEventsCollection> returnedNotificationUserEvents)
    {
		this.returnedNotificationUserEvents = returnedNotificationUserEvents;
	}

}
