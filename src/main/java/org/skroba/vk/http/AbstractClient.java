package org.skroba.vk.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skroba.vk.url.UrlGenerator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public abstract class AbstractClient implements Client {
    private static final int TIMEOUT = 1_000;
    
    protected static final Logger LOGGER = LogManager.getFormatterLogger("AbstractClient");
    
    protected final UrlGenerator urlGenerator;
    
    protected AbstractClient(UrlGenerator urlGenerator) {
        this.urlGenerator = urlGenerator;
    }
    
    @Override
    public String getHost() {
        return urlGenerator.getHost();
    }
    
    @Override
    public boolean isHealthy() {
        return checkHost(urlGenerator.getHost());
    }
    
    private boolean checkHost(String host) {
        return nativePing(host) || nativePing6(host);
    }
    
    private static boolean nativePing(String host) {
        return nativePingImpl("ping", host);
    }
    
    private static boolean nativePing6(String host) {
        return nativePingImpl("ping6", host);
    }
    
    private static boolean nativePingImpl(String cmd, String host) {
        try {
            Process pingProcess
                    = new ProcessBuilder(cmd, "-c", "1", host).start();
            if (!pingProcess.waitFor(TIMEOUT, TimeUnit.MILLISECONDS)) {
                return false;
            }
            return pingProcess.exitValue() == 0;
        } catch (IOException | InterruptedException e) {
            LOGGER.warn(e.getMessage());
            return false;
        }
    }
}
