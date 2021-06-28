package comparison.app.file;

public enum FilePath {
    CHANGE_LOG("src/comparison/dictionary/changeLog.txt"),
    INFO("src/comparison/dictionary/info.txt"),
    ICON("/comparison/icons/ic_launcher.png");

    private final String file;

    FilePath(String property) {
        this.file = property;
    }

    public String getFilePath() {
        return file;
    }

}
