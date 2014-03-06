package com.pearson.qa.notifications.ziggyfw.contexts.factories;

import com.pearson.qa.notifications.ziggyfw.contexts.*;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 4:37 PM
 * To change this template use File | Settings | File Templates.
 */
public interface NotificationsContextFactory
{
    public NotificationsEventsPostContext getCreateNotificationsEventsPostContext(NotificationEvents newNotificationEvent);
    public NotificationsEventsPostContext getCreateNotificationsEventsPostContext();
    public NotificationsEventsReadContext getCreateNotificationsEventsReadContext(List<NotificationEvents> expectedNotificationEvents);
    public NotificationsEventReadContext getCreateNotificationsEventReadContext(NotificationEvents notificationEvents);

    public NotificationsUserEventsPostContext getCreateNotificationsUserEventsPostContext(NotificationUserEventsCollection notificationUserEventsCollection);
    public NotificationsUserEventsUpdateContext getCreateNotificationsUserEventsUpdateContext(NotificationUserEventsCollection notificationUserEventsCollection);
    public NotificationsUserEventsReadListContext getCreateNotificationsEventsReadListContext(List<NotificationUserEventsCollection> expectedNotificationUserEvents);
    public NotificationsUserEventsReadContext getCreateNotificationsUserEventsReadContext(NotificationUserEventsCollection notificationUserEventsCollection);
}
