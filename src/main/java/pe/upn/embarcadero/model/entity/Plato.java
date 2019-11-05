package pe.upn.embarcadero.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "platos")
public class Plato {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_plato")
	private Integer codigoPlato;
	
	@Column(name = "nom_platos", length = 30, nullable = false )
	private String nombrePlato;
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;


	public Integer getCodigoPlato() {
		return codigoPlato;
	}


	public void setCodigoPlato(Integer codigoPlato) {
		this.codigoPlato = codigoPlato;
	}


	public String getNombrePlato() {
		return nombrePlato;
	}


	public void setNombrePlato(String nombrePlato) {
		this.nombrePlato = nombrePlato;
	}


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
	
}
