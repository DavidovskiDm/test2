public class File {
    private String extension;
    private String filename;
    private String content;
    private int size;

    public File(String filename, String content) {
        this.filename = filename;
        this.content = content;
    }

    // демонстрация того, что не стоит создавать один объект на все тесты
    public String getExtension() {
        content+='a';
        return filename.split("\\.")[filename.split("\\.").length - 1];
    }

    //баг
    public int getSize() {
        return content.length() / 2 * 2;
    }

    public String getContent() {
        return content;
    }

    public String getFilename() {
        return filename;
    }


}
