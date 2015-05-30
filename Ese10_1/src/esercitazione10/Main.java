package esercitazione10;
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

import java.util.Random;

import sim.*;

public class Main {

	public static void main(String[] args) {
		Core core = new Core();
		
		core.addTavolo(10); 
		core.addTavolo(10); 
		
		core.addTavolo(8);
		core.addTavolo(8);
		core.addTavolo(8);
		core.addTavolo(8);
		
		core.addTavolo(6);
		core.addTavolo(6);
		core.addTavolo(6);
		core.addTavolo(6);
		
		core.addTavolo(4);
		core.addTavolo(4);
		core.addTavolo(4);
		core.addTavolo(4);
		core.addTavolo(4);
		
		

		Random rn = new Random(42);
		
		long precedente =0;
		for(int t=0; t<2000; ++t) {
			GruppoCustomer c = new GruppoCustomer((long)(60+Math.random()*60), rn.nextFloat(), 1+rn.nextInt(9));   
			c.setTimeArrival(precedente+1+rn.nextInt(9));
			precedente = c.getTimeArrival();
			Event e = new Event(c.getTimeArrival(), Event.eventTypeEnum.ARRIVO_GRUPPO_CLIENTI, c);
			core.addEvent(e);
		}
		
		
		System.out.print("\n\n*** SIMULATION STARTS ***\n\n");
		while(core.moreEvents()) {
			core.doSimulationStep();
		}
		System.out.print("\n\n*** SIMULATION ENDS ***\n\n");

		System.out.println("\n\n\n STATISTICHE \n");

		System.out.println(core.getStatistiche().stampale());

		
	}

}
