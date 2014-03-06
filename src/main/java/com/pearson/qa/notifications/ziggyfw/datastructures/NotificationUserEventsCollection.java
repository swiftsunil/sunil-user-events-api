package com.pearson.qa.notifications.ziggyfw.datastructures;

import com.pearson.qa.common.ziggyfw.datastructures.IValidatable;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.json.simple.JSONObject;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 3/3/14
 * Time: 11:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationUserEventsCollection implements IValidatable
{
    private String appType;
    private String productType;
    private String eventType;
    public JSONObject eventModel;
    private Date createdAt;
    private Date updatedAt;
    private String id;
    private Links[] links;
    private String authorId;
    private String recipientId;

    public NotificationUserEventsCollection()
    {
    }

    public NotificationUserEventsCollection(NotificationUserEventsCollection original)
    {
        setAppType(original.getAppType());
        setProductType(original.getProductType());
        setEventType(original.getEventType());
        setEventModel(original.getEventModel());
        setCreatedAt(original.getCreatedAt());
        setUpdatedAt(original.getUpdatedAt());
        setId(original.getId());
        setLinks(original.getLinks());
        setAuthorId(original.getAuthorId());
    }


    public String getAppType()
    {
        return appType;
    }

    public void setAppType(String appType)
    {
        this.appType = appType;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType(String productType)
    {
        this.productType = productType;
    }

    public String getEventType()
    {
        return eventType;
    }

    public void setEventType(String eventType)
    {
        this.eventType = eventType;
    }

    public JSONObject getEventModel()
    {
        return eventModel;
    }

    public void setEventModel(JSONObject eventModel)
    {
        this.eventModel = eventModel;
    }

    public Date getCreatedAt()
    {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt)
    {
        this.updatedAt = updatedAt;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Links[] getLinks()
    {
        return links;
    }

    public void setLinks(Links[] links)
    {
        this.links = links;
    }

    public String getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public String getRecipientId()
    {
        return recipientId;
    }

    public void setRecipientId(String recipientId)
    {
        this.recipientId = recipientId;
    }


    @Override
    public boolean isEmpty()
    {
        if(!StringUtils.isBlank(getAppType()) || !StringUtils.isBlank(getEventType())
                || !StringUtils.isBlank(String.valueOf(getId())) || !StringUtils.isBlank(getProductType())
                || !StringUtils.isBlank(String.valueOf(getCreatedAt()))|| !StringUtils.isBlank(String.valueOf(getUpdatedAt()))
                || !StringUtils.isBlank(String.valueOf(getEventModel()))|| !StringUtils.isBlank(String.valueOf(getAuthorId()))
                || !StringUtils.isBlank(String.valueOf(getRecipientId())))
            return false;
        else
            return true;
    }

    @Override
    public void validateEquals(IValidatable obj, boolean validateFieldsThatAreNullOnThis) throws ValidationException
    {
        NotificationUserEventsCollection notificationUserEventsCollection = (NotificationUserEventsCollection)obj;
        EqualsBuilder eb = new EqualsBuilder();

        if(validateFieldsThatAreNullOnThis == true || getId()!= null)
        {
            eb.append(getId(), notificationUserEventsCollection.getId());
            if(!eb.isEquals())
                throw new ValidationException("Id : ",getId(), notificationUserEventsCollection.getId());
        }

        if(validateFieldsThatAreNullOnThis == true || getUpdatedAt()!= null)
        {
            eb.append(getUpdatedAt(), notificationUserEventsCollection.getUpdatedAt());
            if(!eb.isEquals())
                throw new ValidationException("Updated At : ",String.valueOf(getUpdatedAt()),String.valueOf(notificationUserEventsCollection.getUpdatedAt()));
        }

        if(validateFieldsThatAreNullOnThis == true || getId()!= null )
        {
            eb.append(getCreatedAt(), notificationUserEventsCollection.getCreatedAt());
            if(!eb.isEquals())
                throw new ValidationException("Created At : ",String.valueOf(getCreatedAt()),String.valueOf(notificationUserEventsCollection.getCreatedAt()));
        }

        if(validateFieldsThatAreNullOnThis == true || getEventType()!= null)
        {
            eb.append(getEventType(), notificationUserEventsCollection.getEventType());
            if(!eb.isEquals())
                throw new ValidationException("Event type : ",getEventType(), notificationUserEventsCollection.getEventType());
        }

        if(validateFieldsThatAreNullOnThis == true || getAppType()!= null)
        {
            eb.append(getAppType(), notificationUserEventsCollection.getAppType());
            if(!eb.isEquals())
                throw new ValidationException("App type : ",getAppType(), notificationUserEventsCollection.getAppType());
        }

        if(validateFieldsThatAreNullOnThis == true || getProductType()!= null)
        {
            eb.append(getProductType(), notificationUserEventsCollection.getProductType());
            if(!eb.isEquals())
                throw new ValidationException("Product type : ",getProductType(), notificationUserEventsCollection.getProductType());
        }

        if(validateFieldsThatAreNullOnThis == true || getProductType()!= null)
        {
            eb.append(getAuthorId(), notificationUserEventsCollection.getAuthorId());
            if(!eb.isEquals())
                throw new ValidationException("Author Id : ",getAuthorId(), notificationUserEventsCollection.getAuthorId());
        }

        if(validateFieldsThatAreNullOnThis == true || getProductType()!= null)
        {
            eb.append(getRecipientId(), notificationUserEventsCollection.getRecipientId());
            if(!eb.isEquals())
                throw new ValidationException("Recipient Id : ",getRecipientId(), notificationUserEventsCollection.getRecipientId());
        }

    }

    @Override
    public void validateState() throws ValidationException
    {
        if(getAppType() == null)
        {
            throw new ValidationException("The appType is missing.");
        }
        else if(getProductType() == null )
        {
            throw new ValidationException("The productType is missing.");
        }
        else if(getEventType() == null)
        {
            throw new ValidationException("The eventType is missing.");
        }
        else if(getAuthorId() == null)
        {
            throw new ValidationException("The authorId is missing.");
        }
        else if(getRecipientId() == null)
        {
            throw new ValidationException("The recipientId is missing.");
        }


        // Validate that creation date is not in the future
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        if (getUpdatedAt() != null && getUpdatedAt().before(getCreatedAt()))
        {
            throw new ValidationException("UpdatedAt", "UpdatedAt is after CreatedAt",
                    String.format("UpdatedAt: %s, CreatedAt: %s", getUpdatedAt(), getCreatedAt()));
        }
    }

}