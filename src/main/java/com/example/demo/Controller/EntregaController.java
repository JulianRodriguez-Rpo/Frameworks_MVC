package com.example.demo.Controller;

import com.example.demo.model.Entrega;
import com.example.demo.model.EstadoEntrega;
import com.example.demo.repository.EntregaRepository;
import com.example.demo.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class EntregaController {

    private final EntregaRepository entregaRepository;
    private final PedidoRepository pedidoRepository;

    @GetMapping("/entregas")
    public String listar(Model model) {
        model.addAttribute("entregas", entregaRepository.findAll());
        return "entregas/list";
    }

    @GetMapping("/entregas/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("entrega", new Entrega());
        model.addAttribute("pedidos", pedidoRepository.findAll());
        model.addAttribute("estados", EstadoEntrega.values());
        return "entregas/form";
    }

    @GetMapping("/entregas/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Entrega entrega = entregaRepository.findById(id).orElse(new Entrega());
        model.addAttribute("entrega", entrega);
        model.addAttribute("pedidos", pedidoRepository.findAll());
        model.addAttribute("estados", EstadoEntrega.values());
        return "entregas/form";
    }

    @PostMapping("/entregas/guardar")
    public String guardar(@ModelAttribute Entrega entrega) {
        entregaRepository.save(entrega);
        return "redirect:/entregas";
    }

    @GetMapping("/entregas/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        entregaRepository.deleteById(id);
        return "redirect:/entregas";
    }
}
