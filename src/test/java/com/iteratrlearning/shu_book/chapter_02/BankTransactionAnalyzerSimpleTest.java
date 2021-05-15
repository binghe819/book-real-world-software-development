package com.iteratrlearning.shu_book.chapter_02;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BankTransactionAnalyzerSimpleTest {

    private static final String RESOURCES = "src/main/resources/";

    BankTransactionAnalyzerSimple bankTransactionAnalyzerSimple = new BankTransactionAnalyzerSimple();

    @Test
    void basic() throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        for (final String line : lines) {
            final String[] columns = line.split(",");
            final double amount = Double.parseDouble(columns[1]);
            total += amount;
        }

        assertThat(total).isEqualTo(6820d);
    }

    @Test
    void month() throws IOException {
        final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
        final List<String> lines = Files.readAllLines(path);
        double total = 0d;
        final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        for(final String line: lines) {
            final String[] columns = line.split(",");
            final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
            if(date.getMonth() == Month.JANUARY) {
                final double amount = Double.parseDouble(columns[1]);
                total += amount;
            }
        }

        assertThat(total).isEqualTo(-150d);
    }

    @Test
    void getTotalByFile() throws IOException {
        double total = bankTransactionAnalyzerSimple.getTotalByCsvFile("bank-data-simple.csv", Month.JANUARY);

        assertThat(total).isEqualTo(-150d);
    }
}