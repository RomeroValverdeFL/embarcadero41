package pe.upn.embarcadero.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import pe.upn.embarcadero.model.entity.*;
import pe.upn.embarcadero.service.PedidoService;
import pe.upn.embarcadero.service.UsuarioService;
import pe.upn.embarcadero.service.PlatosService;


@Controller
@RequestMapping("/pedido")
@SessionAttributes( {"pedido"} )
public class PedidoController {

	@Autowired
	private PlatosService platosService;
	
	@Autowired
	private PedidoService pedidoService;
	

	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Pedido> pedidos = pedidoService.findAll();
			model.addAttribute("pedidos", pedidos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/pedido/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Pedido> optional = pedidoService.findById(id);
			if (optional.isPresent()) {
				
				List<Plato> platos 
					= platosService.findAll(); 
				
				model.addAttribute("pedido", optional.get());
				model.addAttribute("platos", platos);
			} else {
				return "redirect:/pedido";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/pedido/edit";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("pedido") Pedido pedido, 
			Model model, SessionStatus status) {
		try {
			pedidoService.save(pedido);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/pedido";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Pedido pedido = new Pedido();
		model.addAttribute("pedido", pedido);
		try {
			List<Plato> platos = 
					platosService.findAll();
			model.addAttribute("platos", platos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/pedido/nuevo";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Pedido> pedido = pedidoService.findById(id);
			if(pedido.isPresent()) {
				pedidoService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Pedido> pedidos = pedidoService.findAll();
				model.addAttribute("pedidos", pedidos);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/pedido/inicio";
		}
		return "redirect:/pedido";
	}
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Pedido> pedido = pedidoService.findById(id);
			if(pedido.isPresent()) {
				model.addAttribute("pedido", pedido.get());
			} else {
				return "redirect:/pedido";
			}
		} catch (Exception e) {

		}	
		
		return "/pedido/info";
	}
	
	@GetMapping("/{id}/nuevoplato")
	public String nuevoPaciente(@PathVariable("id") int id, Model model) {
		Plato plato = new Plato();
		try {
			Optional<Pedido> pedido = pedidoService.findById(id);
			if(pedido.isPresent()) {
				plato.setPedido(pedido.get());
				model.addAttribute("plato",plato);
			} else {
				return "redirect:/pedido";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/pedido/nuevoplato";
	}
	
	@PostMapping("/saveplato")
	public String savePaciente(@ModelAttribute("plato") Plato plato, 
			Model model, SessionStatus status) {
		try {
			platosService.save(plato);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/pedido/info/" + plato.getPedido().getIdPedido();
	}
	
}























