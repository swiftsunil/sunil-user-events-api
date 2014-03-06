package com.pearson.qa.notifications.ziggyfw.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.executors.NotificationsRestExpressExecutor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsReadContext;
import com.pearson.qa.notifications.ziggyfw.datastructures.NotificationUserEventsCollection;
import com.pearson.qa.notifications.ziggyfw.validators.NotificationsUserEventsReadValidator;

import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class NotificationsUserEventsReadExecutor<T extends NotificationsUserEventsReadContext> implements Executor<T>
{
    private NotificationsRestExpressExecutor<T> notificationsRestExpressExecutor;
    private List<Validator<T>> validators;
    private ObjectMapper mapper;

    @Inject
    public NotificationsUserEventsReadExecutor(NotificationsRestExpressExecutor<T> notificationsRestExpressExecutor, ObjectMapper mapper)
    {
        this.notificationsRestExpressExecutor = notificationsRestExpressExecutor;
        this.mapper = mapper;

        this.validators = new ArrayList<>();
        this.validators.add( new NotificationsUserEventsReadValidator<T>());
    }

    @Override
    public List<Validator<T>> getValidators()
    {
        List<Validator<T>> allValidators = new ArrayList<>();
        allValidators.addAll(notificationsRestExpressExecutor.getValidators());
        allValidators.addAll(this.validators);
        return allValidators;
    }

    @Override
    public boolean canExecute(T context)
    {
        return notificationsRestExpressExecutor.canExecute(context);
    }

    @Override
    public void execute(T context) throws Exception
    {
        if(StringUtils.isBlank(context.getRequestPath()))
        {
            context.setRequestPath(String.format("/userEvents/"+context.getUserEventId()));
        }

        context.getRequestHeaders().add(new BasicHeader("Accept", "*/*"));

        notificationsRestExpressExecutor.execute(context);

        if (context.getReturnedHttpResponseCode() != HttpURLConnection.HTTP_OK)
        {
            return;
        }

        JSONObject returnedNotificationUserEventsJSONObject = context.getReturnedRestExpressDataAsJsonObject();
        context.setReturnedNotificationsUserEvent(mapper.readValue(returnedNotificationUserEventsJSONObject.toJSONString(), NotificationUserEventsCollection.class));
    }
}
