package contacts.factories;

import contacts.Contact;
import contacts.Organization;

import java.util.Scanner;

public class OrganizationFactory implements ContactsFactory {
    private final Scanner scan = new Scanner(System.in);

    @Override
    public Contact createContact() {
        Organization org = new Organization();

        System.out.print("Enter the organization name: ");
        String name = scan.nextLine();
        if (isValidName(name)) {
            org.setName(name);
        } else {
            org.setName("");
            printFieldError("name");
        }

        System.out.print("Enter the address: ");
        String address = scan.nextLine();
        if (isValidName(address)) {
            org.setAddress(address);
        } else {
            org.setAddress("");
            printFieldError("address");
        }

        System.out.print("Enter the number: ");
        String phone = scan.nextLine();
        if (isValidPhoneNumber(phone)) {
            org.setPhone(phone);
        } else {
            System.out.println("Wrong number format!");
            org.setPhone("");
        }

        return org;
    }
}
