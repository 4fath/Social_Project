package com.fatihsenturk.socialapp.Model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by TOSHIBA on 15.3.2016. Mart
 * Dont worry !
 */
@ParseClassName("StuffModel")
public class StuffModel extends ParseObject {

//    private String name;
//    private List<String> tags;                 // alakali etiketler
//    private String description;                     // ilanin aciklamasi
//    private ParseUser owner;                        // ilani koyan kisi
//    private Date startDate;                         // ilanin koyuldugu tarih
//    private Boolean allowStatus;                    // allowed or not by admin
//    private Boolean currentState;                   // kimse tarafindan alinmis mi alinmamis mi ?
//    private List<ParseFile> stufPhotos;        // varsa fotograflari
//    private List<ParseUser> requestQueue;      // bu urune istek yollayan kisiler
//
//    public StuffModel(String name, ArrayList<String> tags, String description, ParseUser owner,
//                      Date startDate, Boolean allowStatus, Boolean currentState, ArrayList<ParseFile> stufPhotos) {
//        super();
//
//        this.name = name;
//        this.tags = tags;
//        this.description = description;
//        this.owner = owner;
//        this.startDate = startDate;
//        this.allowStatus = allowStatus;
//        this.currentState = currentState;
//        this.stufPhotos = stufPhotos;
//    }

    public StuffModel(){
    }

    public List<ParseUser> getRequestQueue() {
        return getList("requestQueue");
    }

    public void setRequestQueue(List<ParseUser> requestQueue) {
        put("requestQueue",requestQueue );
    }

    @Override
    public String toString() {
        return "StuffModel{" +
                "name='" + getName() + '\'' +
                ", tags=" + getTags() +
                ", description='" + getDescription() + '\'' +
                ", owner=" + getOwner() +
                ", allowStatus=" + getAllowStatus() +
                ", currentState=" + getCurrentState() +
                ", stufPhotos=" + getStufPhotos() +
                '}';
    }

    public String getName() {
        return getString("name");
    }

    public void setName(String name) {
       put("name", name);
    }

    public List<String> getTags() {
        return getList("tags");
    }

    public void setTags(List<String> tags) {
        put("tags", tags);

    }

    public String getDescription() {
        return getString("description");
    }

    public void setDescription(String description) {
        put("description", description);
    }

    public ParseUser getOwner() {
        return getParseUser("owner");
    }

    public void setOwner(ParseUser owner) {
        put("owner", owner);
    }

    public Date getStartDate() {
        return this.getCreatedAt();
    }
//
//    public void setStartDate(Date startDate) {
//        put("update");
//    }

    public Boolean getAllowStatus() {
        return getBoolean("allowStatus");
    }

    public void setAllowStatus(Boolean allowStatus) {
        put("allowStatus", allowStatus);
    }

    public Boolean getCurrentState() {
        return getBoolean("currentState");
    }

    public void setCurrentState(Boolean currentState) {
        put("currentState", currentState);
    }

    public List<ParseFile> getStufPhotos() {
        return getList("stufPhotos");
    }

    public void setStufPhotos(List<ParseFile> stufPhotos) {
        put("stufPhotos", stufPhotos);
    }
}
