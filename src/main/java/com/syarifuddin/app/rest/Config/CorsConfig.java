package com.syarifuddin.app.rest.Config;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
	String ipAddress;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
    	getIp();
			registry.addMapping("/**")
			        .allowedOrigins("http://"+ipAddress)
			        .allowedMethods("GET", "POST", "PUT", "DELETE")
			        .allowedHeaders("Content-Type")
			        .allowCredentials(true)
			        .allowPrivateNetwork(true)
			        .maxAge(3600);
        
    }
    
    private void getIp() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                if (!networkInterface.isLoopback() && networkInterface.isUp()) {
                    Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress address = addresses.nextElement();
                        if (address instanceof Inet4Address) {
                            System.out.println("IP Address: " + address.getHostAddress());
                            ipAddress = address.getHostAddress();
                        }
                    }
                }
            }
        } catch (SocketException e) {
            System.out.println("Error getting network interface information: " + e.getMessage());
        }
    }
}