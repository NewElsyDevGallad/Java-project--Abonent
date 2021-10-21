import java.util.ArrayList;
import java.io.Serializable;

public abstract class Tarif implements Serializable{
	private static final long serialVersionUID = 2L;
	protected String tarifName;
	private ArrayList<Abonent> abonents = new ArrayList<Abonent>();
	
	public Tarif() {}
	public Tarif(String nameOftarif){
		this.tarifName = nameOftarif;
	}
	public abstract int rasschitat_stoimost_razgovora(int count_sec);

	public void set_tarif_name(String nameOftarif){
		this.tarifName = nameOftarif;
	}
	
	public String get_tarif_name(){
		return tarifName;
	}
	
	public void add_abonent(Abonent abonent) {
		abonents.add(abonent);
	}
	public void remove_abonent(Abonent abonent) {
		abonents.remove(abonent);
	}
	public int amount_abonent() {
		return abonents.size();
	}
	public void set_abonents(ArrayList<Abonent> abonents){
		this.abonents = abonents;
	}
	public ArrayList<Abonent> get_abonents(){
		return abonents;
	}
}
