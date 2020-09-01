package com.sendcloud.sdk.model;

/**
 * time:yyyy-MM-dd HH:mm:ss
 *
 * @author Administrator
 *
 */
public class MailCalendar {

    private String startTime;
    private String endTime;
    private String title;
    private String organizerName;
    private String organizerEmail;
    private String location;
    private String description;
    private String participatorNames;
    private String participatorEmails;
    private String uid;
    private boolean isCancel;
    private boolean isUpdate;

    public boolean validate() {
        return startTime != null && endTime != null && title != null && organizerName != null && organizerEmail != null
                && location != null && participatorNames != null;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getOrganizerEmail() {
        return organizerEmail;
    }

    public void setOrganizerEmail(String organizerEmail) {
        this.organizerEmail = organizerEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParticipatorNames() {
        return participatorNames;
    }

    public void setParticipatorNames(String participatorNames) {
        this.participatorNames = participatorNames;
    }

    public String getParticipatorEmails() {
        return participatorEmails;
    }

    public void setParticipatorEmails(String participatorEmails) {
        this.participatorEmails = participatorEmails;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }

    public boolean isUpdate() {
        return isUpdate;
    }

    public void setUpdate(boolean isUpdate) {
        this.isUpdate = isUpdate;
    }

}