package atg.test.selenium.business;

import static atg.test.selenium.core.Config.getProperty;
import static atg.test.selenium.core.Selenium.flexdriver;
import static atg.test.selenium.core.Selenium.webdriver;
import static atg.test.selenium.core.autoit.AutoIt.autoit;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.mobile.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import atg.test.selenium.controls.flexui.am.UIMap;
import atg.test.selenium.controls.flexui.core.UIMapBase.AutoIt.AuthenticationDialogFF;
import atg.test.selenium.controls.webui.core.BaseWebUIElement;
import atg.test.selenium.controls.webui.core.FrameManager;
import atg.test.selenium.controls.webui.csa.pages.HomePage;
import atg.test.selenium.controls.webui.endeca.utils.XmgrUtils;
import atg.test.selenium.core.Config;
import atg.test.selenium.core.Logger;
import atg.test.selenium.core.Selenium;
import io.appium.java_client.MobileBy;

import com.google.common.base.Function;

/**
 * All common browser related operations
 *
 * @author Igor Khrol
 */
public final class Browser {

	private Browser() {
		// Utility class should not have a public or default constructor
	}

	private static String mainWindow = null;

	//TODO:	[SG] I don't know what this is for. VM team to review.
	public static final String WINDOW_ID = "windowID";

	private static final long DEFAULT_TIMEOUT = Long.parseLong(getProperty("defaultTimeout"));

	/***
	 * Enumeration with custom store names. Each name is a short store name in the url.
	 * @author Sergey_Pashuk
	 *
	 */
	public enum CustomStoreName{
		MOVIESTORE ("moviestore"),
		VIDEOGAMESTORE ("videogamestore"),
		HOMEUS ("homeus"),
		STOREUS ("storeus"),
		STOREDE ("storede"),
		DEFAULT ("");
		private final String value;

		private CustomStoreName(String value){
			this.value = value;
		}

		public String getName(){
			return value;
		}
	}

	/**
	 * Indicates that opened browse mode (modifications are impossible).
	 */
	public static boolean isBrowseMode = false;

	public enum CaptureNetworkTrafficFormat  {
		XML("xml"), JSON ("json"),
		PLAIN("plain");
		private String value;
		CaptureNetworkTrafficFormat(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	/**
	 *	This class is used to interrupt webdriver.get() method by timeout since it is not asynchronous.
	 *	See http://code.google.com/p/selenium/issues/detail?id=687 for details.
	 */
	private static class TimeoutThread extends Thread {

	    private final int timeout;
	    private boolean timeoutExpired = false;
	    private boolean shoudStop = false;

	    public TimeoutThread(int seconds) {
	        timeout = seconds;
	    }

		@Override
		public void run() {
			long finishTime = timeout * 1000L + System.currentTimeMillis();
			while (System.currentTimeMillis() < finishTime) {
				if (shoudStop) {
					break;
				}
				closeAuthDialogIfExist();
				if (timeoutExpired) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Logger.debug("InterruptedException in TimeoutThread occured");
				}
			}
			Logger.debug("TimeoutThread finished");
		}

	    public void stopThread(){
	    	Logger.debug("Stopping TimeoutThread");
	    	shoudStop = true;
	    }

	    public boolean closeAuthDialogIfExist(){
	    	String title = AuthenticationDialogFF.getTitle();
	    	boolean isExist = autoit.winExists(title, "")==1;

	    	if (isExist) {
	    		Logger.debug("Firefox Authentication Dialog exists");
	    		timeoutExpired = true;
	    		Logger.debug("Attempting to close Firefox Authentication Dialog");
	    		autoit.winClose(title, "");
			} else {
				Logger.debug("Firefox Authentication Dialog doesn't exist");
			}
	    	return isExist;
	    }
	}

	/**
	 * Reloads web page
	 */
	public static void refresh() {
		webdriver.navigate().refresh();
		Browser.waitDocumentReady();
	}

