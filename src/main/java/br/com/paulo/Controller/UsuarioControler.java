package br.com.paulo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.paulo.Models.entity.Usuario;
import br.com.paulo.Models.repo.UsuarioRepo;

@Controller
@RequestMapping(path = "/Usuario")
public class UsuarioControler {

	@Autowired
	private UsuarioRepo repo;
	
	@GetMapping(path="/form")
    public String nnew() {
        return "NovoUser";
    }

	/* Metodo para salvar novo USUARIO*/
	@PostMapping(path = "/save")
	public String novo( Usuario usuario) {
		repo.save(usuario);
		System.out.println("Usuario salvo!!!!!!!!!!!!!!");
		return "redirect:/Usuario/ListaDeUsuarios";
	}
	
	/* Metodo para listar todos USUARIO*/
	@GetMapping("/ListaDeUsuarios")
	public String index(Model model) {
		List<Usuario> lista = repo.findAll();
		
		model.addAttribute("Lista", lista);
		return "ListaDeUsuarios";
	}
		
	/* Metodo para deletar USUARIO*/
	@GetMapping(path = "/deletar/{id}")
	public String deletarPorId(@PathVariable Integer id) {
		Usuario user = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalidooo"));
		
		repo.delete(user);
		return "redirect:/Usuario/ListaDeUsuarios";
	}
	
	/* Metodo para chamar edição de USUARIO*/
	@GetMapping(path = "/edit/{id}")
	public String editPage(@PathVariable Integer id, Model model) {
		Optional<Usuario> user = repo.findById(id);
		
		model.addAttribute("User", user);
		return "EditarUser";

	}
		
	@PostMapping(path = "/saveEdit/{id}")
	public String saveEdit(@PathVariable Integer id, @Validated Usuario user ) {
		repo.save(user);
		
		return "redirect:/Usuario/ListaDeUsuarios";
	}
		
}