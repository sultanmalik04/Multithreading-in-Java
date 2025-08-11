package com.sultan.async_communication;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketBookingSystem {

    private static final ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        bookTicketAsync("Movie123", "User456");
        System.out.println("Main thread is free to handle other requests...");
    }

    public static void bookTicketAsync(String movieId, String userId) {
        CompletableFuture<Boolean> seatAvailabilityFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay(1500); // Simulate 1.5s delay
            System.out.println("Seat availability checked.");
            return true; // Assume seats are available
        }, executor);

        CompletableFuture<Boolean> paymentGatewayFuture = CompletableFuture.supplyAsync(() -> {
            simulateDelay(1200); // Simulate 1.2s delay
            System.out.println("Payment gateway status checked.");
            return true; // Assume payment gateway is up
        }, executor);

        // Combine both results
        seatAvailabilityFuture.thenCombine(paymentGatewayFuture, (seatsAvailable, paymentOk) -> {
            if (seatsAvailable && paymentOk) {
                System.out.println("Booking confirmed for " + userId + " on " + movieId);
            } else {
                System.out.println("Booking failed due to unavailability or payment issue.");
            }
            return null;
        });
    }

    private static void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }
}