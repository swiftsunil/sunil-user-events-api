package com.pearson.qa.notifications.functionaltests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.pearson.qa.common.configuration.modules.XmlConfigurationModule;
import com.pearson.qa.common.utilities.datageneration.DataGenerator;
import com.pearson.qa.common.ziggyfw.datastructures.CommandStack;
import com.pearson.qa.common.ziggyfw.http.modules.HttpModule;
import com.pearson.qa.common.ziggyfw.http.rest.modules.RestModule;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsPostContext;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsPostExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.executiondrivers.NotificationsUserEventsReadExecutionDriver;
import com.pearson.qa.notifications.ziggyfw.modules.NotificationsEventsModule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NotificationsUserEventsReadTest
{
        private static final String RANDOM = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";

        private NotificationsContextFactory notificationsContextFactory;
        private Injector injector;
        private CommandStack history;

        private NotificationUserEventsCollection notificationUserEventsCollection;

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

        @Test(groups = { "Owner:Sunil", "Priority:1"})
        public void NotificationsUserEventsReadSingleTest() throws Exception
        {

            //The event model is to be dynamically procured from the consuming product/app. Hence hard coded for testing purposes.
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

            // Setting appType, productType, eventType, notificationsRecieved, Author.
            notificationUserEventsCollection = new NotificationUserEventsCollection();
            notificationUserEventsCollection.setAppType(DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationUserEventsCollection.setProductType(DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationUserEventsCollection.setEventType(notificationUserEventsCollection.getAppType() + DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationUserEventsCollection.setAuthorId(DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationUserEventsCollection.setRecipientId(DataGenerator.generateRandomString(10, 20, RANDOM));
            notificationUserEventsCollection.setEventModel(eventModelObject);

            //posting a single notification.
            NotificationsUserEventsPostContext notificationsUserEventsPostContext = notificationsContextFactory.getCreateNotificationsUserEventsPostContext(notificationUserEventsCollection);
            notificationsUserEventsPostContext.setAuthorizationToken("1234");
            notificationsUserEventsPostContext.setRequestContentType("application/json");
            notificationsUserEventsPostContext.setNewNotificationsUserEventsCollection(notificationUserEventsCollection);
            history.push(injector.getInstance(NotificationsUserEventsPostExecutionDriver.class).execute(notificationsUserEventsPostContext));

              
            //reading the single posted notification.
            NotificationsUserEventsReadContext notificationsUserEventsReadContext = notificationsContextFactory.getCreateNotificationsUserEventsReadContext(notificationUserEventsCollection);
            notificationsUserEventsReadContext.setAuthorizationToken("1234");
            notificationsUserEventsReadContext.setRequestContentType("application/json");
            notificationsUserEventsReadContext.setExpectedNotificationsUserEvent(notificationUserEventsCollection);
            notificationsUserEventsReadContext.setUserEventId(notificationsUserEventsPostContext.getReturnedNotificationsUserEventsCollection().getId());
            history.push(injector.getInstance(NotificationsUserEventsReadExecutionDriver.class).execute(notificationsUserEventsReadContext));
        }

}
