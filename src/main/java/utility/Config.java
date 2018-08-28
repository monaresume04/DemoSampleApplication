package utility;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

public class Config {

	private XMLConfiguration configuration = null;
	private static Config instance = null;
	private static String currentEnvironment = "";

	public Config() throws ConfigurationException {
		String configPath = System.getProperty("ConfigurationFile");
		if (configPath == null) {
			configPath = "configs/config.xml";
		}
		loadConfig(configPath);
	}
	
	
	public void loadConfig(String ConfigPath) throws ConfigurationException {
		this.configuration =new XMLConfiguration(ConfigPath);
		currentEnvironment = this.configuration.getString("environment.configPath");
		if(!currentEnvironment.isEmpty()) {
			XMLConfiguration envConf;
			envConf =new XMLConfiguration(currentEnvironment);
			this.configuration.clearTree("environmentConfig");
			this.configuration.addNodes("environmentConfig", envConf.getRoot().getChildren());
		}
	}
	
	public static Config getInstance() {
		if(instance==null) {
			try {
				instance =new Config();
			}catch(ConfigurationException e) {
				e.printStackTrace();
			}
			
		}
		return instance;
	}
	public XMLConfiguration getConfiguration() {
		return this.configuration;
	}
	public static String getString(String KeyPath) {
		return getString(KeyPath,null);
	}
	
	public static String getString(String keyPath,String defaultValue) {
		return getInstance().getConfiguration().getString(keyPath, defaultValue);
	}
}

