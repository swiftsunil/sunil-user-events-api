package com.pearson.qa.notifications.functionaltests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pearson.qa.common.configuration.modules.XmlConfigurationModule;
import com.pearson.qa.common.utilities.datageneration.DataGenerator;
import com.pearson.qa.common.ziggyfw.datastructures.CommandStack;
import com.pearson.qa.common.ziggyfw.http.modules.HttpModule;
import com.pearson.qa.common.ziggyfw.http.rest.modules.RestModule;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventReadExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.modules.NotificationsEventsModule;
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
public class NotificationsEventReadTest
{
        private static final String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        int maxSize = DataGenerator.generateRandomInt(1, 5);

        private NotificationsContextFactory notificationsContextFactory;
        private Injector injector;
        private CommandStack history;

        private NotificationEvents notificationsEvents;

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

        @Test(groups = { "Owner:Vishwanath", "Priority:1"})
        public void NotificationsEventsReadSingleTest() throws Exception
        {
            List<String> recipients = new ArrayList<>();
            for(int i=0; i<maxSize; i++)
            {
                recipients.add(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
                recipients.add(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
            }

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

            notificationsEvents = new NotificationEvents();
            notificationsEvents.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationsEvents.setProductType(DataGenerator.generateRandomString(10,20,RANDOM));
            notificationsEvents.setEventType(notificationsEvents.getAppType() + DataGenerator.generateRandomString(10,20,RANDOM));
            notificationsEvents.setAuthorId(String.valueOf(DataGenerator.generateRandomInt(2, 2)));
            notificationsEvents.setRecipientIds(recipients);
            notificationsEvents.setEventModel(eventModelObject);

            //posting a single notification.
            NotificationsEventsPostContext notificationsEventsPostContext = notificationsContextFactory.getCreateNotificationsEventsPostContext(notificationsEvents);
            notificationsEventsPostContext.setAuthorizationToken("1234");
            notificationsEventsPostContext.setRequestContentType("application/json");
            notificationsEventsPostContext.setNewNotificationEvent(notificationsEvents);
            history.push(injector.getInstance(NotificationsEventsPostExecutionDriver.class).execute(notificationsEventsPostContext));

            //reading the single posted notification.
            NotificationsEventReadContext notificationsEventReadContext = notificationsContextFactory.getCreateNotificationsEventReadContext(notificationsEvents);
            notificationsEventReadContext.setAuthorizationToken("1234");
            notificationsEventReadContext.setExpectedNotificationEvent(notificationsEvents);
            notificationsEventReadContext.setEventId(notificationsEventsPostContext.getReturnedNotificationEvent().getId());
            history.push(injector.getInstance(NotificationsEventReadExecutionDriver.class).execute(notificationsEventReadContext));
        }

}
