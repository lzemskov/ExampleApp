package com.example.myfirstapp.repositories;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

/**
 * This class represents a basic representation of a Note with
 * some common set of fields used for serialization.
 *
 * @since 8/28/2018
 * @author lzemskov
 */

@Entity
public class  Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "created_datetime")
    private String createdDateTime;

    @ColumnInfo(name = "last_modified_datetime")
    private String lastModifiedDateTime;

    @ColumnInfo(name = "details")
    private String details;

    @ColumnInfo(name = "subject")
    private String subject;

    public int getUid() {
        return this.uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getCreatedDateTime() {
        return this.createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime){
        this.createdDateTime = createdDateTime;
    }

    public String getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String toStringJson() {
        StringBuilder note = new StringBuilder("{");
        note.append("\"uid\": ");
        note.append(this.getUid());
        note.append(",");
        note.append("\"subject\": \"");
        note.append(this.getSubject());
        note.append("\", ");
        note.append("\"details\" : \"");
        note.append(this.getDetails());
        note.append("\"");
        note.append("}");
        return note.toString();
    }
}
