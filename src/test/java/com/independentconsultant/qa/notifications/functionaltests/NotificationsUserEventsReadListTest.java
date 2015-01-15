package com.pearson.qa.notifications.functionaltests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pearson.qa.common.configuration.modules.XmlConfigurationModule;
import com.pearson.qa.common.utilities.datageneration.DataGenerator;
import com.pearson.qa.common.ziggyfw.datastructures.CommandStack;
import com.pearson.qa.common.ziggyfw.http.modules.HttpModule;
import com.pearson.qa.common.ziggyfw.http.rest.modules.RestModule;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadListContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsReadListExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.modules.NotificationsEventsModule;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;


public class NotificationsUserEventsReadListTest
{

    private static final String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
    private static final String RECIPIENT_ID = String.valueOf(DataGenerator.generateRandomInt(2, 2));
    int maxSize = DataGenerator.generateRandomInt(1, 5);

    private NotificationsContextFactory notificationsContextFactory;
    private Injector injector;
    private CommandStack history;

    List<NotificationUserEventsCollection> notificationUserEventsList = new ArrayList<>();

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

    @Test(groups = { "Owner:Sunil", "Priority:1"})
    public void testNotificationsUserEventsReadList() throws Exception
    {
    	NotificationUserEventsCollection[] n = new NotificationUserEventsCollection[maxSize];

        for(int i = 0; i < maxSize; i++)
        {
            n[i] = new NotificationUserEventsCollection();
            n[i].setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setEventType(n[i].getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setAuthorId(DataGenerator.generateRandomString(10, 20, RANDOM));
            n[i].setRecipientId(RECIPIENT_ID);
            n[i].setEventModel(getEventModelObject());

            notificationUserEventsList.add(i,n[i]);

            addNotificationUserEvents(n[i]);
        }

        //Read Notifications User Events List
        NotificationsUserEventsReadListContext notificationsUserEventsReadListContext = notificationsContextFactory.getCreateNotificationsEventsReadListContext(notificationUserEventsList);
        notificationsUserEventsReadListContext.setAuthorizationToken("1234");
        notificationsUserEventsReadListContext.setExpectedNotificationUserEvents(notificationUserEventsList);
        notificationsUserEventsReadListContext.setRecipientId(RECIPIENT_ID);
        history.push(injector.getInstance(NotificationsUserEventsReadListExecutionDriver.class).execute(notificationsUserEventsReadListContext));
    }


    private void addNotificationUserEvents(NotificationUserEventsCollection notificationsUserEventsCollection) throws Exception
    {
    	NotificationsUserEventsPostContext notificationsUserEventsPostContext = notificationsContextFactory.getCreateNotificationsUserEventsPostContext(notificationsUserEventsCollection);
    	notificationsUserEventsPostContext.setAuthorizationToken("1234");
    	notificationsUserEventsPostContext.setRequestContentType("application/json");
    	notificationsUserEventsPostContext.setNewNotificationsUserEventsCollection(notificationsUserEventsCollection);
        history.push(injector.getInstance(NotificationsUserEventsPostExecutionDriver.class).execute(notificationsUserEventsPostContext));
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
