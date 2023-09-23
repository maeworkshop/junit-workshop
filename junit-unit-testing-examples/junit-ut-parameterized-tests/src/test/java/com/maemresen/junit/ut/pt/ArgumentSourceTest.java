package com.maemresen.junit.ut.pt;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArgumentSourceTest {

	private static Stream<Arguments> nonBlankStringsProvider() {
		return Stream.of(
			Arguments.of("method-a"),
			Arguments.of("method-b"),
			Arguments.of("method-c")
		);
	}

	static class NonBlankRandomStringArgumentProvider implements ArgumentsProvider {

		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
			return Stream.of(
				Arguments.of("argumentProvider-a"),
				Arguments.of("argumentProvider-b"),
				Arguments.of("argumentProvider-c")
			);
		}
	}

	static class LocalDateConverter implements ArgumentConverter {
		@Override
		public Object convert(Object source, ParameterContext context) throws ArgumentConversionException {
			if(source instanceof String dateString) {
				return LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
			}
			throw new ArgumentConversionException(source + " is not a string");
		}
	}

	private static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}

	@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@ArgumentsSource(NonBlankRandomStringArgumentProvider.class)
	@interface NonBlankRandomStringSource {
	}

	@ParameterizedTest
	@NullSource
	void nullSourceTest(String str) {
		assertTrue(isBlank(str));
	}

	@ParameterizedTest
	@EmptySource
	void emptySourceTest(String str) {
		assertTrue(isBlank(str));
	}

	@ParameterizedTest
	@NullAndEmptySource
	void nullAndEmptySourceTest(String str) {
		assertTrue(isBlank(str));
	}

	@ParameterizedTest
	@MethodSource("nonBlankStringsProvider")
	void methodSourceTest(String str) {
		assertFalse(isBlank(str));
	}

	@ParameterizedTest
	@ArgumentsSource(NonBlankRandomStringArgumentProvider.class)
	void argumentSourceTest(String str) {
		assertFalse(isBlank(str));
	}

	@ParameterizedTest
	@NonBlankRandomStringSource
	void customAnnotationSourceTest(String str) {
		assertFalse(isBlank(str));
	}

	@ParameterizedTest(name = "{0}.getValue() must be between 1 and 12")
	@EnumSource(Month.class)
	void enumSourceTest(Month month) {
		final var monthNumber = month.getValue();
		assertAll(
			() -> assertTrue(monthNumber >= 1),
			() -> assertTrue(monthNumber <= 12));
	}

	@ParameterizedTest(name = "{0} must have 30 days")
	@EnumSource(value = Month.class, names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER"})
	void enumSourceOnlyIncludedTest(Month month) {
		assertEquals(30, month.length(false));
	}

	@ParameterizedTest(name = "{0} must have 31 days")
	@EnumSource(
		value = Month.class,
		names = {"APRIL", "JUNE", "SEPTEMBER", "NOVEMBER", "FEBRUARY"},
		mode = EnumSource.Mode.EXCLUDE
	)
	void enumSourceWithoutExcludedTest(Month month) {
		assertEquals(31, month.length(false));
	}

	@ParameterizedTest(name = "{0} must ends with BER")
	@EnumSource(
		value = Month.class,
		names = ".+BER",
		mode = EnumSource.Mode.MATCH_ANY
	)
	void enumSourceOnlyMatchingNameTest(Month month) {
		assertTrue(month.name().endsWith("BER"));
	}

	@ParameterizedTest(name = "getYear({0}) should return {1}")
	@CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
	void csvSourceTest(@JavaTimeConversionPattern("yyyy/MM/dd") LocalDate date, int expected) {
		assertEquals(expected, date.getYear());
	}

	@ParameterizedTest(name = "getYear({0}) should return {1}")
	@CsvSource(value = {"2012/12/25:2012", "2013/12/25:2013", "2014/12/25:2014"}, delimiter = ':')
	void csvSourceTestWithCustomConverter(@ConvertWith(LocalDateConverter.class) LocalDate date, int expected) {
		assertEquals(expected, date.getYear());
	}
}