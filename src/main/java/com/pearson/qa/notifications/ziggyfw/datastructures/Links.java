package com.pearson.qa.notifications.ziggyfw.datastructures;

import com.pearson.qa.common.ziggyfw.datastructures.IValidatable;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/24/14
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class Links implements IValidatable
{

    private String href;
    private String rel;

    public String getHref()
    {
        return href;
    }

    public void setHref(String href)
    {
        this.href = href;
    }

    public String getRel()
    {
        return rel;
    }

    public void setRel(String rel)
    {
        this.rel = rel;
    }

    @Override
    public boolean isEmpty()
    {
        if(!StringUtils.isBlank(getHref()) || !StringUtils.isBlank(getRel()))
            return false;
        else
            return true;
    }

    @Override
    public void validateEquals(IValidatable obj, boolean validateFieldsThatAreNullOnThis) throws ValidationException
    {
        Links links = (Links)obj;
        EqualsBuilder eb = new EqualsBuilder();

        if(validateFieldsThatAreNullOnThis == true || getHref()!= null)
        {
            eb.append(getHref(),links.getHref());
            if(!eb.isEquals())
                throw new ValidationException("Links value : ",getHref(),links.getHref());
        }

        if(validateFieldsThatAreNullOnThis == true || getRel()!= null)
        {
            eb.append(getRel(),links.getRel());
            if(!eb.isEquals())
                throw new ValidationException("Links value : ",getRel(),links.getRel());
        }

    }

    @Override
    public void validateState() throws ValidationException
    {

    }

}
