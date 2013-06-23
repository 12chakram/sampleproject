package com.eng.gp.project.util.date;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Collection;

/**
 * A subclass that allows you to tread lightly over system resources by excerpting large Strings (and StringBuilder's)
 * and summarizing large collections.
 *
 * Note that summarization takes place at append-time, not at toString-time.
 *
 * @author dhorlick
 */
public class ResourceConservingToStringBuilder extends ToStringBuilder
{
    private Integer maxStringLength = 555;
    private Integer maxCollectionSize = 5;

    public ResourceConservingToStringBuilder(final Object object)
    {
        super(object);
    }

    public Integer getMaxStringLength()
    {
        return maxStringLength;
    }

    public void setMaxStringLength(final Integer maxStringLength)
    {
        this.maxStringLength = maxStringLength;
    }

    public Integer getMaxCollectionSize()
    {
        return maxCollectionSize;
    }

    public void setMaxCollectionSize(final Integer maxCollectionSize)
    {
        this.maxCollectionSize = maxCollectionSize;
    }

    @Override
    public ToStringBuilder append(final String fieldName, final Object value)
    {
        if (value!=null)
        {
            if (value instanceof String)
            {
                final String strung = (String) value;
                if (strung.length() > maxStringLength)
                {
                    final StringBuilder summarized = summarize(strung);
                    return super.append(fieldName, summarized.toString());
                }
            }
            else if (value instanceof StringBuilder)
            {
                final StringBuilder stringBuilder = (StringBuilder) value;
                if (stringBuilder.length() > maxStringLength)
                {
                    final StringBuilder summarized = summarize(stringBuilder.toString());
                    return super.append(fieldName, summarized);
                }
            }
            else if (value instanceof Collection)
            {
                final Collection collected = (Collection) value;
                if (collected.size()>maxCollectionSize)
                {
                    return append(fieldName+".size", collected.size());
                }
            }
        }

        return super.append(fieldName, value);
    }

    StringBuilder summarize(final String strung)
    {
        final StringBuilder stringBuilder = new StringBuilder();
        final int fragmentLength = (maxStringLength / 2) - 2;
        stringBuilder.append(strung.substring(0, fragmentLength));
        stringBuilder.append("...");
        stringBuilder.append(strung.substring(strung.length()-fragmentLength, strung.length()));
        return stringBuilder;
    }
}
