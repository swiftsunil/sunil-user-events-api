package com.pearson.qa.notifications.ziggyfw.common.executors;

import com.google.inject.Inject;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.http.rest.executors.RestExpressListExecutor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.common.contexts.NotificationsListContext;
import org.apache.http.message.BasicHeader;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 4:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsListExecutor<T extends NotificationsListContext<?>> implements Executor<T>
{
    private final RestExpressListExecutor<T> restExpressListExecutor;

    @Inject
    public NotificationsListExecutor(RestExpressListExecutor<T> restExpressListExecutor)
    {
        this.restExpressListExecutor = restExpressListExecutor;
    }

    @Override
    public boolean canExecute(T context)
    {
        return restExpressListExecutor.canExecute(context);
    }

    @Override
    public void execute(T context) throws Exception
    {
        if (context.getAuthorizationToken() != null)
        {
            context.getRequestHeaders().add(new BasicHeader("X-Authorization", context.getAuthorizationToken()));
        }
        restExpressListExecutor.execute(context);
    }

    @Override
    public List<Validator<T>> getValidators()
    {
        return restExpressListExecutor.getValidators();
    }

}
