////////////////////////////////////////////////////////////////////////////////
//             //                                                             //
//   #####     // Queue simulator                                             //
//  ######     // (!) 2014 Giovanni Squillero <giovanni.squillero@polito.it>  //
//  ###   \    //                                                             //
//   ##G  c\   //                                                             //
//   #     _\  //                                                             //
//   |   _/    //                                                             //
//   |  _/     //                                                             //
//             // 03FYZ - Tecniche di programmazione 2013-14                  //
////////////////////////////////////////////////////////////////////////////////

package sim;

public class Event implements Comparable<Event> {
	public enum eventTypeEnum {
		ARRIVO_GRUPPO_CLIENTI, PARTENZA_GRUPPO_CLIENTI
	};

	public long timeStamp;
	public eventTypeEnum eventType;
	public GruppoCustomer customer;

	

	public Event(long timeStamp, eventTypeEnum eventType, GruppoCustomer customer) {
		super();
		this.timeStamp = timeStamp;
		this.eventType = eventType;
		this.customer = customer;
	}
	
	@Override
	public int compareTo(Event e) {
		return Long.compare(this.timeStamp, e.timeStamp);
	}
}
