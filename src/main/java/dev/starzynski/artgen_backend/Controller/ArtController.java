package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;

@Controller
@RequestMapping("/api")
public class ArtController {
    @Autowired
    private ArtService artService;

    @PostMapping("/auth/art")
    public ResponseEntity<String> createArt(@RequestPart String username, @RequestPart String link, @RequestPart String prompt, @RequestPart String cost) {
        return new ResponseEntity<String> (artService.createArt(username, link, prompt, Integer.parseInt(cost)), HttpStatus.CREATED);
    }
}
