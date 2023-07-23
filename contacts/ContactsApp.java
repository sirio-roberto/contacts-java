package contacts;

import contacts.factories.ContactsFactory;
import contacts.factories.OrganizationFactory;
import contacts.factories.PersonFactory;
import contacts.util.SerializationUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactsApp {
    private static final Scanner scan = new Scanner(System.in);
    private String fileName;
    private List<Contact> contacts;
    private ContactsFactory factory;

    public ContactsApp() {
        contacts = new ArrayList<>();
    }

    public ContactsApp(String fileName) {
        this.fileName = fileName;
        try {
            Contact[] contactsArray = (Contact[]) SerializationUtil.deserialize(fileName);
            contacts = new ArrayList<>(Arrays.stream(contactsArray).toList());
        } catch (IOException | ClassNotFoundException ex) {
            contacts = new ArrayList<>();
        }
    }

    public void run() {
        System.out.printf("open %s\n\n", fileName);

        String userAction;

        do {
            System.out.print("[menu] Enter action (add, list, search, count, exit): ");
            userAction = scan.nextLine();

            if ("exit".equals(userAction)) {
                return;
            }
            switch (userAction) {
                case "add" -> createContact();
                case "list" -> listMenu();
                case "search" -> searchMenu();
                case "count" -> countContacts();
            }
            System.out.println();
        } while (true);
    }

    private void listMenu() {
        listContacts();
        System.out.println();

        System.out.print("[list] Enter action ([number], back): ");
        String menuAction = scan.nextLine();

        if (!"back".equals(menuAction)) {
            recordMenu(contacts, menuAction);
        }
    }

    private void searchMenu() {
        System.out.print("Enter search query: ");
        String query = scan.nextLine();
        List<Contact> searchResult = runQuery(query);

        System.out.printf("Found %s results:\n", searchResult.size());
        listContacts(searchResult);
        System.out.println();

        String searchAction;

        System.out.print("[search] Enter action ([number], back, again): ");
        searchAction = scan.nextLine();

        if ("back".equals(searchAction)) {
            return;
        }
        if ("again".equals(searchAction)) {
            searchMenu();
        } else {
            recordMenu(searchResult, searchAction);
        }
    }

    private void recordMenu(List<Contact> searchResult, String indexStr) {
        int index = Integer.parseInt(indexStr) - 1;
        Contact selectedContact = searchResult.get(index);
        System.out.println(selectedContact);
        System.out.println();

        String recordAction;
        System.out.print("[record] Enter action (edit, delete, menu): ");
        recordAction = scan.nextLine();

        if ("menu".equals(recordAction)) {
            return;
        }
        if ("edit".equals(recordAction)) {
            editContact(selectedContact);
            recordMenu(searchResult, indexStr);
        } else {
            removeContact(selectedContact);
        }
    }

    private List<Contact> runQuery(String query) {
        List<Contact> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        for (Contact contact: contacts) {
            Matcher matcher = pattern.matcher(contact.toString());
            if (matcher.find()) {
                result.add(contact);
            }
        }
        return result;
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
                String fieldName = scan.nextLine();

                System.out.printf("Enter %s: ", fieldName);
                String updatedValue = scan.nextLine();

                factory = new PersonFactory();
                factory.updateField(person, fieldName, updatedValue);

            } else if (contactToEdit instanceof Organization org) {
                System.out.print("Select a field (name, address, number): ");
                String fieldName = scan.nextLine();

                System.out.printf("Enter %s: ", fieldName);
                String updatedValue = scan.nextLine();

                factory = new OrganizationFactory();
                factory.updateField(org, fieldName, updatedValue);
            }

            saveToFile();
            System.out.println("The record updated!");
        }
    }

    private void editContact(Contact contact) {
        if (contact instanceof Person person) {
            System.out.print("Select a field (name, surname, number): ");
            String fieldName = scan.nextLine();

            System.out.printf("Enter %s: ", fieldName);
            String updatedValue = scan.nextLine();

            factory = new PersonFactory();
            factory.updateField(person, fieldName, updatedValue);

        } else if (contact instanceof Organization org) {
            System.out.print("Select a field (name, address, number): ");
            String fieldName = scan.nextLine();

            System.out.printf("Enter %s: ", fieldName);
            String updatedValue = scan.nextLine();

            factory = new OrganizationFactory();
            factory.updateField(org, fieldName, updatedValue);
        }

        saveToFile();
        System.out.println("Saved");
    }

    private void removeContact() {
        if (contacts.isEmpty()) {
            System.out.println("No records to remove!");
        } else {
            listContacts();
            System.out.print("Select a record: ");
            int userIndex = Integer.parseInt(scan.nextLine());

            contacts.remove(userIndex - 1);
            saveToFile();

            System.out.println("The record removed!");
        }
    }

    private void removeContact(Contact contact) {
        contacts.remove(contact);
        saveToFile();

        System.out.println("The record removed!");
    }

    private void listContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, contacts.get(i).getName());
        }
    }

    private void listContacts(List<Contact> contacts) {
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
        saveToFile();

        System.out.println("The record added.");
    }

    private void saveToFile() {
        Contact[] contactsArray = contacts.toArray(Contact[]::new);
        try {
            SerializationUtil.serialize(contactsArray, fileName);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
