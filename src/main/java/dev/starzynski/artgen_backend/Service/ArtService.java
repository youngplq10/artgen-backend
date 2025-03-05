package dev.starzynski.artgen_backend.Service;

import dev.starzynski.artgen_backend.Model.Art;
import dev.starzynski.artgen_backend.Model.User;
import dev.starzynski.artgen_backend.Repository.ArtRepository;
import dev.starzynski.artgen_backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArtService {
    @Autowired
    private ArtRepository artRepository;

    @Autowired
    private UserRepository userRepository;

    public String createArt(String username, String link, String prompt, Integer cost) {
        try {
            Optional<User> user = userRepository.findByUsername(username);

            Art art = new Art();
            art.setUser(user.get());
            art.setLink(link);
            art.setPrompt(prompt);
            art.setCost(cost);

            artRepository.insert(art);

            user.get().getArts().add(art);

            return art.getLinkTo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
