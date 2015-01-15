package com.pearson.qa.notifications.ziggyfw.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.executors.NotificationsListExecutor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadListContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import com.pearson.qa.notifications.ziggyfw.validators.NotificationsUserEventsReadListValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class NotificationsUserEventsReadListExecutor<T extends NotificationsUserEventsReadListContext> implements Executor<T>
{
    private final NotificationsListExecutor<T> notificationsListExecutor;
    private final List<Validator<T>> validators;
    private final ObjectMapper mapper;

    @Inject
    public NotificationsUserEventsReadListExecutor(NotificationsListExecutor<T> notificationsListExecutor, ObjectMapper mapper)
    {
        this.notificationsListExecutor = notificationsListExecutor;
        this.validators = new ArrayList<>();
        this.validators.add(new NotificationsUserEventsReadListValidator<T>());
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
            context.setRequestPath("/userEvents");
        }

        if(context.getRecipientId() != null)
        {
            context.getRequestQuery().add((String.format("recipientId=%s", context.getRecipientId())));
        }

        context.getRequestHeaders().add(new BasicHeader("Accept", "*/*"));

        notificationsListExecutor.execute(context);

        if(context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_OK)
        {
            return;
        }

        JSONObject returnedNotificationUserEventJSONObject = context.getReturnedRestExpressDataAsJsonObject();
        JSONArray notificationsUserEventsJSONArray = (JSONArray) returnedNotificationUserEventJSONObject.get("items");
        for (Object notificationsUserEventsObject : notificationsUserEventsJSONArray)
        {
            NotificationUserEventsCollection notificationUserEventsCollection = mapper.readValue(((JSONObject)notificationsUserEventsObject).toJSONString(),NotificationUserEventsCollection.class);
            context.getReturnedItems().add(notificationUserEventsCollection);
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
