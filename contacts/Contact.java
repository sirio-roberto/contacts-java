package contacts;

import java.util.Arrays;

public class Contact {
    private String name;
    private String surname;
    private String phone;

    public Contact(String name, String surname, String phone) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public Contact() {}

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (isValidPhoneNumber(phone)) {
            this.phone = phone;
        } else {
            System.out.println("Wrong number format!");
        }
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isBlank()) {
            return false;
        }
        String[] groups = phoneNumber.split("[ -]");
        if (groups.length < 4) {
            return false;
        }
        if (groups[0].matches("^\\+?\\(.+\\)$") && groups[1].matches("^\\(.+\\)$")) {
            return false;
        }
        if (!groups[0].matches("(?i)^\\+?\\(?[a-z0-9]+\\)?$")) {
            return false;
        }
        if (!groups[1].matches("(?i)^\\(?[a-z0-9]{2,}\\)?$")) {
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
