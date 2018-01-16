package atg.test.selenium.core;

import static atg.test.selenium.core.Config.getProperty;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import atg.test.selenium.business.Browser;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

final class DriverManager {

	/**
	 * Creates a new webdriver instance.
	 *
	 * @return the webdriver instance
	 * @throws Exception the exception
	 */
	static WebDriver createDriver() throws Exception {
		WebDriver webdriver;

		int xstart = Integer.parseInt(getProperty("xstart", "100"));
		int ystart = Integer.parseInt(getProperty("ystart", "100"));
		int xres = Integer.parseInt(getProperty("xres", "1024"));
		int yres = Integer.parseInt(getProperty("xres", "768"));

		DesiredCapabilities capabilities;

		String wdUrl = getProperty("RemoteWebDriverURL");
		String appBrowser = getProperty("AppBrowser");

		if (appBrowser.equalsIgnoreCase("firefox")) {
			FirefoxProfile profile = createFFProfile();
			loadFFExtensions(profile);
			setPreferences(profile);
			capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability(FirefoxDriver.PROFILE, profile);
			//Added code as part of selenium upgrade to latest version
			capabilities.setCapability("marionette", false);
			if (wdUrl == null) {
				webdriver = new FirefoxDriver(capabilities);
			} else {
				webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
			}
		} 
		
		else if (appBrowser.equalsIgnoreCase("iexplore")) {
			if (wdUrl == null) {
				wdUrl = "http://localhost:" + getProperty("WebDriverServerPort");
			}
			capabilities = DesiredCapabilities.internetExplorer();
			//To avoid cache issue
			capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			// workaround for
			// http://code.google.com/p/selenium/issues/detail?id=4403
			capabilities.setCapability("nativeEvents", false);
			// end of workaround
			webdriver = new RemoteWebDriver(new URL(wdUrl), capabilities);
//		
		} 
		
		else if (appBrowser.equalsIgnoreCase("iphone")) {
			
			
			
			String PROXY = "www-proxy.us.oracle.com";
	        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
	        proxy.setHttpProxy(PROXY)
	           .setFtpProxy(PROXY)
	           .setSslProxy(PROXY);
	        proxy.setProxyType(ProxyType.MANUAL);
	        
			capabilities = new DesiredCapabilities();
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
			capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Safari");
			capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.2");
			capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
			capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
		    capabilities.setCapability("enablePerformanceLogging", true);
		    capabilities.setCapability("autoWebview", true);			
			 capabilities.setCapability(CapabilityType.PROXY, proxy);
			

			if (wdUrl == null)
				wdUrl = "http://localhost:4723/wd/hub";
			
			try {
		    	System.out.println("\n starting session in Simulator");
//		    	webdriver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities1);
  		    	webdriver = new IOSDriver(new URL(wdUrl), capabilities);
		    	String url =webdriver.getCurrentUrl();
		    	System.out.println("\n The current url is"+url);
//		    	webdriver.get("https://www.oracle.com/");
		    	System.out.println("\n Session successfully started");
		    }
		    catch (MalformedURLException e) {
		    	System.out.println("\n Error starting session in Emulator");
				System.out.println(e.getMessage());
			}
			
			webdriver = new IOSDriver(new URL(wdUrl), capabilities);
		} 
		
		else if (appBrowser.equalsIgnoreCase("nativeapp")) {
			capabilities = new DesiredCapabilities();
			//Do not set 'locationServicesAuthorized' capabilities - this is causing Store Locator Maps to stop rendering.
			//capabilities.setCapability("locationServicesAuthorized", true);
			capabilities.setCapability("bundleId", "com.oraclecorp.internal.ent2.ATGMobileCommerce");
		    capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, getProperty("OSVersion"));
		    capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, getProperty("Device"));
		    capabilities.setCapability(MobileCapabilityType.APP, getProperty("NativeAppPath"));
		    capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "60");
//			
			capabilities.setCapability("screenshotWaitTimeout", "20");
//			
			capabilities.setCapability("waitForAppScript", "if (target.frontMostApp().alert().name()=='â€œMobileStoreâ€� Would Like to Send You Notifications') {$.acceptAlert(); true;}"
					+ "if (target.frontMostApp().alert().name()=='Error Registering for Push Notifications'){$.acceptAlert(); true;} "
					+ "if (target.mainWindow().buttons()[0].name()=='No thanks'){$.acceptAlert(); true;} true;");
//			
				if (wdUrl == null)
					wdUrl = "http://localhost:4723/wd/hub";
				webdriver = new IOSDriver(new URL(wdUrl), capabilities);
		} 
		
		else if (appBrowser.equalsIgnoreCase("android"))
	{
//			
			
			DesiredCapabilities capabilities1 = DesiredCapabilities.android();
			
		    String PROXY = "www-proxy.us.oracle.com";
	        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
	        proxy.setHttpProxy(PROXY)
	           .setFtpProxy(PROXY)
	           .setSslProxy(PROXY);
	        proxy.setProxyType(ProxyType.MANUAL);
	        
//			
			 capabilities1.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
			    capabilities1.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.0");
			    capabilities1.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5556");
			    capabilities1.setCapability("autoWebviewTimeout", 20000);
			    capabilities1.setCapability("androidDeviceReadyTimeout", 60);
			    capabilities1.setCapability("browserName", "Chrome");
			    capabilities1.setCapability("automationName", "Appium");
			    capabilities1.setCapability("recreateChromeDriverSessions", true);
			    capabilities1.setCapability("enablePerformanceLogging", true);
//			    capabilities1.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2");
			    capabilities1.setCapability(CapabilityType.PROXY, proxy);
			    
			    capabilities1.setCapability("appPackage", "com.example.jbruckn.simplewebview");
			    capabilities1.setCapability("appActivity", "com.example.jbruckn.simplewebview.MainActivity");
//			    capabilities1.setCapability("autoWebview", "true");
//			    capabilities1.setCapability("WEBVIEW", "true");
			     
//			    capabilities1.setCapability("clearSystemFiles", true);
//			    capabilities1.setCapability("chromedriverExecutable", "C:\\ChromeDriver\\chromedriver.exe");
//			    webdriver.get("WEBVIEW");
			    Thread.sleep(2500);
//			    try {
			    	System.out.println("\n starting session in Emulator");
			    	webdriver = new AndroidDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities1);
			    	String url =webdriver.getCurrentUrl();
			    	System.out.println("\n The current url is"+url);
