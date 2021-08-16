package org.openreading.readingisgood.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Muhammet Sakarya
 * created at 8/14/2021
 */
public class Base implements Serializable {

    public Base() {
        setInserted(Calendar.getInstance().getTime());
    }

    @Id
    private String id;
    @CreatedDate
    private java.util.Date inserted;
    @LastModifiedDate
    private java.util.Date lastUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.util.Date getInserted() {
        return inserted;
    }

    public java.util.Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(java.util.Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    private void setInserted(Date inserted) {
        this.inserted = inserted;
    }
}
