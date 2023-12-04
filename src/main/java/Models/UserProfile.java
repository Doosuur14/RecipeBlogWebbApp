package Models;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
//@Builder


public class UserProfile {

    private FileInfo fileInfo;
    private String bio;
    private String favFoodCategory;

    public UserProfile() {
    }

    public UserProfile(String bio, String favFoodCategory, FileInfo fileInfo) {
        this.bio = bio;
        this.favFoodCategory = favFoodCategory;
        this.fileInfo = fileInfo;
    }


    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavFoodCategory() {
        return favFoodCategory;
    }

    public void setFavFoodCategory(String favFoodCategory) {
        this.favFoodCategory = favFoodCategory;
    }

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public void setFileInfo(FileInfo fileInfo) {
        this.fileInfo = fileInfo;
    }
}
