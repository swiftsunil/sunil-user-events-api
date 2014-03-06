package com.pearson.qa.notifications.ziggyfw.common.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.http.rest.executors.RestExpressExecutor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.contexts.NotificationsRestExpressContext;
import org.apache.http.message.BasicHeader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 4:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsRestExpressExecutor<T extends NotificationsRestExpressContext> implements Executor<T>
{
    private RestExpressExecutor<T> restExpressExecutor;

    @Inject
    public void setRestExpressExecutor(RestExpressExecutor<T> restExpressExecutor)
    {
        this.restExpressExecutor = restExpressExecutor;
    }

    @Override
    public boolean canExecute(T context)
    {
        return restExpressExecutor.canExecute(context);
    }

    @Override
    public void execute(T context) throws Exception
    {
        if (context.getAuthorizationToken() != null)
        {
            context.getRequestHeaders().add(new BasicHeader("X-Authorization", context.getAuthorizationToken()));
        }

        restExpressExecutor.execute(context);
    }

    @Override
    public List<Validator<T>> getValidators()
    {
        return restExpressExecutor.getValidators();
    }
}
