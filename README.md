# Contacts App

The Contacts App is a console-based application that allows users to manage contacts. It supports two types of contacts: person and organization. Users can add, list, search, and edit/delete contacts. The application provides a user-friendly menu to interact with the contacts.

## Contacts Factory

The `ContactsFactory` interface defines methods for creating and updating contacts. It includes default methods for setting the name and phone number of a contact, along with validation methods for name and phone number formats.

## Organization Factory

The `OrganizationFactory` class implements the `ContactsFactory` interface to create and update organization contacts. It provides methods for creating an organization contact and updating its fields, such as name, address, and phone number.

## Person Factory

The `PersonFactory` class implements the `ContactsFactory` interface to create and update person contacts. It provides methods for creating a person contact and updating its fields, such as name, surname, birth date, gender, and phone number.

## Contact Class

The `Contact` class is an abstract base class representing a generic contact. It implements the `Serializable` interface, making it serializable to allow for data persistence and storage. Contacts can be of type person or organization and have properties such as name, phone number, and timestamps for creation and last update.

## Main Class

The `Main` class serves as the entry point for the Contacts App. It initializes the application and starts the main execution. The application can be initialized with a file name as a command-line argument, allowing contacts to be loaded from and saved to a file for data persistence.

## Organization Class

The `Organization` class represents a contact of type organization. It extends the `Contact` class and includes an additional property called `address`. It provides methods to set and get the address of the organization and overrides the `toString()` method to provide a formatted string representation of the organization contact.

## Person Class

The `Person` class represents a contact of type person. It extends the `Contact` class and includes additional properties such as `surname`, `birthDate`, and `gender`. It provides methods to set and get the surname, birth date, and gender of the person, and overrides the `toString()` method to provide a formatted string representation of the person contact.

## Serialization Utility

The `SerializationUtil` class provides methods to serialize and deserialize Java objects. It allows users to save contacts to a file and retrieve them later, enabling data persistence and storage for the application.

# How to Run the Application

To run the Contacts App, you can use the provided `Main` class. If you have an existing contacts file, you can specify the file name as a command-line argument to load contacts from the file:

```agsl
java contacts.Main your_contacts_file.txt
```


If you don't have an existing contacts file or want to start with an empty list of contacts, simply run the application without any arguments:

```agsl
java contacts.Main
```


Once the application starts, you can interact with the contacts using the provided menu options. The menu allows you to add new contacts, list existing contacts, search for specific contacts, and perform various operations on them.

Enjoy managing your contacts with the Contacts App!
