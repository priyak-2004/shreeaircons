package com.shreeaircons.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

/**
 * Enhanced WhatsApp Notification Service
 * Integrates with WhatsApp Business API
 * Supports various message types and templates
 */
@Component
public class WhatsAppUtil {
    
    @Value("${admin.whatsapp.number}")
    private String adminPhoneNumber;
    
    @Value("${app.name:Shree Air Cons}")
    private String appName;
    
    /**
     * Format phone number for WhatsApp API
     * Converts 10-digit number to international format
     */
    public String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return null;
        }
        // Remove any non-digit characters
        String cleaned = phoneNumber.replaceAll("\\D", "");
        // Add India country code if not present
        if (cleaned.length() == 10) {
            return "91" + cleaned;
        } else if (cleaned.startsWith("91")) {
            return cleaned;
        }
        return cleaned;
    }
    
    /**
     * Generate booking confirmation message
     */
    public String generateBookingMessage(Map<String, String> bookingData) {
        return String.format(
            "📋 *New Booking Request*\n\n" +
            "🧑 Name: %s\n" +
            "📱 Phone: %s\n" +
            "🔧 Service: %s\n" +
            "📍 Address: %s\n" +
            "📝 Issue: %s\n\n" +
            "Please confirm service date and time.\n" +
            "Thank you!",
            bookingData.get("name"),
            bookingData.get("phone"),
            bookingData.get("service"),
            bookingData.get("address"),
            bookingData.get("issue")
        );
    }
    
    /**
     * Generate service confirmation message for customer
     */
    public String generateServiceConfirmationMessage(Map<String, String> serviceData) {
        return String.format(
            "✅ *Service Confirmed*\n\n" +
            "Hello %s,\n\n" +
            "Your appointment is confirmed!\n" +
            "📅 Date: %s\n" +
            "⏰ Time: %s\n" +
            "🔧 Service: %s\n\n" +
            "Our technician will arrive at your address.\n" +
            "Thank you for choosing %s!",
            serviceData.get("customerName"),
            serviceData.get("serviceDate"),
            serviceData.get("serviceTime"),
            serviceData.get("serviceType"),
            appName
        );
    }
    
    /**
     * Generate service completion message
     */
    public String generateServiceCompletionMessage(String customerName) {
        return String.format(
            "🎉 *Service Complete*\n\n" +
            "Hello %s,\n\n" +
            "Your service has been completed!\n" +
            "We hope you're satisfied with our service.\n\n" +
            "Please share your feedback and rate us on our website.\n" +
            "Thank you for choosing %s!",
            customerName, appName
        );
    }
    
    /**
     * Generate reminder message
     */
    public String generateReminderMessage(Map<String, String> appointmentData) {
        return String.format(
            "⏰ *Service Reminder*\n\n" +
            "Hello %s,\n\n" +
            "This is a reminder for your scheduled service:\n" +
            "📅 Date: %s\n" +
            "⏰ Time: %s\n\n" +
            "Our technician will arrive at your address.\n" +
            "Please ensure someone is available.",
            appointmentData.get("customerName"),
            appointmentData.get("serviceDate"),
            appointmentData.get("serviceTime")
        );
    }
    
    public String getAdminPhoneNumber() {
        return formatPhoneNumber(adminPhoneNumber);
    }
}
