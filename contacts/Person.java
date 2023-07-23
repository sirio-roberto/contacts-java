package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person extends Contact{
    private String surname;
    private LocalDate birthDate;
    private Gender gender;


    public Person() {}

    @Override
    public String getName() {
        if (surname != null && !surname.isBlank()) {
            return super.getName() + " " + this.surname;
        } else {
            return super.getName();
        }
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    private String getBirthDate() {
        if (birthDate != null) {
            return birthDate.toString();
        } else {
            return getNoDataStr();
        }
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        if (gender != null) {
            return gender.name();
        } else {
            return getNoDataStr();
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public enum Gender {
        M, F
    }

    @Override
    public String toString() {
        return String.format("""
                Name: %s
                Surname: %s
                Birth date: %s
                Gender: %s
                Number: %s
                Time created: %s
                Time last edit: %s""",
                super.getName(),
                surname,
                getBirthDate(),
                getGender(),
                getPhone(),
                getTimeCreated(),
                getTimeUpdated());
    }
}
