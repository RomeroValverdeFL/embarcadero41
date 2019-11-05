package pe.upn.embarcadero.controller;

import java.util.List;
import java.util.Optional;



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

import pe.upn.embarcadero.service.PlatosService;


@Controller
@RequestMapping("/plato")
@SessionAttributes( {"plato", "paciente" } )
public class PlatosController {

	@Autowired
	private PlatosService platosService;	
	
	@GetMapping
	public String inicio(Model model) {
		try {
			List<Plato> platos = platosService.findAll();
			model.addAttribute("platos", platos);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "/plato/inicio";
	}
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Plato> optional = platosService.findById(id);
			if (optional.isPresent()) {
				
			
				model.addAttribute("plato", optional.get());
			} else {
				return "redirect:/plato";
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "/plato/edit";
	}
	@PostMapping("/save")
	public String save(@ModelAttribute("plato") Plato plato, 
			Model model, SessionStatus status) {
		try {
			platosService.save(plato);
			status.setComplete();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:/plato";
	}
	@GetMapping("/nuevo")
	public String nuevo(Model model) {
		Plato plato = new Plato();
		model.addAttribute("plato", plato);
		
		return "/plato/nuevo";
	}
	@GetMapping("/del/{id}")
	public String eliminar(@PathVariable("id") int id, Model model) {
		try {
			Optional<Plato> plato = platosService.findById(id);
			if(plato.isPresent()) {
				platosService.deleteById(id);
			}
		} catch (Exception e) {
			
			model.addAttribute("dangerDel", "ERROR - Violación contra el principio de Integridad referencia");
			try {
				List<Plato> platos = platosService.findAll();
				model.addAttribute("platos", platos);
			} catch (Exception e2) {
				// TODO: handle exception
			} 
			return "/plato/inicio";
		}
		return "redirect:/plato";
	}
	@GetMapping("/info/{id}")
	public String info(@PathVariable("id") int id, Model model) {
		try {
			Optional<Plato> plato = platosService.findById(id);
			if(plato.isPresent()) {
				model.addAttribute("plato", plato.get());
			} else {
				return "redirect:/plato";
			}
		} catch (Exception e) {

		}	
		
		return "/plato/info";
	}
	
	
	
}























