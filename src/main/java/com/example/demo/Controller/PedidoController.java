package com.example.demo.Controller;

import com.example.demo.model.EstadoPedido;
import com.example.demo.model.Pedido;
import com.example.demo.repository.ClienteRepository;
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
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @GetMapping("/pedidos")
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "pedidos/list";
    }

    @GetMapping("/pedidos/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("estados", EstadoPedido.values());
        return "pedidos/form";
    }

    @GetMapping("/pedidos/editar/{id}")
    public String editarForm(@PathVariable Long id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).orElse(new Pedido());
        model.addAttribute("pedido", pedido);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("estados", EstadoPedido.values());
        return "pedidos/form";
    }

    @PostMapping("/pedidos/guardar")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoRepository.save(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        pedidoRepository.deleteById(id);
        return "redirect:/pedidos";
    }
}
