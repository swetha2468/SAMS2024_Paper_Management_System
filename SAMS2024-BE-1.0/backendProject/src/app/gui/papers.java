package app.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class papers
{

    SimpleIntegerProperty paperId;
    SimpleStringProperty isSubmitted;
    SimpleStringProperty authors;
    SimpleStringProperty title;
    SimpleStringProperty format;
    SimpleStringProperty version;
    SimpleStringProperty filename;

    public int getPaperId() {
        return paperId.get();
    }

    public SimpleIntegerProperty paperIdProperty() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId.set(paperId);
    }

    public String getIsSubmitted() {
        return isSubmitted.get();
    }

    public SimpleStringProperty isSubmittedProperty() {
        return isSubmitted;
    }

    public void setIsSubmitted(String isSubmitted) {
        this.isSubmitted.set(isSubmitted);
    }

    public String getAuthors() {
        return authors.get();
    }

    public SimpleStringProperty authorsProperty() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors.set(authors);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getFormat() {
        return format.get();
    }

    public SimpleStringProperty formatProperty() {
        return format;
    }

    public void setFormat(String format) {
        this.format.set(format);
    }

    public String getVersion() {
        return version.get();
    }

    public SimpleStringProperty versionProperty() {
        return version;
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public String getFilename() {
        return filename.get();
    }

    public SimpleStringProperty filenameProperty() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename.set(filename);
    }

    public papers(Integer paperId, String isSubmitted, String authors, String title, String format, String version, String filename)
    {
        this.paperId = new SimpleIntegerProperty(paperId);
        this.isSubmitted = new SimpleStringProperty(isSubmitted.equals("0") ? "Not Submitted" : "Submitted");
        this.authors = new SimpleStringProperty(authors);
        this.title = new SimpleStringProperty(title);
        this.format = new SimpleStringProperty(format);
        this.version = new SimpleStringProperty(version);
        this.filename = new SimpleStringProperty(filename);
    }

}
