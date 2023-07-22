package contacts;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    private static final Scanner scan = new Scanner(System.in);
    private List<Contact> contacts;

    public ContactsApp(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public ContactsApp() {
        contacts = new ArrayList<>();
    }

    public void createContact() {
        System.out.println("Enter the name of the person:");
        String name = scan.nextLine();
        System.out.println("Enter the surname of the person:");
        String surname = scan.nextLine();
        System.out.println("Enter the number:");
        String phone = scan.nextLine();

        Contact contact = new Contact(name, surname, phone);
        contacts.add(contact);

        System.out.println("\nA record created!");
        System.out.println("A Phone Book with a single record created!");
    }
}
