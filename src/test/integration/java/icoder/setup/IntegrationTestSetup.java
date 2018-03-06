package icoder.setup;

import icoder.RestApplication;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;

@ArquillianSuiteDeployment
public class IntegrationTestSetup {
    @Deployment
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class)
                .addPackages(true, RestApplication.class.getPackage())
                .addAsResource("persistence.xml","META-INF/persistence.xml")
                .addAsWebInfResource("beans.xml")
                .addAsWebInfResource("ejb-jar.xml")
                .addAsWebInfResource("glassfish-resources.xml")
                .addAsResource("log4j2.xml")
                .addAsResource("config.properties");
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }
}
