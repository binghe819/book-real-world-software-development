package com.iteratrlearning.shu_book.chapter_02;

import java.util.List;

public interface BankStatementParser {

    List<BankTransaction> parseLinesFrom(final List<String> lines);
}
