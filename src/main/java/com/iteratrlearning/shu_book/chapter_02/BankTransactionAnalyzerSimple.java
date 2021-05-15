package com.iteratrlearning.shu_book.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple {
    private static final String RESOURCES = "src/main/resources/";

    public double getTotalByCsvFile(String fileName, Month month) throws IOException {
        final Path path = Paths.get(RESOURCES + fileName);

        final List<String> lines = Files.readAllLines(path);

        return getTotalByMonthData(lines, month);
    }

    private double getTotalByMonthData(List<String> lines, Month month) {
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if (date.getMonth() == month) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }
        return total;
    }
}
