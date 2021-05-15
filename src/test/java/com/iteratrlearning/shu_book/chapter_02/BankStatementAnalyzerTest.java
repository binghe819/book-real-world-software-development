package com.iteratrlearning.shu_book.chapter_02;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class BankStatementAnalyzerTest {

    BankStatementAnalyzer bankStatementAnalyzer = new BankStatementAnalyzer();

    @Test
    void analyze() throws IOException {
        BankStatementParser bankStatementParser = new BankStatementCSVParser();
        bankStatementAnalyzer.analyze("bank-data-simple.csv", bankStatementParser);
    }

}