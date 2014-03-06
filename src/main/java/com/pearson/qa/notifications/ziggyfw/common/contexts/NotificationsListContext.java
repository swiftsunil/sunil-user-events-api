package com.pearson.qa.notifications.ziggyfw.common.contexts;

import com.pearson.qa.common.ziggyfw.http.rest.contexts.RestExpressListContext;
import com.pearson.qa.notifications.ziggyfw.contexts.Context;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 3:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsListContext<T> extends RestExpressListContext<T>
{
    private String authorizationToken;

    public NotificationsListContext()
    {
        super();
        setConfigNamespace(Context.class.getCanonicalName());

        setRequestContentType(CONTENT_TYPE_JSON);
        setExpectedResponseContentType(CONTENT_TYPE_JSON);
    }

    public String getAuthorizationToken()
    {
        return authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken)
    {
        this.authorizationToken = authorizationToken;
    }

}
