package ru.courses.mantis.appmanager;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.junit.JUnit4TestRunner;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final Properties properties;
    private WebDriver wd;

    private JavascriptExecutor js;
    private String browser;
    private RegistrationHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private AdministrationHelper administrationHelper;
    private UserHelper userHelper;
    private DbHelper dbHelper;
    private SoapHelper soapHelper;

    public FtpHelper ftp() {
        if(ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
            if (wd !=null) {
                wd.quit();
            }
    }

    public HttpSession newSession() {
          return new HttpSession(this);
    }

    public String getProperty(String key) {
            return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() {
            if (wd == null) {
                if (browser.equals(BrowserType.CHROME)) {
                    wd = new ChromeDriver();
                } else if (browser.equals(BrowserType.FIREFOX)){
                    wd = new FirefoxDriver();
                } else if (browser.equals(BrowserType.IE)){
                    wd = new InternetExplorerDriver();
                }
                wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                js = (JavascriptExecutor) wd;
                wd.get(properties.getProperty("web.baseUrl"));
            }
            return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }

    public AdministrationHelper admin() {
        if(administrationHelper == null) {
            administrationHelper = new AdministrationHelper(this);
        }
        return administrationHelper;
    }

    public UserHelper user() {
        if(userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public DbHelper db() {
        if(dbHelper == null) {
            dbHelper = new DbHelper(this);
        }
        return dbHelper;
    }

    public SoapHelper soap() {
        if(soapHelper == null) {
            soapHelper = new SoapHelper(this);
        }
        return soapHelper;
    }
}
