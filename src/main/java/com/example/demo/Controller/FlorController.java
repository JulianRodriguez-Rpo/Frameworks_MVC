package com.example.demo.Controller;

import com.example.demo.model.Flor;
import com.example.demo.repository.FlorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class FlorController {

    private final FlorRepository florRepository;

    @GetMapping("/flores")
    public String listar(Model model) {
        model.addAttribute("flores", florRepository.findAll());
        return "flores/list";
    }

    @GetMapping("/flores/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("flor", new Flor());
        return "flores/form";
    }

    @GetMapping("/flores/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Flor flor = florRepository.findById(id).orElse(new Flor());
        model.addAttribute("flor", flor);
        return "flores/form";
    }

    @PostMapping("/flores/guardar")
    public String guardar(@ModelAttribute Flor flor) {
        florRepository.save(flor);
        return "redirect:/flores";
    }

    @GetMapping("/flores/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        florRepository.deleteById(id);
        return "redirect:/flores";
    }
}
