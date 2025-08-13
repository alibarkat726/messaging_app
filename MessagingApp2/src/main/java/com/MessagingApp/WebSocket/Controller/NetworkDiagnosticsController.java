package com.MessagingApp.WebSocket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
public class NetworkDiagnosticsController {

    @Autowired
    private Environment environment;

    @GetMapping("/api/network-info")
    public ResponseEntity<Map<String, Object>> getNetworkInfo() {
        Map<String, Object> networkInfo = new HashMap<>();
        
        try {
            // Get local host information
            InetAddress localhost = InetAddress.getLocalHost();
            networkInfo.put("hostname", localhost.getHostName());
            networkInfo.put("hostAddress", localhost.getHostAddress());
            
            // Get port information
            String port = environment.getProperty("server.port", "8080");
            networkInfo.put("serverPort", port);
            
            // Get application information
            networkInfo.put("applicationName", environment.getProperty("spring.application.name"));
            
            // WebSocket information
            networkInfo.put("websocketEndpoint", "/ws");
            
            // MongoDB connection
            String mongoUri = environment.getProperty("spring.data.mongodb.uri");
            if (mongoUri != null) {
                // Don't expose credentials if present
                if (mongoUri.contains("@")) {
                    mongoUri = mongoUri.replaceAll("//[^@]+@", "//***:***@");
                }
                networkInfo.put("mongoDbUri", mongoUri);
            }
            
            return ResponseEntity.ok(networkInfo);
        } catch (Exception e) {
            networkInfo.put("error", e.getMessage());
            return ResponseEntity.status(500).body(networkInfo);
        }
    }
    
    @GetMapping("/api/health")
    public ResponseEntity<Map<String, String>> getHealth() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("timestamp", String.valueOf(System.currentTimeMillis()));
        
        return ResponseEntity.ok(health);
    }
} 