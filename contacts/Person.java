package contacts;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Person extends Contact{
    private String surname;
    private LocalDate birthDate;
    private Gender gender;


    public Person(String name, String phone, String surname, String birthDateStr, String genderStr) {
        super(name, phone);
        this.surname = surname;
        setBirthDate(birthDateStr);
        setGender(genderStr);
    }

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

    public void setBirthDate(String birthDateStr) {
        if (isValidDate(birthDateStr)) {
            this.birthDate = LocalDate.parse(birthDateStr);
        } else {
            System.out.println("Bad birth date!");
            this.birthDate = null;
        }
    }

    private boolean isValidDate(String birthDateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        try {
            LocalDate.parse(birthDateStr, dateFormatter);
            return true;
        } catch (DateTimeParseException ex) {
            return false;
        }
    }

    public String getGender() {
        if (gender != null) {
            return gender.name();
        } else {
            return getNoDataStr();
        }
    }

    public void setGender(String genderStr) {
        switch (genderStr) {
            case "M", "m" -> this.gender = Gender.M;
            case "F", "f" -> this.gender = Gender.F;
            default -> {
                this.gender = null;
                System.out.println("Bad gender!");
            }
        }
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
