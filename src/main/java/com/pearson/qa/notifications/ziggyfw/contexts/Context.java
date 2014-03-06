package com.pearson.qa.notifications.ziggyfw.contexts;

import com.pearson.qa.notifications.ziggyfw.common.contexts.NotificationsRestExpressContext;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class Context extends NotificationsRestExpressContext
{
    public Context() throws Exception
    {
        super();
        setConfigNamespace(Context.class.getCanonicalName());
        super.setAddress();
    }
}
