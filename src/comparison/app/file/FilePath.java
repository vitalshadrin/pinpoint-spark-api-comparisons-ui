package comparison.app.file;

public enum FilePath {
    ICON("/comparison/icons/ic_launcher.png");

    private final String file;

    FilePath(String property) {
        this.file = property;
    }

    public String getFilePath() {
        return file;
    }

}
