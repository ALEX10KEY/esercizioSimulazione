package sim;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;



public class Core {
	
	private Stats statistiche = new Stats();
	
	private Queue<Event> eventList = new PriorityQueue<Event>();
	private Map<Integer, Table> tavoli;
	
	private Random rd= new Random(42);
	
	public Core() {
		super();
		tavoli = new HashMap<Integer, Table>();
	}
	
	public void addEvent(Event e){
		eventList.add(e);
	}
	
	
	public boolean moreEvents(){
		return !eventList.isEmpty();
	}
	
	
	public void doSimulationStep(){
		
		
		Event e = eventList.remove();
		
		switch(e.eventType){
		case ARRIVO_GRUPPO_CLIENTI:
			int idTab = findAvailableTable(e.customer.getNum_persone());
			
			
			
			if( idTab!= -1){
				Table tavolo = tavoli.get(idTab);
				tavolo.setIdCustomers(e.customer.getId());
				tavolo.setLibero(false);
				
				tavoli.put(tavolo.getIdTable(), tavolo);
				
				e.customer.setSoddisfatti(true);
				
				Event seNeAndranno = new Event(e.timeStamp+e.customer.getDurata(), Event.eventTypeEnum.PARTENZA_GRUPPO_CLIENTI, e.customer);
				addEvent(seNeAndranno);
				
				System.out.println("Minuto: "+e.timeStamp+" - Tavolo "+tavolo.getIdTable()+" (Capienza: "+tavolo.getNumPostiASedere()+") Occupato da Gruppo# "+e.customer.getId()+" (Gruppo da "+e.customer.getNum_persone()+" Persone). Se ne vanno tra "+e.customer.getDurata()+" minuti");	

			}else{
				
				
				float tolleranza = e.customer.getTolleranza();
				float probabilita = rd.nextFloat();
				
				if(probabilita <= tolleranza){ 

					e.customer.setSoddisfatti(true);
					System.out.println("Minuto: "+e.timeStamp+" - Il gruppo  "+e.customer.getId()+" (Gruppo da "+e.customer.getNum_persone()+" Persone) e' servito al bancone");

				}
				else{
					e.customer.setSoddisfatti(false);
					System.out.println("Minuto: "+e.timeStamp+" - Il gruppo  "+e.customer.getId()+" (Gruppo da "+e.customer.getNum_persone()+" Persone) se n'e' andato insoddisfatto");

				}
			}
			
			statistiche.aggiungiClienti(e.customer);

			
			break;
		
		case PARTENZA_GRUPPO_CLIENTI:
			Table daLiberare = this.trovaTavolo(e.customer.getId());
						
			daLiberare.setLibero(true);
			daLiberare.setIdCustomers(-1);
			
			tavoli.put(daLiberare.getIdTable(), daLiberare);
			
			System.out.println("Minuto: "+e.timeStamp+" - Tavolo "+daLiberare.getIdTable()+" (Capienza: "+daLiberare.getNumPostiASedere()+") Liberato da gruppo "+e.customer.getId()+". Ritorna disponibile!");	

			break;
		default:
			throw new IllegalArgumentException();
			}
		
		
	
	}
	
	private int findAvailableTable(int numPersone) {
		List<Table> tavoliList = new LinkedList<Table>(tavoli.values());
		
		for(Table tb: tavoliList){
						
						if(tb.isLibero()&&tb.getNumPostiASedere()>= numPersone){
							return tb.getIdTable();
						}
						
		}
		return -1;
	}

	
	public Table trovaTavolo(int idCustomers){
		Table eccolo = null;
		for(Table t: tavoli.values()){
			if(t.getIdCustomers()==idCustomers)
				eccolo = t;
		}
		
		return eccolo;
	}
	
	public void addTavolo(int numPosti){
		Table temp = new Table(numPosti);
		tavoli.put(temp.getIdTable(), temp);
		statistiche.numTavoli(tavoli.size());
	}
	
	public Stats getStatistiche(){
		return this.statistiche;
	}
	
	

}
