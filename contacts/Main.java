package contacts;

public class Main {
    public static void main(String[] args) {
        ContactsApp app;
        if (args.length > 0) {
            app = new ContactsApp(args[0]);
        } else {
            app = new ContactsApp();
        }
        app.run();
    }
}
