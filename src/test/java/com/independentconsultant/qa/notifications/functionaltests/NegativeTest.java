package com.pearson.qa.notifications.functionaltests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pearson.qa.common.configuration.modules.XmlConfigurationModule;
import com.pearson.qa.common.utilities.datageneration.DataGenerator;
import com.pearson.qa.common.ziggyfw.datastructures.CommandStack;
import com.pearson.qa.common.ziggyfw.http.modules.HttpModule;
import com.pearson.qa.common.ziggyfw.http.rest.modules.RestModule;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsUpdateContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.datastructures.*;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsUpdateExecutionDriver;
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
 * Date: 2/28/14
 * Time: 8:28 AM
 * To change this template use File | Settings | File Templates.
 */
public class NegativeTest
{
    private static final String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    int maxSize = DataGenerator.generateRandomInt(1, 5);

    private NotificationsContextFactory notificationsContextFactory;
    private Injector injector;
    private CommandStack history;

    @BeforeMethod
    public void create()
    {
        injector = Guice.createInjector(new NotificationsEventsModule(), new HttpModule(), new RestModule(), new XmlConfigurationModule());
        notificationsContextFactory = injector.getInstance(NotificationsContextFactory.class);
        history = injector.getInstance(CommandStack.class);
    }

    @AfterMethod
    public void delete() throws Exception
    {
        history.undoAll(true);
        history = null;
    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingAuthTokenTest() throws Exception
    {
        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(getNotificationUserEvents());
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setNewNotificationEvent(getNotificationUserEvents());
        notificationsEventsPostContext.setExpectedHttpResponseCode(401);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));
    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingAuthorIdTest() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setRecipientIds(getRecipientsList());
        n.setEventModel(getEventModelObject());

        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(n);
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setNewNotificationEvent(n);
        notificationsEventsPostContext.setExpectedHttpResponseCode(400);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingAppTypeTest() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setRecipientIds(getRecipientsList());
        n.setEventModel(getEventModelObject());

        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(n);
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setNewNotificationEvent(n);
        notificationsEventsPostContext.setExpectedHttpResponseCode(400);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingProductTypeTest() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setRecipientIds(getRecipientsList());
        n.setEventModel(getEventModelObject());

        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(n);
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setNewNotificationEvent(n);
        notificationsEventsPostContext.setExpectedHttpResponseCode(400);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingRecipientIdsTest() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setEventModel(getEventModelObject());

        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(n);
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setNewNotificationEvent(n);
        notificationsEventsPostContext.setExpectedHttpResponseCode(400);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})
    public void MissingEventTypeTest() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setRecipientIds(getRecipientsList());
        n.setEventModel(getEventModelObject());

        NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(n);
        notificationsEventsPostContext.setRequestContentType("application/json");
        notificationsEventsPostContext.setAuthorizationToken("1234");
        notificationsEventsPostContext.setNewNotificationEvent(n);
        notificationsEventsPostContext.setExpectedHttpResponseCode(400);
        history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

    }

    @Test(groups={"Owner:Vishwanath", "Priority:2", "Negative"})  //NOT YET DEV-COMPLETE
    public void MissingIdTest() throws Exception
    {
        NotificationUserEventsCollection n = new NotificationUserEventsCollection();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setRecipientId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setEventModel(getEventModelObject());

        //posting a single UserEventNotification.
        NotificationsUserEventsPostContext notificationsUserEventsPostContext = notificationsContextFactory.getCreateNotificationsUserEventsPostContext(n);
        notificationsUserEventsPostContext.setAuthorizationToken("1234");
        notificationsUserEventsPostContext.setRequestContentType("application/json");
        notificationsUserEventsPostContext.setNewNotificationsUserEventsCollection(n);
        history.push(injector.getInstance(NotificationsUserEventsPostExecutionDriver.class).execute(notificationsUserEventsPostContext));

        n.setCreatedAt(notificationsUserEventsPostContext.getReturnedNotificationsUserEventsCollection().getCreatedAt());
        n.setUpdatedAt(notificationsUserEventsPostContext.getReturnedNotificationsUserEventsCollection().getUpdatedAt());
        n.setLinks(notificationsUserEventsPostContext.getReturnedNotificationsUserEventsCollection().getLinks());

        NotificationsUserEventsUpdateContext notificationsUserEventsUpdateContext = notificationsContextFactory.getCreateNotificationsUserEventsUpdateContext(n);
        notificationsUserEventsUpdateContext.setAuthorizationToken("1234");
        notificationsUserEventsUpdateContext.setRequestContentType("application/json");
        notificationsUserEventsUpdateContext.setModifiedNotificationsUserEventCollection(n);
        notificationsUserEventsUpdateContext.setExpectedHttpResponseCode(404);
        history.push(injector.getInstance(NotificationsUserEventsUpdateExecutionDriver.class).execute(notificationsUserEventsUpdateContext));

    }


    private NotificationEvents getNotificationUserEvents() throws Exception
    {
        NotificationEvents n = new NotificationEvents();
        n.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setEventType(n.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
        n.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
        n.setRecipientIds(getRecipientsList());
        n.setEventModel(getEventModelObject());

        return n;
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
        JSONObject course1 = new JSONObject();
        JSONObject course2 = new JSONObject();
        JSONObject gradeMetadata = new JSONObject() ;

        course1.put("courseName","english101");
        course1.put("courseId","101");
        course2.put("courseName","science101");
        course2.put("courseId","101");

        JSONArray courses = new JSONArray();
        courses.add(course1);
        courses.add(course2);

        gradeMetadata.put("pointsReceived","100");

        JSONObject eventModelObject = new JSONObject();
        eventModelObject.put("courses",courses);
        eventModelObject.put("gradeMetaData",gradeMetadata);

        return eventModelObject;
    }

}
