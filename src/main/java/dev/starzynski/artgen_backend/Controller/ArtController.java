package dev.starzynski.artgen_backend.Controller;

import dev.starzynski.artgen_backend.Service.ArtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class ArtController {
    @Autowired
    private ArtService artService;
}
