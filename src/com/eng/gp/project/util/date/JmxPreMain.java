package com.eng.gp.project.util.date;

import javax.management.MBeanServer;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.lang.management.ManagementFactory;
import java.util.HashMap;

/**
 * This Object is used for binding an applications JMX to a specific port in order to work well with firewalls.
 */
public class JmxPreMain
{
    private JmxPreMain()
    {
    }

    public static void premain(String agentArgs)
	    throws IOException
    {
        final int port = Integer.parseInt( System.getProperty("jmxPreMain.rmi.agent.port"));

        LocateRegistry.createRegistry(port);

        MBeanServer         mbs = ManagementFactory.getPlatformMBeanServer();

        JMXServiceURL       url = new JMXServiceURL("service:jmx:rmi://localhost:"+port+"/jndi/rmi://localhost:"+port+"/jmxrmi");

        JMXConnectorServer  cs  = JMXConnectorServerFactory.newJMXConnectorServer(url, new HashMap<String,Object>(), mbs);

        cs.start();
    }
}
