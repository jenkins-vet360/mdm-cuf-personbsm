package mdm.cuf.personbsm.server.service;

import mdm.cuf.core.bio.AbstractBio;

public class PersonBsmTaskDetail {
    
    private int index;
    
    private AbstractBio bio;
    
    private String errorMessage;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public AbstractBio getBio() {
        return bio;
    }

    public void setBio(AbstractBio bio) {
        this.bio = bio;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
