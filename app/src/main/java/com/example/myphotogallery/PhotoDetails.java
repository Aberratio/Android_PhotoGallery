package com.example.myphotogallery;

import java.util.ArrayList;

class PhotoDetails {
    private String folderName;
    private ArrayList<String> allPhotosInFolderPaths;

    String getFolderName() {
        return folderName;
    }

    void setFolderName(String newFolderPath) {
        folderName = newFolderPath;
    }

    ArrayList<String> getAllPhotosInFolderPaths() {
        return allPhotosInFolderPaths;
    }

    void setAllPhotosInFolderPaths(ArrayList<String> newFullPhotoPath) {
        allPhotosInFolderPaths = newFullPhotoPath;
    }
}
