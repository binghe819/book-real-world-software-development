package com.iteratrlearning.shu_book.chapter_02;

import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BankStatementProcessorTest {

    private List<BankTransaction> bankTransactions = new ArrayList<>();

    BankStatementProcessor bankStatementProcessor;

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

        bankStatementProcessor = new BankStatementProcessor(bankTransactions);
    }

    @Test
    void calculateTotalAmount() {
        double total = bankStatementProcessor.calculateTotalAmount();

        assertThat(total).isEqualTo(6820d);
    }

    @Test
    void calculateTotalInMonth() {
        double total = bankStatementProcessor.calculateTotalInMonth(Month.JANUARY);

        assertThat(total).isEqualTo(-150d);
    }

    @Test
    void calculateTotalForCategory() {
        double total = bankStatementProcessor.calculateTotalForCategory("Royalties");

        assertThat(total).isEqualTo(2000);
    }

}