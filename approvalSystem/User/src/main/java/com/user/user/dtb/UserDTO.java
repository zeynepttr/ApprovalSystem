package com.user.user.dtb;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private Long groupId;

    public Long getid()
    {
        return id;
    }

    public void setid(Long id)
    {
        this.id =  id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username =  username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password =  password;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId =  roleId;
    }
    public Long getGroupId() {
        return groupId;
    }

   public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}

