package com.pearson.qa.notifications.ziggyfw.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.executors.NotificationsRestExpressExecutor;
import com.pearson.qa.notifications.ziggyfw.contexts.NotificationsUserEventsUpdateContext;
import org.apache.commons.lang.StringUtils;
import org.apache.http.message.BasicHeader;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 12:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsUserEventsUpdateExecutor<T extends NotificationsUserEventsUpdateContext> implements Executor<T>
{
    private NotificationsRestExpressExecutor<T> notificationsRestExpressExecutor;
    private List<Validator<T>> validators;
    private ObjectMapper mapper = new ObjectMapper();

    @Inject
    public NotificationsUserEventsUpdateExecutor(NotificationsRestExpressExecutor<T> notificationsRestExpressExecutor, List<Validator<NotificationsUserEventsUpdateContext>> validators, ObjectMapper mapper)
    {
        this.notificationsRestExpressExecutor = notificationsRestExpressExecutor;
        this.mapper = mapper;
        this.validators = new ArrayList<>();
        for (Validator<NotificationsUserEventsUpdateContext> validator : validators)
        {
            this.validators.add((Validator<T>) validator);
        }
    }

    @Override
    public boolean canExecute(T context)
    {
        return notificationsRestExpressExecutor.canExecute(context);
    }

    @Override
    public void execute(T context) throws Exception
    {
        if (StringUtils.isBlank(context.getRequestPath()))
        {
            context.setRequestPath(String.format("/userEvents"));
        }

        if(context.getRequestContentType() != null)
        {
            context.getRequestHeaders().add(new BasicHeader("Content-Type",context.getRequestContentType()));
        }

        if (StringUtils.isBlank(context.getRequestBody()))
        {
            context.setRequestBody(mapper.writeValueAsString(context.getModifiedNotificationsUserEventCollection()));
        }

        context.getRequestHeaders().add(new BasicHeader("Accept", "*/*"));

        notificationsRestExpressExecutor.execute(context);
    }

    @Override
    public List<Validator<T>> getValidators()
    {
        List<Validator<T>> allValidators = new ArrayList<>();
        allValidators.addAll(notificationsRestExpressExecutor.getValidators());
        allValidators.addAll(this.validators);
        return allValidators;
    }

}
