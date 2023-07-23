package contacts.factories;

import contacts.Contact;
import contacts.Person;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PersonFactory implements ContactsFactory {
    private final Scanner scan = new Scanner(System.in);

    @Override
    public Contact createContact() {
        Person person = new Person();

        System.out.print("Enter the name: ");
        String name = scan.nextLine();
        setContactName(person, name);

        System.out.print("Enter the surname: ");
        String surname = scan.nextLine();
        setPersonSurname(person, surname);

        System.out.print("Enter the birth date: ");
        String birthDateStr = scan.nextLine();
        setPersonBirthDate(person, birthDateStr);

        System.out.print("Enter the gender (M, F): ");
        String genderStr = scan.nextLine();
        setPersonGender(person, genderStr);

        System.out.print("Enter the number: ");
        String phone = scan.nextLine();
        setContactPhone(person, phone);

        return person;
    }

    private void setPersonSurname(Person person, String surname) {
        if (isValidName(surname)) {
            person.setSurname(surname);
        } else {
            person.setSurname("");
            printFieldError("surname");
        }
    }

    private void setPersonBirthDate(Person person, String birthDateStr) {
        if (isValidDate(birthDateStr)) {
            person.setBirthDate(LocalDate.parse(birthDateStr));
        } else {
            person.setBirthDate(null);
            printFieldError("birth date");
        }
    }

    private void setPersonGender(Person person, String genderStr) {
        switch (genderStr) {
            case "M", "m" -> person.setGender(Person.Gender.M);
            case "F", "f" -> person.setGender(Person.Gender.F);
            default -> {
                printFieldError("gender");
                person.setGender(null);
            }
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

    @Override
    public void updateField(Contact contact, String fieldName, String fieldValue) {
        if (contact instanceof Person person) {
            switch (fieldName) {
                case "name" -> setContactName(person, fieldValue);
                case "surname" -> setPersonSurname(person, fieldValue);
                case "birth" -> setPersonBirthDate(person, fieldValue);
                case "gender" -> setPersonGender(person, fieldValue);
                case "number" -> setContactPhone(person, fieldValue);
            }
            contact.setTimeUpdated(LocalDateTime.now());
        }
    }
}
