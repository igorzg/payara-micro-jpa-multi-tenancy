package icoder.config;


import icoder.helpers.Strings;

import javax.enterprise.inject.Default;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


@Default
public class Config {

  public static final String DEVELOPMENT = "development";
  public static final String STAGING = "staging";
  public static final String TEST = "test";
  public static final String PRODUCTION = "production";
  public static final String CI = "ci";


  private Properties properties = new Properties();

  /**
   * Check if is development
   *
   * @return String
   */
  public boolean isDevelopment() {
    String env = getEnv();
    if (Strings.isNullOrEmpty(env)) {
      throw new RuntimeException("Configuration file is not loaded!");
    }
    return env.equals(DEVELOPMENT);
  }

  /**
   * Check if is staging
   *
   * @return String
   */
  public boolean isStaging() {
    String env = getEnv();
    if (Strings.isNullOrEmpty(env)) {
      throw new RuntimeException("Configuration file is not loaded!");
    }
    return env.equals(STAGING);
  }

  /**
   * Check if is production
   *
   * @return String
   */
  public boolean isTest() {
    String env = getEnv();
    if (Strings.isNullOrEmpty(env)) {
      throw new RuntimeException("Configuration file is not loaded!");
    }
    return env.equals(TEST);
  }

  /**
   * Check if is production
   *
   * @return String
   */
  public boolean isProduction() {
    String env = getEnv();
    if (Strings.isNullOrEmpty(env)) {
      throw new RuntimeException("Configuration file is not loaded!");
    }
    return env.equals(PRODUCTION);
  }

  /**
   * Check if is ci
   *
   * @return String
   */
  public boolean isCI() {
    String env = getEnv();
    if (Strings.isNullOrEmpty(env)) {
      throw new RuntimeException("Configuration file is not loaded!");
    }
    return env.equals(CI);
  }
  /**
   * Get Environment string
   *
   * @return String
   */
  public String getEnv() {
    return toString("environment");
  }
  /**
   * @param name String
   * @return String
   */
  public Integer toInt(String name) {
    return Integer.parseInt(toString(name));
  }

  /**
   * @param name String
   * @return String
   */
  public String toString(String name, String def) {
    return properties.containsKey(name) ? properties.getProperty(name) : def;
  }

  /**
   * @param name String
   * @return String
   */
  public String toString(String name) {
    return properties.getProperty(name);
  }

  /**
   * Load config file as Properties
   *
   * @param fileName    String
   * @param classLoader ClassLoader
   * @return Properties
   * @throws IOException
   */
  public Properties load(String fileName, ClassLoader classLoader) throws IOException {
    InputStream inputStream = classLoader.getResourceAsStream(fileName);
    properties.load(inputStream);
    return properties;
  }

  /**
   * Clear config
   */
  public void clear() {
    properties.clear();
  }

  /**
   * Load config file as Properties
   *
   * @param value String
   * @return Properties
   * @throws IOException
   */
  public Properties load(String value) throws IOException {
    InputStream inputStream = Config.class.getClassLoader().getResourceAsStream(value);
    properties.load(inputStream);
    return properties;
  }
}