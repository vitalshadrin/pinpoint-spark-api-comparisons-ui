package comparison.app.property;

public enum PropertyPath {
    OPTIONS("Config Files/testOptions.properties"),
    GENERAL("Config Files/General/test_groups.properties");

    private final String property;

    PropertyPath(String property) {
        this.property = property;
    }

    public String getPropertyPath() {
        return property;
    }

}
