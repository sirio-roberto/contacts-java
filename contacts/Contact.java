package contacts;

import java.time.LocalDateTime;

public abstract class Contact {
    private String name;
    private String phone;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeUpdated;

    protected Contact() {
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

    public void setPhone(String phone) {
        this.phone = phone;
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

    public boolean hasNumber() {
        return phone != null && !phone.isBlank();
    }

    protected String getNoDataStr() {
        return "[no data]";
    }
}
