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
        setContactName(org, name);

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
        setContactPhone(org, phone);

        return org;
    }
}
