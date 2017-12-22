package icoder.setup;

import icoder.RestApplication;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@ArquillianSuiteDeployment
public class IntegrationTestSetup {
  @Deployment
  public static WebArchive createDeployment() {
    return ShrinkWrap.create(WebArchive.class)
         .addPackages(true, RestApplication.class.getPackage())
        .addAsWebInfResource("beans.xml")
        .addAsResource("log4j2.xml")
        .addAsResource("config.properties");
  }
}
