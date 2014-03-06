package com.pearson.qa.notifications.functionaltests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pearson.qa.common.configuration.modules.XmlConfigurationModule;
import com.pearson.qa.common.utilities.datageneration.DataGenerator;
import com.pearson.qa.common.ziggyfw.datastructures.CommandStack;
import com.pearson.qa.common.ziggyfw.http.modules.HttpModule;
import com.pearson.qa.common.ziggyfw.http.rest.modules.RestModule;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventsReadExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.modules.NotificationsEventsModule;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/26/14
 * Time: 7:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsReadTest
{

    private static final String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    int maxSize = DataGenerator.generateRandomInt(1, 5);

    private NotificationsContextFactory notificationsContextFactory;
    private Injector injector;
    private CommandStack history;

    List<NotificationEvents> notificationEventsList = new ArrayList<>();

    @BeforeMethod
    public void create()
    {
        injector = Guice.createInjector(new NotificationsEventsModule(), new HttpModule(), new RestModule(),new XmlConfigurationModule());
        notificationsContextFactory = injector.getInstance(NotificationsContextFactory.class);
        history = injector.getInstance(CommandStack.class);
    }

    @AfterMethod
    public void delete() throws Exception
    {
        history.undoAll(true);
        history = null;
    }

    @Test(groups = { "Owner:Vishwanath", "Priority:1"})
    public void NotificationsEventsReadMultipleTest() throws Exception
    {
        NotificationEvents[] n = new NotificationEvents[maxSize];

        for(int i = 0; i < maxSize; i++)
        {
            n[i] = new NotificationEvents();
            n[i].setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setEventType(n[i].getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
            n[i].setRecipientIds(getRecipientsList());
            n[i].setEventModel(getEventModelObject());

            notificationEventsList.add(i,n[i]);

            addNotificationEvents(n[i]);
        }

        //reading the multiple posted notifications.
        NotificationsEventsReadContext notificationsEventsReadContext = notificationsContextFactory.getCreateNotificationsEventsReadContext(notificationEventsList);
        notificationsEventsReadContext.setAuthorizationToken("1234");
        notificationsEventsReadContext.setExpectedNotificationEvents(notificationEventsList);
        history.push(injector.getInstance(NotificationsEventsReadExecutionDriver.class).execute(notificationsEventsReadContext));
    }




    private void addNotificationEvents(NotificationEvents notificationsEvent) throws Exception
    {
        //posting a single notification.
        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(notificationsEvent);
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setNewNotificationEvent(notificationsEvent);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));
    }

    private List<String> getRecipientsList()
    {
        List<String> recipients = new ArrayList<>();
        for(int i=0; i<maxSize; i++)
        {
            recipients.add(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
            recipients.add(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        }

        return recipients;
    }

    private JSONObject getEventModelObject() throws JSONException
    {
        //The event model is to be dynamically procured from the consuming product/app. Hence hard coded for testing purposes.
        JSONObject course1 = new JSONObject();
        course1.put("courseName","english101");
        course1.put("courseId","101");

        JSONObject course2 = new JSONObject();
        course2.put("courseName","science101");
        course2.put("courseId","101");

        JSONArray courses = new JSONArray();
        courses.add(course1);
        courses.add(course2);

        JSONObject gradeMetadata = new JSONObject() ;
        gradeMetadata.put("pointsReceived","100");

        JSONObject eventModelObject = new JSONObject();
        eventModelObject.put("courses",courses);
        eventModelObject.put("gradeMetaData",gradeMetadata);

        return eventModelObject;
    }

}
