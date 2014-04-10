package model;

import java.awt.Cursor;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import view.AnnounceFrame;

/**
 *
 * @author Nami
 */
public class LehoAnnounceHandler {
    AnnounceFrame announceFrame;
    private static LehoAnnounceHandler _instance = null;
    private Map<String, String> subjects;
    private Map<String, String> announcements;
    Config config;
    String path = null;
    
    public static LehoAnnounceHandler getInstance(Config config) throws IOException, InterruptedException{
        if(_instance == null)
            _instance = new LehoAnnounceHandler(config);
        return _instance;
    }
    
    private LehoAnnounceHandler(Config config) throws IOException, InterruptedException {
        announceFrame = new AnnounceFrame(config);
        announceFrame.setVisible(true);
        announceFrame.pack();
        this.config = config;
        subjects = new HashMap<>();
        announcements = new HashMap<>();
        path = "http://leho.howest.be/main/announcements/announcements.php?cidReq=";
        try {            
            announceFrame.getRootPane().setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            readFromConfig("http://leho.howest.be//user_courses.php");
            sleep(1500);
            getSubjects("http://leho.howest.be//user_courses.php");
        } finally {
            announceFrame.getRootPane().setCursor(Cursor.getDefaultCursor());
        }
    }
    
    private void getSubjects(String path) throws IOException, InterruptedException{
        subjects = getIDS(path);
        this.config.setSubjects(subjects);
        Object[] sub = new Object[subjects.size()];
        int count = 0;
        for (Map.Entry<String, String> entry : subjects.entrySet()) {
            sub[count] = entry.getValue();
            count++;
        }
        announceFrame.setSubjects(sub);
    }
    
    public void getAnnouncementDates(String code) throws IOException {
        Document doc = setConnection(path+code);
        Element announceTable = doc.select("#agenda_list").first();
        List<String> dates = new ArrayList<>();
        for(int i=0;i<announceTable.getElementsByClass("announcements_datum").size();i++){
            dates.add(announceTable.getElementsByClass("announcements_datum").get(i).text());
        }
        announceFrame.setAnnouncementDates(dates.toArray());
    }
    
    public void getAnnouncement(String code, int nr) throws IOException{
        Document doc = setConnection(path+code);
        String announcement = doc.select("#agenda_list").first().getElementsByClass("text").get(nr).text();
        announceFrame.setAnnouncement(announcement);
    }
    
    public void getAnnouncementData(String code, int nr) throws IOException{
        Document doc = setConnection(path+code);
        String announcement = doc.select("#agenda_list").first().getElementsByClass("data").get(nr).text().substring(0, doc.select("#agenda_list").first().getElementsByClass("data").get(nr).text().toLowerCase().indexOf("zichtbaar voor"));
        announceFrame.setAnnouncementData(announcement);
    }
    
    public Document setConnection(String path) throws IOException{
        Connection con = Jsoup.connect(path);
        con.cookies(config.getCookies());
        return con.get();
    }
    
    private void writeToConfig(String path) throws IOException{
        Properties prop = new Properties();
        OutputStream output = new FileOutputStream("subjects.properties");
        Map<String, String> ids = getIDS(path);
        for (Map.Entry<String, String> entry : ids.entrySet()) {
            Document doc = setConnection(this.path+entry.getKey());
            Element announceTable = doc.select("#agenda_list").first();
            prop.setProperty(entry.getKey(), Integer.toString(announceTable.getElementsByClass("announcements_datum").size()));
        }
        prop.store(output, null);
        output.close();
    }
    private void createIfNotExist(String path) throws IOException{
        File file = new File(path);
        file.createNewFile();
    }
    private void readFromConfig(String path) throws IOException{
        Properties prop = new Properties();
        createIfNotExist("subjects.properties");
        InputStream input = new FileInputStream("subjects.properties");
        Map<String, String> ids = getIDS(path);
        prop.load(input);
        for (Map.Entry<String, String> entry : ids.entrySet()) {
            if(prop.getProperty(entry.getKey()) == null){
                writeToConfig(path);
                break;
            }
        }
        input.close();
    }
    
    public Map<String, String> getIDS(String path) throws IOException{
        Document doc = setConnection(path);
        HashMap<String, String> ids = new HashMap<>();
        Elements links = doc.select("a[href~=leho.howest.be/courses/CUR]");
        for(int i=0;i<links.size();i++){
            int from = links.get(i).toString().lastIndexOf("CUR");
            int to = links.get(i).toString().lastIndexOf("\">") - 1;
            //Java SSD redirection fix
            if(links.get(i).toString().substring(from, to).equalsIgnoreCase("CUR05600791010337001986")){
                ids.put("CUR05600791010336001986", links.get(i).text());
            } else {
                ids.put(links.get(i).toString().substring(from, to), links.get(i).text());
            }
        }
        
        return ids;
    }
}
