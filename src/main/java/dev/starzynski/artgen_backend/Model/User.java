package dev.starzynski.artgen_backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    private String username;

    private String email;

    private Date createdAtDAte;

    private String password;

    private String role;

    public User() {
        this.createdAtDAte = new Date();
        this.role = "USER";
    }

    public ObjectId getId() { return id; }
    public void setId(ObjectId id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Date getCreatedAtDAte() { return createdAtDAte; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
}
