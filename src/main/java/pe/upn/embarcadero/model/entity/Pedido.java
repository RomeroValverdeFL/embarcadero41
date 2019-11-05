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

import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;




@Entity
@Table(name = "pedidos")
public class Pedido {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedido;
	
	@Column(name = "nom_pedido", length = 60)
	private String nombrePedido;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Usuario usuarioP;
	
	
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
	private List<Plato> platos;

	public Pedido() {
		platos = new ArrayList<>();
	}
	//-----------------
	public void addPlato(Plato plato) {
		plato.setPedido(this);
		this.platos.add(plato);
	}
	//-----------------
	public Integer getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(Integer idPedido) {
		this.idPedido = idPedido;
	}
	public String getNombrePedido() {
		return nombrePedido;
	}
	public void setNombrePedido(String nombrePedido) {
		this.nombrePedido = nombrePedido;
	}
	public Usuario getUsuarioP() {
		return usuarioP;
	}
	public void setUsuarioP(Usuario usuarioP) {
		this.usuarioP = usuarioP;
	}
	public List<Plato> getPlatos() {
		return platos;
	}
	public void setPlatos(List<Plato> platos) {
		this.platos = platos;
	}
	
}





