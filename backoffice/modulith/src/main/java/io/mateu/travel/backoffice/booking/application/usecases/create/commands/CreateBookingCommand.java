package io.mateu.travel.backoffice.booking.application.usecases.create.commands;

public record CreateBookingCommand(String id, String leadName, String serviceDescription, int salePrice) {
}
