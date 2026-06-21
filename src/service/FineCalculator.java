package service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {

    private static final double FINE_PER_DAY = 5.0;

    public double calculateFine(LocalDate dueDate, LocalDate returnDate) {
        if (dueDate == null || returnDate == null) {
            return 0.0;
        }

        if (!returnDate.isAfter(dueDate)) {
            return 0.0;
        }

        long overdueDays = ChronoUnit.DAYS.between(dueDate, returnDate);
        return overdueDays * FINE_PER_DAY;
    }
}