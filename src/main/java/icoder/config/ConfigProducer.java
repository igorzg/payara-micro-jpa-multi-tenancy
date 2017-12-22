package icoder.config;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.Annotated;
import javax.enterprise.inject.spi.InjectionPoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Produce config
 *
 * @since 1.0
 */

public class ConfigProducer {

  private static Map<String, Config> configs = new HashMap<>();

  @Produces
  @IConfig
  public Config produce(InjectionPoint injectionPoint) throws IOException {
    Annotated annotated = injectionPoint.getAnnotated();
    IConfig iConfig = annotated.getAnnotation(IConfig.class);
    return load(iConfig.value());
  }

  /**
   * To string
   * @param key String
   * @param def String
   *
   * @return String
   */
  public static String toString(String key, String def) {
    return loadDefault().toString(key, def);
  }

  /**
   * To string
   * @param key String
   * @return String
   */
  public static String toString(String key) {
    return loadDefault().toString(key);
  }

  /**
   * To integer
   * @param key String
   * @return Integer
   */
  public static Integer toInt(String key) {
    return loadDefault().toInt(key);
  }

  /**
   * Check if is staging
   *
   * @return String
   */
  public static boolean isStaging() {
    return loadDefault().isStaging();
  }

  /**
   * Check if is production
   *
   * @return String
   */
  public static boolean isTest() {
    return loadDefault().isTest();
  }

  /**
   * Check if is production
   *
   * @return String
   */
  public static boolean isProduction() {
    return loadDefault().isProduction();
  }

  /**
   * Check if is ci
   *
   * @return String
   */
  public static boolean isCI() {
    return loadDefault().isCI();
  }
  /**
   * Load default config
   * @return Config
   */
  public static Config loadDefault() {
    return load("config.properties");
  }

  /**
   * Loads config
   * @param key String
   * @return Config
   */
  public static Config load(String key) {
    if (!configs.containsKey(key)) {
      Config config = new Config();
      try {
        config.load(key);
      } catch (Exception e) {
        e.printStackTrace();
      }
      configs.put(key, config);
    }
    return configs.get(key);
  }

}
