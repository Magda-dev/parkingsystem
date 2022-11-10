package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.model.Ticket;

public class FareCalculatorService {
	public double finalPrice;
	private double durationHours;
	
    public void calculateFare(Ticket ticket){
        if( (ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime())) ){
            throw new IllegalArgumentException("Out time provided is incorrect:"+ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
System.out.println("IN " + inHour);
System.out.println("OUT" +outHour);

        //TODO: Some tests are failing here. Need to check if this logic is correct
        durationHours = Math.floor((outHour - inHour) / 3600000);
        System.out.println("durationHours = "+durationHours);

      //Get remaining time from hours and convert to minutes
        double durationMinutes = Math.floor(((outHour - inHour) % 3600000) *60);
        System.out.println("durationMinutes = "+durationMinutes);

        
        free30Minutes(durationHours);
        
        
        
        
        
        
        switch (ticket.getParkingSpot().getParkingType()){
            case CAR: {
            	finalPrice = durationHours * (Fare.CAR_RATE_PER_HOUR);
            	System.out.println(finalPrice);

                ticket.setPrice(finalPrice);
                System.out.println("Le prix a payer pour votre voiture est donc = " + ticket.getPrice());
                break;
            }
            case BIKE: {
            	finalPrice = durationHours * (Fare.BIKE_RATE_PER_HOUR);
                ticket.setPrice(finalPrice);
                System.out.println("Le prix a payer pour votre moto est donc = " + ticket.getPrice());
                break;
            }
            default: throw new IllegalArgumentException("Unkown Parking Type");
        }
    }

	private void free30Minutes(double durationHours) {
		durationHours = this.durationHours;
		if(durationHours <= 0.5) {
	    	System.out.println("Vous n'avez rien à payer");

	        durationHours = 0;
	    }else {
	    	System.out.println("La duree de stationnement totale est de :" + durationHours + "hrs et ");
	    	durationHours = durationHours - 0.5;
	    	System.out.println("Nous vous faisons cadeau des 30 premières mins. Vous ne payerez que pour les " + durationHours + " hr et ");
	    }
		
	}
    
    
}