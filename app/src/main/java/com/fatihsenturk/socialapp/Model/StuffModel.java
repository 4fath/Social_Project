package com.fatihsenturk.socialapp.Model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
@ParseClassName("StuffModel")
public class StuffModel extends ParseObject {

    private String name;
    private ArrayList<String> tags;                 // alakali etiketler
    private String description;                     // ilanin aciklamasi
    private ParseUser owner;                        // ilani koyan kisi
    private Date startDate;                         // ilanin koyuldugu tarih
    private Boolean allowStatus;                    // allowed or not by admin
    private Boolean currentState;                   // kimse tarafindan alinmis mi alinmamis mi ?
    private ArrayList<ParseFile> stufPhotos;        // varsa fotograflari
    private ArrayList<ParseUser> requestQueue;      // bu urune istek yollayan kisiler

    public StuffModel(String name, ArrayList<String> tags, String description, ParseUser owner,
                      Date startDate, Boolean allowStatus, Boolean currentState, ArrayList<ParseFile> stufPhotos) {
        super();

        this.name = name;
        this.tags = tags;
        this.description = description;
        this.owner = owner;
        this.startDate = startDate;
        this.allowStatus = allowStatus;
        this.currentState = currentState;
        this.stufPhotos = stufPhotos;
    }

    public StuffModel(){

    }

    public ArrayList<ParseUser> getRequestQueue() {
        return requestQueue;
    }

    public void setRequestQueue(ArrayList<ParseUser> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public String toString() {
        return "StuffModel{" +
                "name='" + name + '\'' +
                ", tags=" + tags +
                ", description='" + description + '\'' +
                ", owner=" + owner +
                ", startDate=" + startDate +
                ", allowStatus=" + allowStatus +
                ", currentState=" + currentState +
                ", stufPhotos=" + stufPhotos +
                '}';
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
       put("name", name);
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParseUser getOwner() {
        return owner;
    }

    public void setOwner(ParseUser owner) {
        this.owner = owner;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Boolean getAllowStatus() {
        return allowStatus;
    }

    public void setAllowStatus(Boolean allowStatus) {
        this.allowStatus = allowStatus;
    }

    public Boolean getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Boolean currentState) {
        this.currentState = currentState;
    }

    public ArrayList<ParseFile> getStufPhotos() {
        return stufPhotos;
    }

    public void setStufPhotos(ArrayList<ParseFile> stufPhotos) {
        this.stufPhotos = stufPhotos;
    }
}
