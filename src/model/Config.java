package model;

import java.util.Map;

public class Config
{
    private Map<String, String> announcements;
    private Map<String, String> cookies;
    private Map<String, String> subjects;
    private int lastVisit;
            
    private static Config _instance;
    
    public static Config getInstance(){
        if(_instance == null)
            return new Config();
        return _instance;
    }
    
    private Config(){}
    
    public Map<String, String> getCookies()
    {
        return this.cookies;
    }
    public void setCookies(Map<String, String> cookies)
    {
        this.cookies = cookies;
    }
    public void setSubjects(Map<String, String> subj){
        this.subjects= subj;
    }
    public void setAnnouncements(Map<String, String> ann){
        this.announcements = ann;
    }
    
    public Map<String, String> getSubjects(){
        return this.subjects;
    }
    
    public Map<String, String> getAnnouncements(){
        return this.announcements;
    }
    
    public int getVisit(){
        return this.lastVisit;
    }
    
    public void setVisit(int dt){
        this.lastVisit = dt;
    }
}
