package com.shreeaircons.service;

import com.shreeaircons.entity.Booking;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationService {
    
    @Value("${admin.whatsapp.number}")
    private String adminPhoneNumber;
    
    @Value("${admin.email}")
    private String adminEmail;
    
    private static final String WHATSAPP_API_URL = "https://api.whatsapp.com/send";
    
    public void sendBookingNotification(Booking booking) {
        String message = formatBookingMessage(booking);
        sendWhatsAppMessage(adminPhoneNumber, message);
    }
    
    public void sendStatusUpdateNotification(Booking booking) {
        String message = formatStatusUpdateMessage(booking);
        sendWhatsAppMessage(booking.getPhoneNumber(), message);
    }
    
    private void sendWhatsAppMessage(String phoneNumber, String message) {
        try {
            // Note: This is a placeholder for WhatsApp Business API integration
            // In production, you would use official WhatsApp Business API or a service like Twilio
            // For now, this demonstrates the method structure
            
            Map<String, Object> payload = new HashMap<>();
            payload.put("phone", phoneNumber);
            payload.put("message", message);
            
            // Implementation for actual WhatsApp API integration would go here
            System.out.println("WhatsApp message sent to: " + phoneNumber);
            System.out.println("Message: " + message);
            
        } catch (Exception e) {
            System.err.println("Failed to send WhatsApp message: " + e.getMessage());
        }
    }
    
    private String formatBookingMessage(Booking booking) {
        return String.format(
            "New Booking Request!\n\n" +
            "Name: %s\n" +
            "Phone: %s\n" +
            "Service: %s\n" +
            "Address: %s\n" +
            "Problem: %s\n\n" +
            "Please confirm service date and time.",
            booking.getCustomerName(),
            booking.getPhoneNumber(),
            booking.getRepairType(),
            booking.getAddress(),
            booking.getProblemDescription()
        );
    }
    
    private String formatStatusUpdateMessage(Booking booking) {
        return String.format(
            "Hello %s,\n\n" +
            "Your booking status: %s\n" +
            "Service Date: %s\n" +
            "Service Time: %s\n\n" +
            "Thank you for choosing Shree Air Cons!",
            booking.getCustomerName(),
            booking.getStatus(),
            booking.getServiceDate(),
            booking.getServiceTime()
        );
    }
}
