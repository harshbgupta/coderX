package com.kritsn.ques;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright © 2025 Kritsn LLP. All rights reserved.
 *
 * @author Radhey (hr-sh)
 * @since Sep 01, 2025
 */
public class ProxyDemo {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Service target = new RealService();

        Service loggingProxy = (Service) Proxy.newProxyInstance(
                Service.class.getClassLoader(),
                new Class[]{Service.class},
                (Object proxyObj, Method method, Object[] args1) -> {
                    System.out.println("Before method: " + method.getName());
                    Object result = method.invoke(target, args1); // delegate to real object
                    System.out.println("After method: " + method.getName());
                    return result;
                }
        );

        System.out.println(loggingProxy.doWork("Harsh"));
    }
}


interface Service {
    String doWork(String input);
}

class RealService implements Service {
    public String doWork(String input) {
        return "Processed: " + input;
    }
}

/* Output:
Before: doWork
After: doWork
Processed: Harsh
*/