package com.pearson.qa.notifications.ziggyfw.datastructures;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import com.pearson.qa.common.ziggyfw.datastructures.IValidatable;
import com.pearson.qa.common.ziggyfw.exceptions.ValidationException;

/**
 * Created with IntelliJ IDEA.
 * User: UPATIVI
 * Date: 2/25/14
 * Time: 4:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Author implements IValidatable
{

	private String firstName;
	private String lastName;
	private int id;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    @Override
    public boolean isEmpty()
    {
        if(!StringUtils.isBlank(getFirstName()) || !StringUtils.isBlank(getLastName()) || !StringUtils.isBlank(String.valueOf(getId())))
            return false;
        else
            return true;
    }

    @Override
    public void validateEquals(IValidatable obj, boolean validateFieldsThatAreNullOnThis) throws ValidationException
    {
        Author author = (Author)obj;
        EqualsBuilder eb = new EqualsBuilder();

        if(validateFieldsThatAreNullOnThis == true || getFirstName()!= null)
        {
            eb.append(getFirstName(),author.getFirstName());
            if(!eb.isEquals())
                throw new ValidationException("First Name : ",getFirstName(),author.getFirstName());
        }

        if(validateFieldsThatAreNullOnThis == true || getLastName()!= null)
        {
            eb.append(getLastName(),author.getLastName());
            if(!eb.isEquals())
                throw new ValidationException("Last Name : ",getLastName(),author.getLastName());
        }

        if(validateFieldsThatAreNullOnThis == true || getId()!= null )
        {
            eb.append(getId(),author.getId());
            if(!eb.isEquals())
                throw new ValidationException("Id : ",String.valueOf(getId()),String.valueOf(author.getId()));
        }
    }

    @Override
    public void validateState() throws ValidationException
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
