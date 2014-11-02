package net.dean.jraw.models.attr;

import net.dean.jraw.models.JsonProperty;

import java.util.Date;

/**
 * Indicates that this object was created
 */
public interface Created extends JsonAttribute {
    /**
     * Gets the date this object was created in local time
     * @return Date created in local time
     */
    @JsonProperty
    public Date getCreated();

    /**
     * Gets the date this object was created in UTC
     * @return Date created in UTC
     */
    @JsonProperty
    public Date getCreatedUtc();
}
