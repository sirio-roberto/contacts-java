package contacts.factories;

import contacts.Contact;
import contacts.Person;

import java.time.LocalDate;
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
        if (isValidName(name)) {
            person.setName(name);
        } else {
            person.setName("");
            printFieldError("name");
        }

        System.out.print("Enter the surname: ");
        String surname = scan.nextLine();
        if (isValidName(surname)) {
            person.setSurname(surname);
        } else {
            person.setSurname("");
            printFieldError("surname");
        }

        System.out.print("Enter the birth date: ");
        String birthDateStr = scan.nextLine();
        if (isValidDate(birthDateStr)) {
            person.setBirthDate(LocalDate.parse(birthDateStr));
        } else {
            person.setBirthDate(null);
            printFieldError("birth date");
        }

        System.out.print("Enter the gender (M, F): ");
        String genderStr = scan.nextLine();
        person.setGender(getGenderFromString(genderStr));

        System.out.print("Enter the number: ");
        String phone = scan.nextLine();
        if (isValidPhoneNumber(phone)) {
            person.setPhone(phone);
        } else {
            System.out.println("Wrong number format!");
            person.setPhone("");
        }

        return person;
    }

    @Override
    public void updateField(Contact contact, String fieldName, String fieldValue) {

    }

    private Person.Gender getGenderFromString(String genderStr) {
        switch (genderStr) {
            case "M", "m" -> {
                return Person.Gender.M;
            }
            case "F", "f" -> {
                return Person.Gender.F;
            }
            default -> {
                printFieldError("gender");
                return null;
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
}
