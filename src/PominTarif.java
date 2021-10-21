import java.io.Serializable;

public class PominTarif extends Tarif implements Serializable {
	private static final long serialVersionUID = 4L;
	private int stoimost_min_razgovora;
	
	public PominTarif() {}
	public PominTarif(String tarifName ,int stoimost_min_razgovora){
		super(tarifName);
		this.stoimost_min_razgovora = stoimost_min_razgovora;
	}
	@Override
	public void set_tarif_name(String newNameOftarif) {
		this.tarifName = newNameOftarif;
	}
	@Override
	public String get_tarif_name() {
		return tarifName;
	}
	
	public void set_stoimost_min_razgovora(int new_Stoimost_min_razgovora) {
		this.stoimost_min_razgovora = new_Stoimost_min_razgovora;
	}
	public int get_stoimost_min_razgovora() {
		return stoimost_min_razgovora;
	}
	@Override
	public int rasschitat_stoimost_razgovora(int count_sec){
		if(count_sec > 0)
			return count_sec/60*stoimost_min_razgovora;
		else
			return -1;
	}
}
