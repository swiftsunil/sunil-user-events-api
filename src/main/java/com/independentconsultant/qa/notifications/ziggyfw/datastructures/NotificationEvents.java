package com.pearson.qa.notifications.ziggyfw.datastructures;

import com.pearson.qa.common.ziggyfw.datastructures.IValidatable;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.json.simple.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class NotificationEvents implements IValidatable
{
    private String appType;
    private String productType;
    private String eventType;
    private String authorId;
    List<String> recipientIds;
    public JSONObject eventModel;
    private Date createdAt;
    private Date updatedAt;
    private String id;
    private Links[] links;

    public NotificationEvents()
    {
    }

    public NotificationEvents(NotificationEvents original)
    {
        setAppType(original.getAppType());
        setProductType(original.getProductType());
        setEventType(original.getEventType());
        setAuthorId(original.getAuthorId());
        setRecipientIds(original.getRecipientIds());
        setEventModel(original.getEventModel());
        setCreatedAt(original.getCreatedAt());
        setUpdatedAt(original.getUpdatedAt());
        setId(original.getId());
        setLinks(original.getLinks());
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

    public String getAuthorId()
    {
        return authorId;
    }

    public void setAuthorId(String authorId)
    {
        this.authorId = authorId;
    }

    public List<String> getRecipientIds()
    {
        return recipientIds;
    }

    public void setRecipientIds(List<String> recipientIds)
    {
        this.recipientIds = recipientIds;
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

    @Override
    public boolean isEmpty()
    {
        if(!StringUtils.isBlank(getAppType()) || !StringUtils.isBlank(getEventType())
                || !StringUtils.isBlank(String.valueOf(getId())) || !StringUtils.isBlank(getProductType())
                || !StringUtils.isBlank(String.valueOf(getAuthorId())) || !StringUtils.isBlank(String.valueOf(getCreatedAt()))
                || !StringUtils.isBlank(String.valueOf(getUpdatedAt())) || !StringUtils.isBlank(String.valueOf(getRecipientIds()))
                || !StringUtils.isBlank(String.valueOf(getEventModel())))
            return false;
        else
            return true;
    }

    @Override
    public void validateEquals(IValidatable obj, boolean validateFieldsThatAreNullOnThis) throws ValidationException
    {
        NotificationEvents notificationEvents = (NotificationEvents)obj;
        EqualsBuilder eb = new EqualsBuilder();

        if(validateFieldsThatAreNullOnThis == true || getId()!= null)
        {
            eb.append(getId(), notificationEvents.getId());
            if(!eb.isEquals())
                throw new ValidationException("Id : ",getId(),notificationEvents.getId());
        }

        if(validateFieldsThatAreNullOnThis == true || getUpdatedAt()!= null)
        {
            eb.append(getUpdatedAt(),notificationEvents.getUpdatedAt());
            if(!eb.isEquals())
                throw new ValidationException("Updated At : ",String.valueOf(getUpdatedAt()),String.valueOf(notificationEvents.getUpdatedAt()));
        }

        if(validateFieldsThatAreNullOnThis == true || getId()!= null )
        {
            eb.append(getCreatedAt(),notificationEvents.getCreatedAt());
            if(!eb.isEquals())
                throw new ValidationException("Created At : ",String.valueOf(getCreatedAt()),String.valueOf(notificationEvents.getCreatedAt()));
        }

        if(validateFieldsThatAreNullOnThis == true || getId()!= null)
        {
            eb.append(getRecipientIds(),notificationEvents.getRecipientIds());
            if(!eb.isEquals())
                throw new ValidationException("RecipientIds : ",String.valueOf(getRecipientIds()),String.valueOf(notificationEvents.getRecipientIds()));
        }

        if(validateFieldsThatAreNullOnThis == true || getAuthorId()!= null)
        {
            eb.append(getAuthorId(),notificationEvents.getAuthorId());
            if(!eb.isEquals())
                throw new ValidationException("Author : ",String.valueOf(getAuthorId()),String.valueOf(notificationEvents.getAuthorId()));
        }

        if(validateFieldsThatAreNullOnThis == true || getEventType()!= null)
        {
            eb.append(getEventType(),notificationEvents.getEventType());
            if(!eb.isEquals())
                throw new ValidationException("Event type : ",getEventType(),notificationEvents.getEventType());
        }

        if(validateFieldsThatAreNullOnThis == true || getAppType()!= null)
        {
            eb.append(getAppType(),notificationEvents.getAppType());
            if(!eb.isEquals())
                throw new ValidationException("App type : ",getAppType(),notificationEvents.getAppType());
        }

        if(validateFieldsThatAreNullOnThis == true || getProductType()!= null)
        {
            eb.append(getProductType(),notificationEvents.getProductType());
            if(!eb.isEquals())
                throw new ValidationException("Product type : ",getProductType(),notificationEvents.getProductType());
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
        else if(getRecipientIds() == null)
        {
            throw new ValidationException("The recipients is missing.");
        }
        else if(getAuthorId() == null)
        {
            throw new ValidationException("The author is missing.");
        }
        else if(getRecipientIds() == null)
        {
            throw new ValidationException("The recipientIds are missing.");
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