	/**
	 * Goes Back a page
	 */
	public static void goBack() {
		webdriver.navigate().back();
		Browser.waitDocumentReady();
	}
	
	/**
	 * Goes Back a page
	 */
	public static void goForward() {
		webdriver.navigate().forward();
		Browser.waitDocumentReady();
	}

	/**
	 * Maximizes browser window
	 */
	public static void maximize() {
		//	we start Chrome maximized; besides this method does not seem to be supported in Chrome anyway
		//	[ailyukov] Appium doesn't not support this method yet.
		if(!Browser.isChrome() && !Browser.isIPhone() && Browser.isMaximize() ){
			FrameManager.resetFrame();	//	workaround for WebDriver bug when a frame is selected
			webdriver.manage().window().maximize();
		}
	}

	private static boolean isMaximize() {
		// true by default
		return Boolean.parseBoolean(getProperty("isMaximize", "true"));
	}

	/**
	 * Returns if is flex loaded.
	 *
	 * @return true, if is flex loaded
	 */
	public static boolean isFlexLoaded() {
		BaseWebUIElement flexObj = new BaseWebUIElement(By.cssSelector("*#" + getProperty("flexID")));
		return flexObj.isPresent();
	}

	/**
	 * Verifies whether flex UI is loaded.
	 *
	 * @param expectFlex do we expect to see flex UI loaded
	 */
	public static void verifyFlexLoaded(boolean expectFlex) {
		Assert.assertEquals("Flex UI check", expectFlex, Browser.isFlexLoaded());
	}

	/**
	 * TODO check
	 * TODO comment
	 * @throws Exception
	 */
	public static void waitFlexLoaded() throws Exception{
		flexdriver.flexWaitForLoad(getProperty("flexID"));

		//Added to check Site Templates
		Wait<String> wait = new FluentWait<String>("")
		.withTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);

		wait.until(new Function<String, Boolean>() {
			@Override
			public Boolean apply(String target) {
				try {
					return flexdriver.getFlexExists(UIMap.BrowsePanelMap.commonLocatorToSiteTemplates) ||
							flexdriver.getFlexExists(UIMap.BrowsePanelMap.commonLocatorToAssetSelector) ||
							flexdriver.getFlexExists(UIMap.BrowsePanelMap.commonLocatorToAssetSelectorBrowseMode) ||
							flexdriver.getFlexExists(atg.test.selenium.controls.flexui.osc.UIMap.LoginPageMap.loginForm);
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
		});
		if (flexdriver.getFlexExists(UIMap.BrowsePanelMap.commonLocatorToSiteTemplates)) {
			flexdriver.flexWaitForElementVisible(UIMap.BrowsePanelMap.commonLocatorToSiteTemplates);
		}
		if (flexdriver.getFlexExists(UIMap.BrowsePanelMap.commonLocatorToAssetSelector)) {
			flexdriver.flexWaitForElementVisible(UIMap.BrowsePanelMap.commonLocatorToAssetSelector);
		}
	}

	/**
	 * Wait for Flex to disappear
	 * @throws Exception
	 */
	public static void waitFlexUnloaded() throws Exception{
		new BaseWebUIElement(By.cssSelector("*#" + getProperty("flexID"))).waitForElementNotPresent();
	}

	/** TODO
	 * Template for sync after refresh call.
	 */
	public static void waitForPageToLoad() {
		//Selenium.selenium.waitForPageToLoad(getProperty("defaultTimeout"));
		Browser.waitDocumentReady();	//	but it is not an adequate replacement
	}


	/***
	 * Gets the absolute URL of the current page.
	 *
	 * @return
	 * 	- the absolute URL of the current page
	 */
	public static String getLocation() {
		//return Selenium.selenium.getLocation();
		return webdriver.getCurrentUrl();
	}

	public static String getPageTitle() {
		return webdriver.getTitle();
	}

	public static String getMobileCRSDomainName(){
			return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort");
	}

	/**
	 * Return BCC URL
	 * @return BCC URL
	 */
	public static String getBCCURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("CAAdminPort") + "/atg/bcc";
	}

