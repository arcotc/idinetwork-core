package uk.co.idinetwork.core.model;

import org.gmr.web.multipart.GMultipartFile;

public class MultiPartFile {
    private String filename;
    private GMultipartFile file;

    public String getName() {
        return filename;
    }

    public void setName(String filename) {
        this.filename = filename;
    }

    public void setFile(GMultipartFile file) {
        this.file = file;
    }

    public GMultipartFile getFile() {
        return file;
    }
}