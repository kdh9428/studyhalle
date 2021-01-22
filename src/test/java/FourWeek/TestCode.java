package FourWeek;

import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIf;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.CountDownLatch;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.condition.OS.*;

//@IndicativeSentencesGeneration(separator = " -> ", generator = DisplayNameGenerator.ReplaceUnderscores.class)
//@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class TestCode {

    @Test
    @DisplayName("테스트 코드 1")
    void test_1() {
        System.out.println("Test1");
    }

    @Test
    void test_2() {
        System.out.println("Test2");
    }

    @BeforeAll
    static void before_All() {
        System.out.println("BeforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @AfterEach
    void AfterEach() {
        System.out.println("AfterEach");
    }

    @Test
    @Disabled
    void disabled() {

        if_it_is_negative(-1);
        System.out.println("Disabled");
    }

    @DisplayName("윤년 계산에서 연도의 음수 값은 지원되지 않습니다.")
    @ParameterizedTest(name = "For example, year {0} is not supported.")
    @ValueSource(ints = {-1, -4})
    void if_it_is_negative(int year) {
        System.out.println(year);
    }


    @Test
    @DisplayName("정상적인 나이")
    void testAge() {
        Person person = new Person();
        person.setAge(10);
        assertEquals(10, person.getAge());
    }

    @Test
    @DisplayName("나이가 150 이상일 경우")
    void testAge1() {
        IllegalArgumentException ageThrows = assertThrows(IllegalArgumentException.class, () -> new Person().setAge(160));
        assertEquals("잘못된 나이 입니다.", ageThrows.getMessage());
    }

    @Test
    @DisplayName("나이가 음수일 경우")
    void testAge2() {
        IllegalArgumentException ageThrows = assertThrows(IllegalArgumentException.class, () -> new Person().setAge(-10));
        assertEquals("잘못된 나이 입니다.", ageThrows.getMessage());
    }

    @ParameterizedTest(name = "나이가 {0}일 경우 에러 발생")
    @ValueSource(ints = {-10, 160})
    void palindromes(int age) {
        IllegalArgumentException ageThrows = assertThrows(IllegalArgumentException.class, () -> new Person().setAge(age));
        assertEquals("잘못된 나이 입니다.", ageThrows.getMessage());
    }

    @Test
    @ParameterizedTest
    @NullSource
    void tesTAssertThat(String a) {
        assertTrue(a == null);

    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = {" ", "   ", "\t", "\n"})
    void nullEmptyAndBlankStrings(String text) {
        assertTrue(text == null || text.trim().isEmpty(), () -> "테스트");
//        assertTrue(text);


    }

    @Test
    void testStartSwitch() {
        Person person = new Person();
        String firstName = person.getFirstName();
        System.out.println(firstName);
        System.out.println(person.getLastName());

        String a = null;
        assertNull(a);
//        assertNotNull(a);

    }

    @Test
    @DisplayName("assertions 그룹화 테스트")
    void groupedAssertions() {
        Person person = new Person();
        assertAll("test1",
                () -> {
                    assertNotNull(person);


                    assertAll("First name",
                            () -> assertTrue(person.getFirstName().startsWith("J")),
                            () -> assertTrue(person.getFirstName().endsWith("e")));

                }, // 첫 번째 그룹 테스트

                () -> {
                    String secondName = person.getLastName();
                    assertAll("Last name",
                            () -> assertTrue(secondName.startsWith("D")),
                            () -> assertTrue(secondName.endsWith("e")));

                } // 첫 번째 그룹 테스트
        );
    }

    @Test
    @DisplayName("테스트가 10밀리초 이상일 경우 실패")
    void testTimeOut() {


        assertTimeout(ofMillis(10), () -> {
            CountDownLatch countDownLatch = new CountDownLatch(1);
        });
    }

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        // The following assertion fails with an error message similar to:
        // execution timed out after 10 ms
        assertTimeoutPreemptively(ofMillis(10), () -> {
            // Simulate task that takes more than 10 ms.
            new CountDownLatch(1).await();
        });
    }


    @Test
    @EnabledOnOs(MAC)
    void onlyOnMacOs() {
        // ...
    }

    @TestOnMac
    void testOnMac() {
        // ...
    }

    @Test
    @EnabledOnOs({LINUX, MAC})
    void onLinuxOrMac() {
        // ...
    }

    @Test
    @DisabledOnOs(WINDOWS)
    void notOnWindows() {
        // ...
    }

    @Test
    @TestOnWin
    void OnWindows() {
        // ...
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(MAC)
    @interface TestOnMac {
    }

    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    @Test
    @EnabledOnOs(WINDOWS)
    @interface TestOnWin {
    }


    @ParameterizedTest(name = "{index} {displayName} message = {0}")
    @ValueSource(ints = {10, 20, 30})
    void parameterizedTest(@ConvertWith(PersonConverter.class) Person person) {
        System.out.println(person.getAge());
    }

    @ParameterizedTest(name = "[{index}] 나이 : {0}, 이름 : {1}")
    @CsvSource({"10, Aaron", "20, 'Aaliyah Smith'"})
    void cvsSourceTest(@AggregateWith(PersonAggregator.class) Person person) {
        System.out.println(person);
    }

    static class PersonAggregator implements ArgumentsAggregator {

        @Override
        public Object aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) throws ArgumentsAggregationException {
//            assertEquals(Person.class, argumentsAccessor, "Person 객체만 가능합니다.");
            Person person = new Person();
            person.setAge(argumentsAccessor.getInteger(0));
            person.setName(argumentsAccessor.getString(1));
            return person;
        }
    }
}

class Person {

    private int age;
    private String name = "Jane Doe";

    public void setAge(int age) {
        if (age < 0 || age > 150) throw new IllegalArgumentException("잘못된 나이 입니다.");
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getFirstName() {
        String first = name.substring(0, name.indexOf(" "));
        return first;
    }

    public String getLastName() {
        String second = name.substring(name.indexOf(" ") + 1);
        return second;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