	/**
	 * Return Agent BCC URL
	 * @return Agent BCC URL
	 */
	public static String getAgentBCCURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("AgentHttpPort") + "/atg/bcc";
	}

	/**
	 * Return Direct Edit URL
	 * @return Direct Edit URL
	 */
	public static String getDEURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("CAAdminPort") + "/ControlCenter";
	}

	/**
	 * Return CRS URL
	 * @return CRS URL
	 */
	public static String getCRSURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort") + "/crs";
	}

	public static String getMotorprose(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort") + "/teststation";
	}

	/**
	 * Return CSA URL
	 * @return CSA URL
	 */
	public static String getCSAURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort") + "/csa";
	}

	/**
	 * Return CSC URL
	 * @return CSC URL
	 */
	public static String getCSCURL() {
		return Config.getProtocol() + getProperty("AgentHostName") + ":" + getProperty("AgentHttpPort") + "/agent";
	}

	/**
	 * Return Mobile CRS URL
	 * @return Mobile CRS URL
	 */
	public static String getMobileCRSURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort") + "/crs/mobile";
	}

	/**
	 * Return Endeca URL
	 * @return Endeca URL
	 */
	public static String getEndecaURL() {
		return Config.getProtocol() + getProperty("EndecaHostName") + ":" + getProperty("EndecaHttpPort") + "/ifcr/sites/"+ XmgrUtils.getSiteName() + ".html";
	}
	
	/**
	 * Return Endeca URL
	 * @return Endeca URL
	 */
	public static String getEndecaWelcomePageURL() {
		return Config.getProtocol() + getProperty("EndecaHostName") + ":" + getProperty("EndecaHttpPort") + "/ifcr/index.html";
	}

	/**
	 * Return Endeca URL with application
	 * @param app - The application to login to
	 * @return Endeca URL
	 */
	public static String getEndecaURL(String app) {
		return Config.getProtocol() + getProperty("EndecaHostName") + ":" + getProperty("EndecaHttpPort") + "/ifcr/sites/" + app + ".html";
	}

	public static String getDiscoverURL() {
		return Config.getProtocol() + getProperty("EndecaHostName") + ":" + getProperty("EndecaHttpPort") + "/discover/";
	}

	public static String getDiscoverAuthoringURL() {
		return Config.getProtocol() + getProperty("EndecaHostName") + ":" + getProperty("EndecaHttpPort") + "/discover-authoring/";
	}

	/**
	 * Open Endeca login page.
	 */
	public static void openEndecaLoginPage() {
		Browser.maximize();
		Selenium.get(getEndecaURL());
		Browser.waitDocumentReady();
	}

	/**
	 * Open Endeca login page with application.
	 */
	public static void openEndecaLoginPage(String app) {
		Browser.maximize();
		Selenium.get(getEndecaURL(app));
		Browser.waitDocumentReady();
	}

	/**
	 * Open BCC login page.
	 */
	public static void openBCCLoginPage() {
		//selenium.setTimeout(timeout);
		Browser.maximize();
		Selenium.get(getBCCURL());
	}

	/**
	 * Opens mobile crs - default store.
	 * @throws InterruptedException
	 */
	public static void openMobileCRS() throws InterruptedException{
		openMobileCRS("");
	}

	/**
	 * Opens mobile crs - specified store.
	 *
	 * @param storeName - raw name of the store to open ("homeus", "storeus", "storede" etc.)
	 * @throws InterruptedException
	 */
	public static void openMobileCRS(String storeName) throws InterruptedException{
		String url = Browser.getMobileCRSURL()
				+ (storeName.isEmpty() ? "/" : ("/" + storeName));
		Selenium.get(url);
		Browser.waitForPageLoadingStarts(3000);
		Thread.sleep(10000);
		Browser.waitDocumentReady();
	}

	/**
	 * Open crs - default store.
	 */
	public static void openCRS(){
		openCRS("");
	}
	/**
	 * Open csa - default store.
	 * @throws InterruptedException
	 */
	public static void openCSA() throws InterruptedException{
		openCSA("");
	}
	
	public static void openJetPage() throws InterruptedException{
//		openJetPage("");
	}

	public static void openMobileCSA() throws InterruptedException{
		openMobileCSA("");
	}
	public static void openJetMobilePage() throws InterruptedException{
		openJetMobilePage("");
	}
	
	public static void openJetMobilePage(String storeName) throws InterruptedException{		
		String url = "https://slc09cxg.us.oracle.com:1998/mobile/custom/NavigatorData/navigatorMetadata";
		System.out.println("The first url opened is for Rest Call"+url);
		Selenium.get(url);
		Browser.waitForPageLoadingStarts(3000);
//		Browser.waitDocumentReady();
	}
	
	public static void openMyTeamPage() throws InterruptedException{	
		
		
		String welcomeUrl = "https://p1gsi2r13ko2.fa.dc1.c9dev1.oraclecorp.com/fscmUI/hcm/vp/myTeam";
		System.out.println("The Welcome Page opened is:"+welcomeUrl);
		Selenium.get(welcomeUrl);
		Thread.sleep(10000);
		
		
//		WebElement Navigator = Selenium.webdriver.findElement(By.id("_UIShell_headerNav_icon"));
//		mClick(Navigator);
//		
//		WebElement Myteam = Selenium.webdriver.findElement(By.className("ocaj-nav-moc-outer-text ocaj-nav-list-text ocaj-nav-samelineelement"));
//		mClick(Myteam);
//		
		WebElement primaryCard = Selenium.webdriver.findElement(By.className("card-contexual-actions"));
		mClick(primaryCard); 
		
		WebElement clickCard = Selenium.webdriver.findElement(By.className("card-contexual-actions"));
		mClick(clickCard); 
		
		Thread.sleep(5000);
	}
	
	
