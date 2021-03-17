package comparison.app.property;

public enum PropertyPath {
    DICTIONARY("src/comparison/resources/appText.properties"),
    OPTIONS("Config Files/testOptions.properties");

    private final String property;

    PropertyPath(String property) {
        this.property = property;
    }

    public String getPropertyPath() {
        return property;
    }

}
