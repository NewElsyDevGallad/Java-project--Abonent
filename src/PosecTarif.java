import java.io.Serializable;

public class PosecTarif extends Tarif implements Serializable {
	private static final long serialVersionUID = 3L;
	private int stoimost_sec_razgovora;
	
	public PosecTarif() {}
	public PosecTarif(String tarifName ,int stoimost_sec_razgovora){
		super(tarifName);
		this.stoimost_sec_razgovora = stoimost_sec_razgovora;
	}
	@Override
	public void set_tarif_name(String newNameOftarif) {
		this.tarifName = newNameOftarif;
	}
	@Override
	public String get_tarif_name() {
		return tarifName;
	}
	
	public void set_stoimost_sec_razgovora(int new_Stoimost_sec_razgovora) {
		this.stoimost_sec_razgovora = new_Stoimost_sec_razgovora;
	}
	public int get_stoimost_sec_razgovora() {
		return stoimost_sec_razgovora;
	}
	@Override
	public int rasschitat_stoimost_razgovora(int count_sec){
		if(count_sec > 0)
			return count_sec*stoimost_sec_razgovora;
		else
			return -1;
	}
}
