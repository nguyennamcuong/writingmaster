package com.example.writingmasternbs.dto;

public class ImageDTO {
    String imagename;
    int imageid;

    public ImageDTO() {
    }


    public ImageDTO(String imagename, int imageid) {
        this.imagename = imagename;
        this.imageid = imageid;
    }

    @Override
    public String toString() {
        return "ImageDTO{" +
                "imagename='" + imagename + '\'' +
                ", imageid=" + imageid +
                '}';
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }
}
