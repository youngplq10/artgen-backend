package dev.starzynski.artgen_backend.Model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "arts")
public class Art {
    @Id
    private ObjectId id;

    @DBRef
    private User user;

    private String link, prompt;

    private Integer cost;

    public Art() {
        this.id = new ObjectId();
    }

    public ObjectId getId() { return id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public Integer getCost() { return cost; }
    public void setCost(Integer cost) { this.cost = cost; }
}
