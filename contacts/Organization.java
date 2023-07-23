package contacts;

public class Organization extends Contact {
    private String address;

    public Organization() {}

    public String getAddress() {
        if (address != null && !address.isBlank()) {
            return address;
        } else {
            return getNoDataStr();
        }
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format("""
                Organization name: %s
                Address: %s
                Number: %s
                Time created: %s
                Time last edit: %s""",
                getName(),
                getAddress(),
                getPhone(),
                getTimeCreated().format(dateFormatter),
                getTimeUpdated().format(dateFormatter));
    }
}
