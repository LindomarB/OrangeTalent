package com.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.models.Endereco;
import com.projeto.models.Usuario;
import com.projeto.repository.EnderecoRepository;
import com.projeto.repository.UsuarioRepository;

@Controller
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	@RequestMapping(value="/cadastrar", method = RequestMethod.GET)
	public String form() {
		return "usuario/formUsuario";
	}

	@RequestMapping(value="/cadastrar", method = RequestMethod.POST)
	public String form(Usuario usuario) {
		usuarioRepository.save(usuario);
		return "redirect:/cadastrar";
	}
	@RequestMapping("/")
	public ModelAndView listaUsuarios() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Usuario> usuarios = usuarioRepository.findAll();
		mv.addObject("usuarios", usuarios);
		return mv;
	}
	@RequestMapping(value= "detalhes/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesUsuario(@PathVariable("id") long id) {
		Usuario usuario = usuarioRepository.findById(id);
		ModelAndView mv = new ModelAndView("usuario/detalhes");
		mv.addObject("usuario", usuario);
		
		Iterable<Endereco> enderecos = enderecoRepository.findByUsuario(usuario);
		mv.addObject("enderecos", enderecos);
		return mv;
	}
	@RequestMapping(value= "detalhes/{id}", method = RequestMethod.POST)
	public String detalhesUsuarioPost(@PathVariable("id") long id, Endereco endereco) {
		Usuario usuario = usuarioRepository.findById(id);
		endereco.setUsuario(usuario);
		enderecoRepository.save(endereco);
		return "redirect:/{id}";
	}
	
}
