package com.gc.dto;

/**
 * Created by maurice on 8/20/17.
 * DTo for groups DB table.
 */
public class GroupDto {
    private int groupid;
    private String name;
    private String displayName;

    /*
    * Default constructor
     */
    public GroupDto() {
    }

    /*
    * Paramiterized constructor
     */
    public GroupDto(int groupid, String name, String displayName) {
        this.groupid = groupid;
        this.name = name;
        this.displayName = displayName;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return "GroupDto{" +
                "groupid=" + groupid +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
