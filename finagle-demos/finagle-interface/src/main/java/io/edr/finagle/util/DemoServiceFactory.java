package io.edr.finagle.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.edr.finagle.api.FinagleClientRegister;
import io.edr.finagle.demo.service.HelloService;

public class DemoServiceFactory {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    private HelloService.ServiceIface helloService;

    private FinagleClientRegister register;

    public synchronized HelloService.ServiceIface getHelloService() {
        if (helloService == null)
            try {
                helloService = (HelloService.ServiceIface) register.getService("helloService",
                        HelloService.class.getName());
            } catch (Exception ex) {
                logger.error("get helloService error", ex);
            }
        return helloService;
    }

    public void setFinagleClientRegister(FinagleClientRegister value) {
        this.register = value;
    }
}