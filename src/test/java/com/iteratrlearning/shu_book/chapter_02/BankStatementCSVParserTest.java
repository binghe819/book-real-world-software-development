package com.iteratrlearning.shu_book.chapter_02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BankStatementCSVParserTest {

    BankStatementCSVParser bankStatementCSVParser = new BankStatementCSVParser();

    List<BankTransaction> bankTransactions = new ArrayList<>();

    private static final String RESOURCES = "src/main/resources/";

    @BeforeEach
    void setBankTransactions() {
        DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        bankTransactions.add(new BankTransaction(LocalDate.parse("30-01-2017", DATE_PATTERN),-100,"Deliveroo"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("30-01-2017", DATE_PATTERN),-50,"Tesco"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("01-02-2017", DATE_PATTERN),6000,"Salary"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("02-02-2017", DATE_PATTERN),2000,"Royalties"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("02-02-2017", DATE_PATTERN),-4000,"Rent"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("03-02-2017", DATE_PATTERN),3000,"Tesco"));
        bankTransactions.add(new BankTransaction(LocalDate.parse("05-02-2017", DATE_PATTERN),-30,"Cinema"));
    }

    @Test
    void parseLinesFromCSV() throws IOException {

        final String fileName = "bank-data-simple.csv";
        final Path path = Paths.get(RESOURCES + fileName);
        final List<String> lines = Files.readAllLines(path);
        List<BankTransaction> bankTransactions = bankStatementCSVParser.parseLinesFrom(lines);

        assertThat(bankTransactions).extracting("amount")
                .containsExactly(-100d, -50d, 6000d, 2000d, -4000d, 3000d, -30d);

        assertThat(bankTransactions).extracting("description")
                .containsExactly("Deliveroo", "Tesco", "Salary", "Royalties", "Rent", "Tesco", "Cinema");
    }

    @Test
    void calculateTotalAmount() {

        double total = BankStatementCSVParser.calculateTotalAmount(bankTransactions);

        assertThat(total).isEqualTo(6820d);
    }

    @Test
    void selectInMonth() {

        List<BankTransaction> janBankTransactions = BankStatementCSVParser.selectInMonth(bankTransactions, Month.JANUARY);
        double total = BankStatementCSVParser.calculateTotalAmount(janBankTransactions);

        assertThat(total).isEqualTo(-150d);
    }
}
