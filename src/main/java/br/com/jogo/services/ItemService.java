package br.com.jogo.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Item;
import br.com.jogo.dto.ItemDTO;
import br.com.jogo.dto.ItemNewDTO;
import br.com.jogo.repositories.ItemRepository;
import br.com.jogo.services.exceptions.DataIntegrityException;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class ItemService {

	@Autowired
	private ItemRepository repository;

	public Item find(Integer id) {
		Optional<Item> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Item.class.getName()));
	}

	@Transactional
	public Item insert(Item obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Item update(Item obj) {
		Item newObj = find(obj.getId());
		updateData(obj, newObj);
		return repository.save(newObj);
	}

	private void updateData(Item obj, Item aux) {
		/*
		 * if (obj.getTexto() != null) { aux.setTexto(obj.getTexto()); } if
		 * (obj.getNivel() != 0) { aux.setNivel(obj.getNivel()); } if
		 * (obj.getCategoria() != null) { aux.setCategoria(obj.getCategoria()); } }
		 */
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma questão que possui alternativas");
		} catch (ObjectNotFoundException e) {
		}
	}

	public List<Item> findAll() {
		return repository.findAll();
	}

	public Item fromDTO(ItemNewDTO objDto) {
		return new Item(objDto.getNome(), objDto.getFuncao(), objDto.getRaridade(), objDto.getPreco(),
				objDto.getPenalidade());
	}

	public Item fromDTO(ItemDTO objDto) {
		return new Item(objDto.getId(), objDto.getNome(), objDto.getFuncao(), objDto.getRaridade(), objDto.getPreco(),
				objDto.getPenalidade());
	}

}
