package sim;

public class Table {
	static private int totaleTavoli = 0;
	private int idCustomers;   
	private int idTable;
	private int numPostiASedere;
	private boolean libero;
	
	
	public Table(int numPostiASedere) {
		super();
		this.libero = true;
		this.numPostiASedere = numPostiASedere;
		idTable = ++totaleTavoli;
	}

	

	public int getIdCustomers() {
		return idCustomers;
	}



	public void setIdCustomers(int idCustomers) {
		this.idCustomers = idCustomers;
	}



	public int getIdTable() {
		return idTable;
	}


	public void setIdTable(int idTable) {
		this.idTable = idTable;
	}


	public int getNumPostiASedere() {
		return numPostiASedere;
	}


	public void setNumPostiASedere(int numPostiASedere) {
		this.numPostiASedere = numPostiASedere;
	}


	public boolean isLibero() {
		return libero;
	}


	public void setLibero(boolean libero) {
		this.libero = libero;
	}


	
	
	
	

}
