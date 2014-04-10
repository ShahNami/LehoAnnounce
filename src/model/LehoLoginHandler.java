package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import view.LoginFrame;
/**
 *
 * @author Nami
 */
public class LehoLoginHandler {
    private boolean isLoggedIn;
    private static LehoLoginHandler _instance = null;
    private LoginFrame loginFrame;
    private Config config;
    
    public boolean isLoggedIn(){
        return isLoggedIn;
    }
    public static LehoLoginHandler getInstance() throws IOException, FileNotFoundException{
        if(_instance == null)
            _instance = new LehoLoginHandler();
        return _instance;
    }
    
    public void dispose(){
        loginFrame.dispose();
    }
    
    private LehoLoginHandler() throws IOException, FileNotFoundException{
        loginFrame = new LoginFrame();
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
        loginFrame.pack();
        config = Config.getInstance();
        //login(loginFrame.getUsername(), loginFrame.getPassword());
    }
    
    
    /**
     * author: Nathan Desmet & Niels Gunst
     * @param username
     * @param password
     * @throws java.lang.InterruptedException
     */
    public void login(String username, String password) throws InterruptedException
    {
        loginFrame.setInfo("Please try again.");
        try
        {
            Connection.Response res = Jsoup.connect("https://leho.howest.be/main/ssl/index.php").data(new String[] { "login", username, "password", password, "submitAuth", "OK", "_qf__formLogin", "" }).method(Connection.Method.POST).execute();
            if (res.parse().select("#login_fail").size() > 0) {
                this.isLoggedIn = false;
                loginFrame.setInfo("Incorrect login.");
            } else {
                this.isLoggedIn = true;
                loginFrame.setInfo("Correct login.");
                config.setCookies(res.cookies());
                loginFrame.dispose();
                LehoAnnounceHandler.getInstance(config);
            }
        } catch (IOException e) {
            this.isLoggedIn = false;
        }
    }
}
