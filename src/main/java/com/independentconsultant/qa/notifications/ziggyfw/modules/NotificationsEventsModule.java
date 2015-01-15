package com.pearson.qa.notifications.ziggyfw.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.pearson.qa.common.ziggyfw.executors.Executor;
import com.pearson.qa.common.ziggyfw.validators.Validator;
import com.pearson.qa.notifications.ziggyfw.contexts.*;
import com.pearson.qa.notifications.ziggyfw.contexts.factories.NotificationsContextFactory;
import com.pearson.qa.notifications.ziggyfw.executors.*;
import com.pearson.qa.notifications.ziggyfw.validators.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationsEventsModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        install(new FactoryModuleBuilder().build(NotificationsContextFactory.class));
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, false);
        bind(ObjectMapper.class).toInstance(mapper);
    }

    @Provides
    public List<Executor<NotificationsEventsPostContext>> getCreateNotificationsEventsPostExecutors(NotificationsEventsPostExecutor<NotificationsEventsPostContext> executor)
    {
        List<Executor<NotificationsEventsPostContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    //list of events
    @Provides
    public List<Executor<NotificationsEventsReadContext>> getCreateNotificationsEventsReadExecutors(NotificationsEventsReadExecutor<NotificationsEventsReadContext> executor)
    {
        List<Executor<NotificationsEventsReadContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    //single event
    @Provides
    public List<Executor<NotificationsEventReadContext>> getCreateNotificationsEventReadExecutors(NotificationsEventReadExecutor<NotificationsEventReadContext> executor)
    {
        List<Executor<NotificationsEventReadContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    @Provides
    public List<Executor<NotificationsUserEventsUpdateContext>> getCreateNotificationsUserEventsUpdateExecutors(NotificationsUserEventsUpdateExecutor<NotificationsUserEventsUpdateContext> executor)
    {
        List<Executor<NotificationsUserEventsUpdateContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    @Provides
    public List<Executor<NotificationsUserEventsPostContext>> getCreateNotificationsUserEventsPostExecutors(NotificationsUserEventsPostExecutor<NotificationsUserEventsPostContext> executor)
    {
        List<Executor<NotificationsUserEventsPostContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    //single event
    @Provides
    public List<Executor<NotificationsUserEventsReadContext>> getCreateNotificationsUserEventsReadExecutors(NotificationsUserEventsReadExecutor<NotificationsUserEventsReadContext> executor)
    {
        List<Executor<NotificationsUserEventsReadContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }

    //list of events
    @Provides
    public List<Executor<NotificationsUserEventsReadListContext>> getCreateNotificationsUserEventsReadListExecutors(NotificationsUserEventsReadListExecutor<NotificationsUserEventsReadListContext> executor)
    {
        List<Executor<NotificationsUserEventsReadListContext>> executors = new ArrayList<>();
        executors.add(executor);
        return executors;
    }







    @Provides
    public List<Validator<NotificationsEventsPostContext>> getCreateNotificationsEventsPostValidators(NotificationsEventsPostValidator validator)
    {
        List<Validator<NotificationsEventsPostContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    @Provides
    public List<Validator<NotificationsEventsReadContext>> getCreateNotificationsEventsReadValidators(NotificationsEventsReadValidator validator)
    {
        List<Validator<NotificationsEventsReadContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    @Provides
    public List<Validator<NotificationsEventReadContext>> getCreateNotificationsEventsReadValidators(NotificationsEventReadValidator validator)
    {
        List<Validator<NotificationsEventReadContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    @Provides
    public List<Validator<NotificationsUserEventsUpdateContext>> getCreateNotificationsUserEventsUpdateValidators(NotificationsUserEventsUpdateValidator validator)
    {
        List<Validator<NotificationsUserEventsUpdateContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    @Provides
    public List<Validator<NotificationsUserEventsPostContext>> getCreateNotificationsUserEventsPostValidators(NotificationsUserEventsPostValidator validator)
    {
        List<Validator<NotificationsUserEventsPostContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    //single event
    @Provides
    public List<Validator<NotificationsUserEventsReadContext>> getCreateNotificationsUserEventsReadValidators(NotificationsUserEventsReadValidator validator)
    {
        List<Validator<NotificationsUserEventsReadContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }

    ////list of events
    @Provides
    public List<Validator<NotificationsUserEventsReadListContext>> getCreateNotificationsUserEventsReadListValidators(NotificationsUserEventsReadListValidator validator)
    {
        List<Validator<NotificationsUserEventsReadListContext>> validators = new ArrayList<>();
        validators.add(validator);
        return validators;
    }


}
