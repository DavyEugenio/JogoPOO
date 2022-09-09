package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Admin;
import br.com.jogo.repositories.AdminRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class AdminService {

	@Autowired
	private AdminRepository repository;

	public Admin find(Integer id) {
		Optional<Admin> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Admin.class.getName()));
	}

	@Transactional
	public Admin insert(Admin obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Admin update(Admin obj) {
		Admin newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Admin obj, Admin aux) {
		if (obj.getEmail() != null) {
			aux.setEmail(obj.getEmail());
		}
		if (obj.getNome() != null) {
			aux.setNome(obj.getNome());
		}
		if (obj.getNomeUsuario() != null) {
			aux.setNomeUsuario(obj.getNomeUsuario());
		}
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um admin relacionado");
		} catch (ObjectNotFoundException e) {
		}
	}

	public List<Admin> findAll() {
		return repository.findAll();
	}
	/*
	 * public Admin fromDTO(AdminDTO catDto) { return new Admin(catDto.getId(),
	 * catDto.getDescricao()); }
	 */
}
