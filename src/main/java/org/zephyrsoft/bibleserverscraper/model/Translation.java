package org.zephyrsoft.bibleserverscraper.model;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public enum Translation {

	ELB("ELB", "Elberfelder", BookChapter::getNameGerman),
	LUT("LUT", "Luther", BookChapter::getNameGerman),
	NLB("NLB", "Neues Leben", BookChapter::getNameGerman),
	KJV("KJV", "King James", BookChapter::getNameEnglish);

	private String abbreviation;
	private String name;
	private Function<BookChapter, String> nameGetter;

	private Translation(String abbreviation, String name, Function<BookChapter, String> nameGetter) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.nameGetter = nameGetter;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public String getName() {
		return name;
	}

	public String nameOf(BookChapter bookChapter) {
		return nameGetter.apply(bookChapter);
	}

	public String fileNameOf(BookChapter bookChapter) {
		return abbreviation + "-" + bookChapter.getBook().getOrdinal() + "-" + bookChapter.getNameGerman() + ".txt";
	}

	public static Stream<Translation> stream() {
		return Stream.of(values());
	}

	public static void forEach(Consumer<Translation> action) {
		stream().forEach(action);
	}
}
