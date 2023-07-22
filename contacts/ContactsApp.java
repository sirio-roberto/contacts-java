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

    public void run() {
        String userAction;

        do {
            System.out.print("Enter action (add, remove, edit, count, list, exit): ");
            userAction = scan.nextLine();

            if ("exit".equals(userAction)) {
                return;
            }
            switch (userAction) {
                case "add" -> createContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "list" -> listContacts();
            }
        } while (true);
    }

    private void countContacts() {
        System.out.printf("The Phone Book has %d records.\n", contacts.size());
    }

    private void editContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to edit!");
        } else {
            listContacts();
            System.out.print("Select a record: ");
            int userIndex = Integer.parseInt(scan.nextLine());
            Contact contactToEdit = contacts.get(userIndex - 1);

            System.out.print("Select a field (name, surname, number): ");
            String chosenField = scan.nextLine();

            System.out.printf("Enter %s: ", chosenField);
            String updatedValue = scan.nextLine();
            switch (chosenField) {
                case "name" -> contactToEdit.setName(updatedValue);
                case "surname" -> contactToEdit.setSurname(updatedValue);
                case "number" -> contactToEdit.setPhone(updatedValue);
            }
            System.out.println("The record updated!");
        }
    }

    private void removeContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
        } else {
            listContacts();
            System.out.print("Select a record: ");
            int userIndex = Integer.parseInt(scan.nextLine());

            contacts.remove(userIndex - 1);
            System.out.println("The record removed!");
        }
    }

    private void listContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contacts.get(i));
        }
    }

    public void createContact() {
        System.out.print("Enter the name: ");
        String name = scan.nextLine();
        System.out.print("Enter the surname: ");
        String surname = scan.nextLine();
        System.out.print("Enter the number: ");
        String phone = scan.nextLine();

        Contact contact = new Contact(name, surname, phone);
        contacts.add(contact);

        System.out.println("The record added.");
    }
}
