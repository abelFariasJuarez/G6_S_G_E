package sge.modelo;

import javax.persistence.Column;
import javax.persistence.EntityManager;

import sge.dao.*;
import sge.modelo.valueobjects.*;


public class Traductor {
	
	Repositorio repo;
	
	public Repositorio getRepo() {
		return repo;
	}
	
	public void setRepo(Repositorio repo) {
		this.repo = repo;
	}

	public Traductor(Repositorio repo) {
		this.repo = repo;
	}
	
	public RestriccionHorasFamiliaVO getVolueObject(RestriccionHorasFamilia per)
	{
		RestriccionHorasFamiliaVO vo = new RestriccionHorasFamiliaVO();		
		vo.setCodigo(per.getCodigo());
		vo.setMaximo(per.getMaximo());
		vo.setMinimo(per.getMinimo());		
		return vo;		
	}
	
	public RestriccionHorasFamilia getPersistente(RestriccionHorasFamiliaVO vo)
	{
		RestriccionHorasFamilia per = repo.findRestriccionHorasFamiliaBy("codigo", vo.getCodigo());		

		if (per == null) {
			per = new RestriccionHorasFamilia();
		}
		per.setCodigo(vo.getCodigo());
		per.setMaximo(vo.getMaximo());
		per.setMinimo(vo.getMinimo());		
		
		return per;		
	}

	public UbicacionVO getVolueObject(Ubicacion per)
	{
		UbicacionVO vo = new UbicacionVO();		
		vo.setLatitud(per.getLatitud());
		vo.setLongitud(per.getLongitud());
		return vo;		
	}
	
	public Ubicacion getPersistente(UbicacionVO vo)
	{
		Ubicacion per = repo.findByUbicacion(vo.getLongitud(),vo.getLatitud());
		if (per == null) {
			per = new Ubicacion();
		}
		per.setLatitud(vo.getLatitud());
		per.setLongitud(vo.getLongitud());
		return per;		
	}
	
	public void llenarAtributos(Cliente c, ClienteVO cVO) {
		llenarAtributos((Usuario) c,(UsuarioVO) cVO);
		c.setTipodoc(cVO.getTipoDoc());
		c.setNrodoc(cVO.getNrodoc());
		c.setTelefono(cVO.getTelefono());
		c.setCategoria(cVO.getCategoria());
		c.setDispositivos(cVO.getDispositivos());
		c.setPuntos(cVO.getPuntos());
		c.setUbicacion(cVO.getUbicacion());
		c.setAhorroAutomatico(cVO.isAhorroAutomatico());
		c.setAccionParaMejorarEficiencia(cVO.getAccionParaMejorarEficiencia());		
		
	}
	
	public void llenarAtributos(Zona zona, ZonaVO zonaVO) {
		zona.setId(zonaVO.getId());
		zona.setNombre(zonaVO.getNombre());
		zona.setCentro(zonaVO.getCentro());
		zona.setRadio(zonaVO.getRadio());	
		zona.setTransformadores(zonaVO.getTransformadores());
		zona.setClientes(zonaVO.getClientes());		
	}
	
	public void llenarAtributos(Transformador t, TransformadorVO tVO) {
		t.setClientes(tVO.getClientes());
		t.setId(tVO.getId());		
		t.setIdZona(tVO.getIdZona());		
		t.setUbicacion(tVO.getUbicacion());
		
	}
	
	public void llenarAtributos(Usuario u, UsuarioVO uVO) {
		u.setNombre(uVO.getNombre());
		u.setApellido(uVO.getApellido());
		u.setDomicilio(uVO.getDomicilio());
		u.setFechaingreso(uVO.getFechaIngreso());
		u.setUsername(uVO.getUsername());
		u.setPassword(uVO.getPassword());
	}
	

}
