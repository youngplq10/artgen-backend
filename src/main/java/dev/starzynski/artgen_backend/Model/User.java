package dev.starzynski.artgen_backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;

    private String username, email, password, role;

    private Date createdAtDAte;

    private Integer credits;

    @DBRef
    private List<Art> arts;

    public User() {
        this.id = new ObjectId();
        this.createdAtDAte = new Date();
        this.role = "USER";
        this.credits = 100;
        this.arts = new ArrayList<>();
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

    public Integer getCredits() { return credits; }
    public void setCredits(Integer credits) { this.credits = credits; }

    public List<Art> getArts() { return arts; }
}
