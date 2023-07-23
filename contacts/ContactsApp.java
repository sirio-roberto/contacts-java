package contacts;

import contacts.factories.ContactsFactory;
import contacts.factories.OrganizationFactory;
import contacts.factories.PersonFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsApp {
    private static final Scanner scan = new Scanner(System.in);
    private final List<Contact> contacts;
    private ContactsFactory factory;

    public ContactsApp() {
        contacts = new ArrayList<>();
    }

    public void run() {
        String userAction;

        do {
            System.out.print("Enter action (add, remove, edit, count, info, exit): ");
            userAction = scan.nextLine();

            if ("exit".equals(userAction)) {
                return;
            }
            switch (userAction) {
                case "add" -> createContact();
                case "remove" -> removeContact();
                case "edit" -> editContact();
                case "count" -> countContacts();
                case "info" -> getContactDetail();
            }
            System.out.println();
        } while (true);
    }

    private void getContactDetail() {
        if (contacts.isEmpty()) {
            System.out.println("No records created");
        } else {
            listContacts();

            System.out.print("Enter index to show info: ");
            int userIndex = Integer.parseInt(scan.nextLine());

            System.out.println(contacts.get(userIndex - 1));
        }
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

            if (contactToEdit instanceof Person person) {
                System.out.print("Select a field (name, surname, number): ");
                String chosenField = scan.nextLine();

                System.out.printf("Enter %s: ", chosenField);
                String updatedValue = scan.nextLine();
                // TODO: implement logic on personFactory

            } else if (contactToEdit instanceof Organization org) {
                System.out.print("Select a field (address, number): ");
                String chosenField = scan.nextLine();

                System.out.printf("Enter %s: ", chosenField);
                String updatedValue = scan.nextLine();

                factory = new OrganizationFactory();
                factory.updateField(org, chosenField, updatedValue);
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
            System.out.printf("%d. %s\n", i + 1, contacts.get(i).getName());
        }
    }

    public void createContact() {
        System.out.print("Enter the type (person, organization): ");
        String type = scan.nextLine();

        if ("organization".equals(type)) {
            factory = new OrganizationFactory();
        } else {
            factory = new PersonFactory();
        }

        Contact contact = factory.createContact();
        contacts.add(contact);

        System.out.println("The record added.");
    }
}
