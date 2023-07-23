package contacts;

import java.time.LocalDateTime;

abstract class Contact {
    private String name;
    private String phone;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    protected Contact(String name, String phone) {
        this.name = name;
        setPhone(phone);
        timeCreated = LocalDateTime.now();
        timeUpdated = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        if (hasNumber()) {
            return phone;
        } else {
            return getNoDataStr();
        }
    }

    protected String getNoDataStr() {
        return "[no data]";
    }

    public void setPhone(String phone) {
        if (isValidPhoneNumber(phone)) {
            this.phone = phone;
        } else {
            System.out.println("Wrong number format!");
            this.phone = "";
        }
    }

    public LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeUpdated() {
        return timeUpdated;
    }

    public void setTimeUpdated(LocalDateTime timeUpdated) {
        this.timeUpdated = timeUpdated;
    }

    private boolean isValidPhoneNumber(String phoneNumber) {
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

    public boolean hasNumber() {
        return phone != null && !phone.isBlank();
    }
}
