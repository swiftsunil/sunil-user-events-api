package com.pearson.qa.notifications.ziggyfw.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.executors.NotificationsListExecutor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationEvents;
import com.pearson.qa.notifications.ziggyfw.validators.NotificationsEventsReadValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:11 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsReadExecutor<T extends NotificationsEventsReadContext> implements Executor<T>
{
    private final NotificationsListExecutor<T> notificationsListExecutor;
    private final List<Validator<T>> validators;
    private final ObjectMapper mapper;

    @Inject
    public NotificationsEventsReadExecutor(NotificationsListExecutor<T> notificationsListExecutor, ObjectMapper mapper)
    {
        this.notificationsListExecutor = notificationsListExecutor;
        this.validators = new ArrayList<>();
        this.validators.add(new NotificationsEventsReadValidator<T>());
        this.mapper = mapper;
    }

    @Override
    public boolean canExecute(T context)
    {
        return notificationsListExecutor.canExecute(context);
    }

    @Override
    public void execute(T context) throws Exception
    {
        if(StringUtils.isBlank(context.getRequestPath()))
        {
            context.setRequestPath("/events");
        }

        context.getRequestHeaders().add(new BasicHeader("Accept", "*/*"));

        notificationsListExecutor.execute(context);

        if(context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_OK)
        {
            return;
        }

        JSONObject returnedNotificationEventJSONObject = context.getReturnedRestExpressDataAsJsonObject();
        JSONArray notificationsJSONArray = (JSONArray) returnedNotificationEventJSONObject.get("items");
        for (Object notificationsObject : notificationsJSONArray)
        {
            NotificationEvents notificationEvents = mapper.readValue(((JSONObject)notificationsObject).toJSONString(),NotificationEvents.class);
            context.getReturnedItems().add(notificationEvents);
        }
    }

    @Override
    public List<Validator<T>> getValidators()
    {
        List<Validator<T>> allValidators = new ArrayList<>();
        allValidators.addAll(notificationsListExecutor.getValidators());
        allValidators.addAll(this.validators);
        return allValidators;
    }

}
