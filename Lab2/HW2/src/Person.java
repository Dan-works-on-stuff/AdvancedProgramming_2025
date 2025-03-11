import java.time.LocalDate;

abstract class Person {
    abstract String getName();
    abstract LocalDate getDOB();
    protected String name;
    protected LocalDate dob;
}
