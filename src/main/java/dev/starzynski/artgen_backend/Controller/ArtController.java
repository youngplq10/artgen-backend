package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class ArtController {
    @Autowired
    private ArtService artService;

    @PostMapping("/auth/art")
    public ResponseEntity<String> createArt(@RequestPart String username, @RequestPart String link, @RequestPart String prompt) {
        return new ResponseEntity<String> (artService.createArt(username, link, prompt), HttpStatus.CREATED);
    }

    @GetMapping("/auth/art/{linkTo}")
    public ResponseEntity<?> getArtData(@PathVariable String linkTo) {
        return artService.getArtData(linkTo);
    }
}
