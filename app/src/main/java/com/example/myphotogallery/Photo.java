package com.example.myphotogallery;

import java.util.ArrayList;

class Photo {
    private String folderPath;
    private ArrayList<String> fullPhotoPath;

    String getFolderPath() {
        return folderPath;
    }

    void setFolderPath(String newFolderPath) {
        folderPath = newFolderPath;
    }

    ArrayList<String> getFullPhotoPath() {
        return fullPhotoPath;
    }

    void setFullPhotoPath(ArrayList<String> newFullPhotoPath) {
        fullPhotoPath = newFullPhotoPath;
    }
}
