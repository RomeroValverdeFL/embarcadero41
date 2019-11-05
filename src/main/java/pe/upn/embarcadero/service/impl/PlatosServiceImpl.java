package pe.upn.embarcadero.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.upn.embarcadero.model.entity.Plato;

import pe.upn.embarcadero.service.PlatosService;
import pe.upn.embarcadero.model.repository.PlatosRepository;;

@Service
public class PlatosServiceImpl implements PlatosService{
	@Autowired
	private PlatosRepository platoRepository; 
	
	@Transactional(readOnly = true)	
	@Override
	public List<Plato> findAll() throws Exception {
		// TODO Auto-generated method stub
		return platoRepository.findAll();
	}
	@Transactional(readOnly = true)
	@Override
	public Optional<Plato> findById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return platoRepository.findById(id);
	}
	@Transactional
	@Override
	public Plato save(Plato entity) throws Exception {
		// TODO Auto-generated method stub
		return platoRepository.save(entity);
	}
	@Transactional
	@Override
	public Plato update(Plato entity) throws Exception {
		// TODO Auto-generated method stub
		return platoRepository.save(entity);
	}
	@Transactional
	@Override
	public void deleteById(Integer id) throws Exception {
		platoRepository.deleteById(id);
		
	}
	@Transactional
	@Override
	public void deleteAll() throws Exception {
		platoRepository.deleteAll();
		
	}
	

}
