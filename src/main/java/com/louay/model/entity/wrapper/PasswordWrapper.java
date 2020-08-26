package com.louay.model.entity.wrapper;

import java.io.Serializable;

public class PasswordWrapper implements Serializable{
    private static final long serialVersionUID = 6177378199747586989L;
    private String oldPassword;
    private String newPassword;
    private String reNewPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getReNewPassword() {
        return reNewPassword;
    }

    public void setReNewPassword(String reNewPassword) {
        this.reNewPassword = reNewPassword;
    }

    @Override
    public String toString() {
        return "PasswordWrapper{" +
                "oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                ", reNewPassword='" + reNewPassword + '\'' +
                '}';
    }
}
