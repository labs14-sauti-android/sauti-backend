package com.labs.sauti.model.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "userRoles")
public class UserRole implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnoreProperties("userRoles")
    private User user;

    @Id
    @ManyToOne
    @JoinColumn(name = "roleId")
    @JsonIgnoreProperties("userRoles")
    private Role role;

    public UserRole() {
    }

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof UserRole)) return false;
        UserRole otherUserRole = (UserRole) other;
        return user == otherUserRole.user && role == otherUserRole.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, role);
    }
}