import java.util.ArrayList;
import java.io.Serializable;

public class Company implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Tarif> tarifName = new ArrayList<Tarif>();
	
	public Company() {}
	public Company(String name){
		this.name = name;
	}
	public void set_name(String newName) {
		this.name = newName;
	}
	public String get_name() {
		return name;
	}
	public void setTarifName(ArrayList<Tarif> new_tarifName) {
		this.tarifName = new_tarifName;
	}
	public ArrayList<Tarif> get_tarif_name() {
		return tarifName;
	}
	public void add_tarif(Tarif tarif){
		tarifName.add(tarif);
	}
	public void remove_tarif(Tarif tarif){
		tarifName.remove(tarif);
	}
}
