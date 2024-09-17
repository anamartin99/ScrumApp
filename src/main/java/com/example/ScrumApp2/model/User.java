package com.example.ScrumApp2.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
        name="user",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class User implements UserDetails {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Basic
        @Column(nullable = false)
        private String username;
        private String password;

        @Column(unique = true)
        private String email;
        @Enumerated(EnumType.STRING)
        ERole role;

        public User() {
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
                return List.of(new SimpleGrantedAuthority((role.name())));
        }

        @Override
        public boolean isAccountNonExpired() {
                return true;
        }

        @Override
        public boolean isAccountNonLocked() {
                return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
                return true;
        }

        @Override
        public boolean isEnabled() {
                return true;
        }

        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
        private Set<Task> tasks;

        @ManyToMany
        @JoinTable(name = "user_projects", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
        private Set<Project> projects;

        public User(Builder builder) {
                this.id = builder.id;
                this.username = builder.username;
                this.password = builder.password;
                this.email = builder.email;
                this.role = builder.role;
                this.tasks = builder.tasks;
                this.projects = builder.projects;
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public ERole getRole() {
                return role;
        }

        public void setRole(ERole role) {
                this.role = role;
        }

        public Set<Task> getTasks() {
                return tasks;
        }

        public void setTasks(Set<Task> tasks) {
                this.tasks = tasks;
        }

        public Set<Project> getProjects() {
                return projects;
        }

        public void setProjects(Set<Project> projects) {
                this.projects = projects;
        }
        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", username='" + username + '\'' +
                        ", password='" + password + '\'' +
                        ", email='" + email + '\'' +
                        ", role=" + role +
                        ", tasks=" + tasks +
                        ", projects=" + projects +
                        '}';
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                User user = (User) o;
                return Objects.equals(id, user.id) &&
                        Objects.equals(username, user.username) &&
                        Objects.equals(password, user.password) &&
                        Objects.equals(email, user.email) &&
                        role == user.role &&
                        Objects.equals(tasks, user.tasks) &&
                        Objects.equals(projects, user.projects);
        }
        @Override
        public int hashCode() {
                return Objects.hash(id, username, password, email, role, tasks, projects);
        }

        public static class Builder {
                private Long id;
                private String username;
                private String password;
                private String email;
                private ERole role;
                private Set<Task> tasks;
                private Set<Project> projects;

                public Builder id(Long id) {
                        this.id = id;
                        return this;
                }

                public Builder username(String username) {
                        this.username = username;
                        return this;
                }

                public Builder password(String password) {
                        this.password = password;
                        return this;
                }

                public Builder email(String email) {
                        this.email = email;
                        return this;
                }

                public Builder role(ERole role) {
                        this.role = role;
                        return this;
                }

                public Builder tasks(Set<Task> tasks) {
                        this.tasks = tasks;
                        return this;
                }

                public Builder projects(Set<Project> projects) {
                        this.projects = projects;
                        return this;
                }

                public User build() {
                        return new User(this);
                }
        }
}
