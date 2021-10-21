import java.io.Serializable;

public class Abonent implements Serializable {
	private static final long serialVersionUID = 5L;
	private String FN;
	private String number;
	private int account;
	
	public Abonent() {}
	public Abonent(String FN) {
		this.FN = FN;
	}
	public void set_FN(String newFN) {
		this.FN = newFN;
	}
	public String get_FN() {
		return this.FN;
	}
	public void set_number(String newNumber) {
		this.number = newNumber;
	}
	public String get_number() {
		return this.number;
	}
	public void set_account(int newAccount) {
		this.account = newAccount;
	}
	public int get_account() {
		return this.account;
	}
}

