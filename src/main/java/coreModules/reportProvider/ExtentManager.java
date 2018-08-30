package coreModules.reportProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {
	 private static ExtentReports extent;
	 //   String fileName ="extent.html";
	    public static ExtentReports getInstance() {
	    	if (extent == null) {
	    		File location = new File("logs/" + getLogFileName() +"/extent.html");
	    		createInstance(location.getPath());
	    		try {
	    			FileUtils.forceMkdir(location.getParentFile());
	    		}catch(IOException e) {
	    			e.printStackTrace();
	    		}
	    	}
	    	
	    		
	        return extent;
	    }
	    
	    public static ExtentReports createInstance(String fileName) {
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
	        htmlReporter.config().setTestViewChartLocation(ChartLocation.BOTTOM);
	        htmlReporter.config().setChartVisibilityOnOpen(true);
	        htmlReporter.config().setTheme(Theme.STANDARD);
	        htmlReporter.config().setDocumentTitle(fileName);
	        htmlReporter.config().setEncoding("utf-8");
	        htmlReporter.config().setReportName(fileName);
	        
	        extent = new ExtentReports();
	        extent.attachReporter(htmlReporter);
	        
	        return extent;
	    }
	    
	    public static String getLogFileName() {
	    	Date date =new Date();
	    	return new SimpleDateFormat("yyyy.MM.dd").format(date)+File.separator+new SimpleDateFormat("yyyy.MM.dd HH-mm-ss.SSS").format(date);
	    }
}