public static void openWelcomePage() throws InterruptedException{			
		
	String welcomeUrl = "https://p1gsi2r13ko2.fa.dc1.c9dev1.oraclecorp.com/homePage/faces/FuseWelcome";	
	
		System.out.println("The Welcome Page opened is:"+welcomeUrl);
		Selenium.get(welcomeUrl);
		Thread.sleep(5000);
		
		
		WebElement username = Selenium.webdriver.findElement(By.name("userid"));
		writeText(username, "mary.jane");	
		
		WebElement password = Selenium.webdriver.findElement(By.name("password"));
		writeText(password, "Welcome1");
		
		WebElement submit = Selenium.webdriver.findElement(By.xpath(".//button[@id='btnActive']"));
//		submit.click();
		mClick(submit);
		Thread.sleep(5000);
	}
	
	public static void mClick(WebElement element){
		
//		JavascriptExecutor executor = (JavascriptExecutor)driver;
//		executor.executeScript("arguments[0].click();", element);
		((JavascriptExecutor) Selenium.webdriver).executeScript("arguments[0].click();", element);
	}
	
	
	public static void writeText(WebElement textField, String text) {
	    ((JavascriptExecutor) Selenium.webdriver).executeScript("arguments[0].value='" + text + "';", textField);
	}
	
	public static void openJetHomePage(){		
		String HomeUrl = "https://p1gsi2r13ko2.fa.dc1.c9dev1.oraclecorp.com/fscmUI/hcm/vp/home";
		System.out.println("The home Page opened is:"+HomeUrl);
		Selenium.get(HomeUrl);
		Browser.waitForPageLoadingStarts(3000);
		
//		Browser.waitDocumentReady();
	}


	public static void openMobileCSA(String storeName) throws InterruptedException{
		String url = "http://oracle.com";
		System.out.println("The url to be opened is"+url);
		Selenium.get(url);
//		Browser.waitForPageLoadingStarts(3000);
//		Thread.sleep(10000);
		Browser.waitDocumentReady();
	}

	public static String getMobileCSAURL(){
		return Config.getProtocol() + getProperty("CA1HostName") + ":" + getProperty("ProdAdminPort") + "/csa";
	}
	/**
	 * Open csa for vm
	 * @param CustomStoreName
	 * @throws InterruptedException
	 */
	public static void openCSA(CustomStoreName customNameStore) throws InterruptedException{
		openCSA(customNameStore.value);
	}
	/**
	 * Open crs for vm
	 * @param CustomStoreName
	 */
	public static void openCRS(CustomStoreName customNameStore){
		openCRS(customNameStore.value);
	}
	/**
	 * Open csa - specified store
	 * @param customNameStore - raw name of the store to open ("homeus", "storeus", "storede" etc.)
	 * @throws InterruptedException
	 */
	public static void openCSA(String customNameStore) throws InterruptedException{

		if(isMobileIpad()||isMobileIphone()||isMobileAndroid())
		{
//			String url = Browser.getCSAURL() + (customNameStore.isEmpty() ? "" : ("/" + customNameStore));
			String url = "http://google.com";
			Browser.open(url);
			Browser.waitDocumentReady();
			try {
				if(customNameStore.equals("")){
				HomePage.FeaturedProductList.getInstance().currentPage.waitForElementOnPage();
				HomePage.getInstance().isPresent();
				}
			} catch (Exception e) {
				Logger.debug("Homepage not present");
				e.printStackTrace();
			}
		}
		else
		{
		String url = Browser.getCSAURL() + (customNameStore.isEmpty() ? "" : ("/" + customNameStore));
		Browser.open(url);
		Thread.sleep(1500);
		Browser.waitDocumentReady();
		Browser.maximize();
		Thread.sleep(1000);
		}
	}

	public static boolean isMobileIphone()
	{
		return getProperty("Device").toLowerCase().startsWith("iphone");
	}

	public static boolean isMobileIpad()
	{
		return getProperty("Device").toLowerCase().startsWith("ipad");
	}

	public static boolean isMobileAndroid()
	{
		return getProperty("Device").toLowerCase().startsWith("android");
	}

	/**
	 * Open crs - specified store
	 * @param customNameStore - raw name of the store to open ("homeus", "storeus", "storede" etc.)
	 */
	public static void openCRS(String customNameStore){
		String url = Browser.getCRSURL() + (customNameStore.isEmpty() ? "" : ("/" + customNameStore));
		Browser.open(url);
		Browser.waitDocumentReady();
		Browser.maximize();
	}

	public static void openMotorprise()
	{
		Browser.maximize();
		Selenium.get(Browser.getMotorprose());
		Browser.waitDocumentReady();
	}
	/**
	 * Open CSC login page
	 */
	public static void openCSCLoginPage() {
		Browser.maximize();
		Selenium.get(getCSCURL());
	}

	/**
	 * Open Agent BCC login page.
	 */
	public static void openAgentBCCLoginPage() {
		//selenium.setTimeout(timeout);
		Browser.maximize();
		Selenium.get(getAgentBCCURL());
	}
	/**
	 * Open osc login page.
	 *
	 * @throws Exception the exception
	 */
	public static void openOSCLoginPage() throws Exception {
		String url = Config.getProtocol() + getProperty("oscHostName");
		Browser.open(url);
		Browser.waitFlexLoaded();
	}

	/**
	 * Clears browser cache. Method clears history in case browser is IE and re-starts selenium (for FF we do not need special history cleanup because all the history is deleted on selenium closing).
	 * After invoking this method you must login to application.
	 *
	 * @throws Exception the exception
	 */
	public static void clearBrowserHistory() throws Exception{
		if (getProperty("AppBrowser").equals("iexplore")){
			String command = "RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 4351";
			Process proc = Runtime.getRuntime().exec(command);
			proc.waitFor();
		}
		Selenium.stop();
		Selenium.start();
	}

	public static void clearCookies() throws Exception{
		//	in IE cookies are not deleted for some reason, possibly http://code.google.com/p/selenium/issues/detail?id=615
		if (Browser.isIE()){
			String command = "RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2";
			Process proc = Runtime.getRuntime().exec(command);
			proc.waitFor();
		}
		else{
			webdriver.manage().deleteAllCookies();
		}

		// [SV] For some reason Appium Web-driver does not start-up after stopping
		// Need to investigate more closely
		if (!Browser.isIPhone()) {
			Selenium.stop();
			Selenium.start();
		}
	}

	/**
	 * Restart the browser. Simulates closing and opening browser.
	 * Saves browser cookies and restore them after selenium webdriver restart.
	 * Can be useful to make logged user session to expire.
	 * [SV] Verified on Mobile Safari (iphone)
	 *
	 * @throws Exception
	 */
	public static void restartBrowser() throws Exception {
		// Current location
		String url = Browser.getLocation();
		// Save cookies
		Set<Cookie> cookies = webdriver.manage().getCookies();

		// Restart selenium webdriver => close & open the browser
		Selenium.stop();
		Selenium.start();

		// Restore cookies
		for (Cookie cookie : cookies) {
			// Except the ones that expired at the end of previous session
			if(cookie.getExpiry() != null)
				webdriver.manage().addCookie(cookie);
		}

		// Navigate to the same page as before closing the browser
		webdriver.navigate().to(url);
		Browser.waitDocumentReady();
	}

	public static boolean isIE(){
		return getProperty("AppBrowser").equalsIgnoreCase("iexplore");
	}

	public static boolean isFF(){
		return getProperty("AppBrowser").equalsIgnoreCase("firefox");
	}

	public static boolean isAndroid() {
		return getProperty("AppBrowser").equalsIgnoreCase("android");
	}

	public static boolean isIPhone() {
		return getProperty("AppBrowser").equalsIgnoreCase("iphone");
	}

	public static boolean isNativeApp() {
		return getProperty("AppBrowser").equalsIgnoreCase("nativeapp");
	}

	public static boolean isChrome() {
		return getProperty("AppBrowser").equalsIgnoreCase("chrome");
	}

	/**
	 * Returns is current browser is Internet Explorer 9. <br>
	 * Unlike {@link #isFF()} and {@link #isIE()}, this function
	 * checks browser's user agent string.
	 * @return is IE9
	 */
	public static boolean isIE9() {
		String userAgentStr = (String) ((JavascriptExecutor) webdriver).executeScript("return navigator.userAgent;");
		Logger.debug("User agent string: '" + userAgentStr + "'");
		return userAgentStr.contains("Trident/5.0;");
	}

	/**
	 * Returns is current browser is Internet Explorer 11. <br>
	 * Unlike {@link #isFF()} and {@link #isIE()}, this function
	 * checks browser's user agent string.
	 * @return is IE11
	 */
	public static boolean isIE11() {
		String userAgentStr = (String) ((JavascriptExecutor) webdriver).executeScript("return navigator.userAgent;");
		Logger.debug("User agent string: '" + userAgentStr + "'");
		return userAgentStr.contains("Trident/7.0;");
	}
	
	public static String getCurrentUserAgent() {
		String userAgentStr = (String) ((JavascriptExecutor) webdriver).executeScript("return navigator.userAgent;");
		Logger.debug("User agent string: '" + userAgentStr + "'");
		return userAgentStr;
	}

	public static void waitDocumentReady() {
		waitDocumentReady(DEFAULT_TIMEOUT);
	}

	public static void waitDocumentReady(String timeout) {
		waitDocumentReady(Long.parseLong(timeout));
	}

	public static void waitDocumentReady(long timeout) {
		String state;

		// Wait until page starts loading to skip animation
		if(isIPhone() || isAndroid()) {
			 waitForPageLoadingStarts(5000);
		}

		//	ignoring exception which is thrown sometimes in IE9
		Wait<JavascriptExecutor> wait = new FluentWait<JavascriptExecutor>((JavascriptExecutor)webdriver)
		  .withTimeout(timeout, TimeUnit.MILLISECONDS)
		  .ignoring(WebDriverException.class)
		  .pollingEvery(100, TimeUnit.MILLISECONDS);

		try{
			wait.until(new Function<JavascriptExecutor, Boolean>() {
				@Override
				public Boolean apply(JavascriptExecutor js) {
					return ((String)js.executeScript("return document.readyState;")).equals("complete");
				}
			});
		}
		catch(TimeoutException e){
			//		sometimes document state remains interactive
			state = (String)((JavascriptExecutor)webdriver).executeScript("return document.readyState;");
			if(!state.equals("interactive")){
				throw new TimeoutException("Document state is " + state + ". Original message is: " + e.getMessage());
			}
		}
	}

	/**
	 * Wait until page starts loading.<br>
	 * Time frame after some action (e.g. click) and before navigating to a new
	 * page usually is used to perform some sort of animation or visual effect.<br>
	 * <br>
	 * NOTE: Not supported by Selenium WebDriver. For use with Appium.
	 *
	 * @param timeout
	 *            the timeout in milliseconds
	 */
	public static void waitForPageLoadingStarts(long timeout) {
		Wait<JavascriptExecutor> waitLoading = new FluentWait<JavascriptExecutor>((JavascriptExecutor) webdriver)
			.withTimeout(timeout, TimeUnit.MILLISECONDS)
			.pollingEvery(50, TimeUnit.MILLISECONDS);
		try {
			waitLoading.until(new Function<JavascriptExecutor, Boolean>() {
				@Override
				public Boolean apply(JavascriptExecutor js) {
					return ((String) js.executeScript("return document.readyState;")).equals("loading");
				}
			});
		} catch (TimeoutException e1) {
			// throw new TimeoutException("Page did not start loading. Error message is: " + e1.getMessage());
			Logger.warning("Page did not start loading. Error message is: " + e1.getMessage());
		}
	}

	/**
	 * Waits until body's style attribute doesn't contain 'cursor: wait' string.<br>
	 * When state of some UI components like checkbox, select or another is changed, body <i>style</i> attribute
	 * can be changed to display busy mouse pointer. Once app reflects the state of changed UI component,
	 * 'cursor: wait' in style will be replaced with smth. like 'cursor: default'.<br>
	 * If you observed such behavior, you can use this function as auxiliary
	 * synchronization point.<br>
	 * Please don't use this function if you don't observe described situation because
	 * in this case function will bring time penalty.
	 *
	 */
	public static void waitForBodyStyleNotBusy(){
		final String busyCursorStyle = "cursor: wait";
		BaseWebUIElement body = new BaseWebUIElement(By.xpath(".//body[@class='atg']"));
		Wait<BaseWebUIElement> wait = new FluentWait<BaseWebUIElement>(body)
											.withTimeout(300, TimeUnit.MILLISECONDS)
											.pollingEvery(50, TimeUnit.MILLISECONDS);
		try {
			wait.until(new Function<BaseWebUIElement, Boolean>() {
				@Override
				public Boolean apply(BaseWebUIElement body) {
					String bodyStyle = body.getAttribute("style").toLowerCase();
					return bodyStyle.contains(busyCursorStyle);
				}
			});
		} catch (TimeoutException e) {
			//DO NOT NEED to perform any specific action.
		}

		wait = new FluentWait<BaseWebUIElement>(body)
				.withTimeout(DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS);
		wait.until(new Function<BaseWebUIElement, Boolean>() {
			@Override
			public Boolean apply(BaseWebUIElement body) {
				String bodyStyle = body.getAttribute("style").toLowerCase();
				return !bodyStyle.contains(busyCursorStyle);
			}
		});
	}

	/**
	 * Store main window name/handle
	 * @param nameOrHandle
	 */
	public static void setMainWindow(String nameOrHandle){
		mainWindow = nameOrHandle;
	}

	/**
	 * Close window. If only there is only one window present blank window will left after
	 * this command.
	 * @throws Exception
	 */
	@Deprecated
	public static void closeWindow() throws Exception {
		throw new Exception("Deprecated. Use bccLogin with 3 params to reopen the window.");
	}

	/**
	 * Closes window with given ID with following main window select
	 * @param windowID - id of window to close
	 */
	public static void closeWindow(String windowID){
		selectWindow(windowID);
		webdriver.close();
		selectMainWindow();
	}

	/**
	 * This method returns the id of the current window
	 *
	 * @author Matthew_kelly
	 */
	public static String getWindowHandle() {
		return webdriver.getWindowHandle();
	}

	/**
	 * Gets the current window inner size.
	 *
	 * @return the window inner size
	 */
	public static Dimension getWindowInnerSize() {
//		Hope this work for all browsers.
//		window.innerWidth/innerHeight not work in IE8-, and even in IE9+ it is not available in quirks mode
		int width = Integer.parseInt(((JavascriptExecutor)webdriver).executeScript("return document.documentElement.clientWidth;").toString());
		int height = Integer.parseInt(((JavascriptExecutor)webdriver).executeScript("return document.documentElement.clientHeight;").toString());
		return new Dimension(width, height);
	}

	/**
	 * Open URL in current window.
	 *
	 * @param url the url
	 */
	public static void open(String url) {
		FrameManager.crsFrame = null;
//		Browser.maximize();
		Selenium.get(url);
	}

	/**
	 * Open URL in current window, interrupt page load if timeout expires
	 * @param url the url
	 * @param timeout - timeout to wait for page to load, in seconds
	 */
	public static void open(String url, int timeout) {
		FrameManager.crsFrame = null;
		Browser.maximize();
		TimeoutThread timeoutThread = new TimeoutThread(timeout);
		timeoutThread.start();
		Selenium.get(url);
		timeoutThread.stopThread();
		timeoutThread.interrupt();
		if(timeoutThread.timeoutExpired){
			throw new TimeoutException("URL " + url + " was not loaded in " + timeout + " seconds.");
		}
	}

	/***
	 * Open URL in new Window.
	 *
	 *  @param url
	 *  	- the url
	 *  @param windowID
	 *  	- the new window id.
	 */
	public static void openWindow(String url, String windowID) {
		((JavascriptExecutor) webdriver).executeScript(String.format("window.open('%1$s','%2$s')", url, windowID));
		//	not sure if it works
	}

	/***
	 * Select window.
	 *
	 * @param windowID
	 * 	  	- the window id.
	 * @throws Exception
	 */
	public static void selectWindow(String windowID){
		webdriver.switchTo().window(windowID);
	}

	public static String[] getWindowHandles() {
		Set<String> handles = webdriver.getWindowHandles();
		return handles.toArray(new String[handles.size()]);
	}

	/**
	 * Selects main window.
	 * @throws Exception
	 */
	public static void selectMainWindow(){
		mainWindow = getWindowHandles()[0];
		webdriver.switchTo().window(mainWindow);
	}

	/**
	 * Selects second window.
	 */
	public static void selectChildWindow(){
		Assert.assertTrue("There is only one window. No any child window.", webdriver.getWindowHandles().size()>1);
		String secondHwnd = getWindowHandles()[1];
		webdriver.switchTo().window(secondHwnd);
	}

	/**
	 * Selects window by index.
	 * @param index - index of window to be selected (starts with 0)
	 */
	public static void selectWindow(int index){
		Assert.assertTrue("There is no window with index: "+index, webdriver.getWindowHandles().size()>index);
		String secondHwnd = getWindowHandles()[index];
		webdriver.switchTo().window(secondHwnd);
	}
}
