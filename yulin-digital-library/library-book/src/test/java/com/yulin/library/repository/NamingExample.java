package com.yulin.library.repository;

import java.util.Properties;

import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingFactory;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.listener.Event;
import com.alibaba.nacos.api.naming.listener.EventListener;
import com.alibaba.nacos.api.naming.listener.NamingEvent;

/**
 * @author nkorange
 */
public class NamingExample {

    public static void main(String[] args) throws NacosException {

        Properties properties = new Properties();
        properties.setProperty("serverAddr", System.getProperty("serverAddr"));
        properties.setProperty("namespace", System.getProperty("namespace"));

        NamingService naming = NamingFactory.createNamingService(properties);

        naming.registerInstance("library-gateway", "11.11.11.11", 8888, "TEST1");

        naming.registerInstance("library-gateway", "2.2.2.2", 9999, "DEFAULT");

        System.out.println(naming.getAllInstances("library-gateway"));

        naming.deregisterInstance("library-gateway", "2.2.2.2", 9999, "DEFAULT");

        System.out.println(naming.getAllInstances("library-gateway"));

        naming.subscribe("library-gateway", new EventListener() {
            @Override
            public void onEvent(Event event) {
                System.out.println(((NamingEvent)event).getServiceName());
                System.out.println(((NamingEvent)event).getInstances());
            }
        });
    }
}
