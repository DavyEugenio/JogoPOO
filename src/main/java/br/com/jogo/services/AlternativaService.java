package br.com.jogo.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.jogo.domain.Alternativa;
import br.com.jogo.dto.AlternativaNewDTO;
import br.com.jogo.repositories.AlternativaRepository;
import br.com.jogo.services.exceptions.ObjectNotFoundException;

@Service
public class AlternativaService {

	@Autowired
	private AlternativaRepository repository;

	public Alternativa find(Integer id) {
		Optional<Alternativa> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado, Id: " + id + ", Tipo: " + Alternativa.class.getName()));
	}

	public Set<Alternativa> updateAllByList(Set<Alternativa> objs, List<Alternativa> newObjs) {
		if(objs.size() == 4 && objs.size() == newObjs.size()) {
			int i = 0;
			for(Alternativa alt: objs) {
				updateData(newObjs.get(i), alt);
				System.out.println(alt);
				i++;
				
			}
			return Set.copyOf(repository.saveAll(objs));
		} else {
			//um erro
			return null;
		}
		
	}

	private void updateData(Alternativa obj, Alternativa aux) {
		aux.setTexto(obj.getTexto());
		aux.setCorreta(obj.isCorreta());
	}

	public Alternativa fromDTO(AlternativaNewDTO objNewDto) {
		return new Alternativa(objNewDto.getTexto(), objNewDto.isCorreta());
	}

}