//			    	webdriver.get("https://www.oracle.com/");
			    	System.out.println("\n Session successfully started");
//			    }
//			    catch (MalformedURLException e) {
//			    	System.out.println("\n Error starting session in Emulator");
//					System.out.println(e.getMessage());
//				}
			    
			    
			   
			    
			
			if (wdUrl == null)
				wdUrl = "http://localhost:4723/wd/hub";
////			
//			webdriver = new AndroidDriver(new URL(wdUrl), capabilities1);
////			webdriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			
		} else {
			throw new Exception("Unsupported browser: " + appBrowser);
		}

		// if wdUrl != null, it is a RemoteWebDriver which needs to be augmented
//		if (wdUrl != null) {
//			webdriver = new Augmenter().augment(webdriver);
//		}

//		if (!(Browser.isIPhone() || Browser.isAndroid() || Browser.isNativeApp()))
//			Browser.setMainWindow(webdriver.getWindowHandle());
		// let us wait for at least a second for each element to appear
		// commented out due to
		// http://code.google.com/p/selenium/issues/detail?id=2798
		// webdriver.manage().timeouts().implicitlyWait(1000,
		// TimeUnit.MILLISECONDS);

		// http://code.google.com/p/chromedriver/issues/detail?id=156
//		if (!Browser.isChrome() && !Browser.isIPhone() && !Browser.isAndroid() && !Browser.isNativeApp()) {
//			webdriver.manage().timeouts().pageLoadTimeout(
//					Integer.parseInt(getProperty("defaultTimeout")), TimeUnit.MILLISECONDS);
//		}
//
//		if (!(Browser.isChrome() || Browser.isIPhone() || Browser.isNativeApp())) {
//			webdriver.manage().window().setPosition(new Point(xstart, ystart));
//			webdriver.manage().window().setSize(new Dimension(xres, yres));
//		}
//
		return webdriver;
	}

	/**
	 * Creates the FF profile.
	 *
	 * @return the firefox profile
	 */
	private static FirefoxProfile createFFProfile() {
		FirefoxProfile profile;
		String profPath = Config.getProperty("BrowserProfile");
		if (profPath != null && !profPath.isEmpty()) {
			profile = new FirefoxProfile(new File(profPath));
		} else {
			profile = new FirefoxProfile();
		}
		// these properties are always overridden
		// attempt to fix
		// http://code.google.com/p/selenium/issues/detail?id=2863 (occurs in VM
		// tests)
		profile.setPreference("capability.policy.default.HTMLIFrameElement.name", "allAccess");
		profile.setPreference("capability.policy.default.Window.frameElement", "allAccess");
		profile.setPreference("capability.policy.default.HTMLDocument.compatMode", "allAccess");
		profile.setPreference("capability.policy.default.Window.pageXOffset", "allAccess");
		profile.setPreference("capability.policy.default.Window.pageYOffset", "allAccess");
		
		try{
			profile.setPreference("webdriver.log.file", Config.getCommonFolderPath()+"\\..\\"+Config.getCurrentProjectName()+"\\reports\\"+"\\firefox_console.log");
		}catch(Exception e){}
		
		//to clear the browser cache
		profile.setPreference("browser.cache.offline.enable",false);
		// to disable any startup pages loaded by default
		profile.setPreference("browser.startup.page", 0);
		profile.setPreference("browser.startup.homepage", "about:blank");
		// to fix 13524645
		int timeout = Integer.parseInt(getProperty("defaultTimeout")) / 1000;
		timeout = timeout < 30 ? 30 : timeout;
		profile.setPreference("dom.max_script_run_time", timeout);
		profile.setPreference("dom.max_chrome_script_run_time", timeout);
		return profile;
	}

	private static void setPreferences(FirefoxProfile profile){
		String[] preferences, prefContainer;
		String preferencesContainer = Config.getProperty("FirefoxPreferences");
		if (preferencesContainer != null && !preferencesContainer.isEmpty()){
			preferences = preferencesContainer.split("\\|");
			for(String preference: preferences){
				prefContainer = preference.split("\\=");
				profile.setPreference(prefContainer[0], Boolean.valueOf(prefContainer[1]));
			}			
		}
	}
	
	/**
	 * Load FF extensions.
	 *
	 * @param profile the FF profile
	 */
	private static void loadFFExtensions(FirefoxProfile profile) {
		String[] extensionPaths;
		String exprop = Config.getProperty("FirefoxExtensions");
		if (exprop != null && !exprop.isEmpty()) {
			extensionPaths = exprop.split("\\|");
			for (int i = 0; i < extensionPaths.length; i++) {
				try {
					profile.addExtension(new File(extensionPaths[i]));
				} catch (Exception e) {
					Logger.warning("Error upon opening extension by path " + extensionPaths[i]
							+ ". Original exception: " + e.getMessage());
				}
				// disable firebug firstrun popup
				if (extensionPaths[i].toLowerCase().contains("firebug")) {
					profile.setPreference("extensions.firebug.showFirstRunPage", false);
				}
			}
		}
	}
}
