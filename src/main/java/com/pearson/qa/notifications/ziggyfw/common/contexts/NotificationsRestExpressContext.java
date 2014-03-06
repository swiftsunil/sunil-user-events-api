package com.pearson.qa.notifications.ziggyfw.common.contexts;

import com.pearson.qa.common.ziggyfw.http.rest.contexts.RestExpressContext;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 3:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsRestExpressContext extends RestExpressContext
{
    private String authorizationToken;

    public NotificationsRestExpressContext() throws Exception
    {
        super();
        setRequestContentType(CONTENT_TYPE_JSON);
        setExpectedResponseContentType(CONTENT_TYPE_JSON);
    }

    public NotificationsRestExpressContext(NotificationsRestExpressContext original, boolean copyActualProperties) throws Exception
    {
        super(original, copyActualProperties);
        setAuthorizationToken(original.getAuthorizationToken());
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
