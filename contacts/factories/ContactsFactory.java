package contacts.factories;

import contacts.Contact;

public interface ContactsFactory {
    Contact createContact();

    default void setContactName(Contact contact, String name) {
        if (isValidName(name)) {
            contact.setName(name);
        } else {
            contact.setName("");
            printFieldError("name");
        }
    }

    default void setContactPhone(Contact contact, String phone) {
        if (isValidPhoneNumber(phone)) {
            contact.setPhone(phone);
        } else {
            System.out.println("Wrong number format!");
            contact.setPhone("");
        }
    }

    default boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    default void printFieldError(String fieldValue) {
        System.out.printf("Bad %s!\n", fieldValue);
    }

    default boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        String[] groups = phoneNumber.split("[ -]");

        if (groups.length > 1 && groups[0].matches("^\\+?\\(.+\\)$") && groups[1].matches("^\\(.+\\)$")) {
            return false;
        }
        if (!groups[0].matches("(?i)^\\+?((\\([a-z0-9]+\\))|([a-z0-9]+))$")) {
            return false;
        }
        if (groups.length > 1 && !groups[1].matches("(?i)^\\(?[a-z0-9]{2,}\\)?$")) {
            return false;
        }
        for (int i = 2; i < groups.length; i++) {
            if (!groups[i].matches("(?i)^?[a-z0-9]{2,}?$")) {
                return false;
            }
        }
        return true;
    }
}
