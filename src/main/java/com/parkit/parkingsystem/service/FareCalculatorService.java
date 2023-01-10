package com.parkit.parkingsystem.service;

import com.parkit.parkingsystem.constants.Fare;
import com.parkit.parkingsystem.dao.TicketDAO;
import com.parkit.parkingsystem.model.Ticket;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class FareCalculatorService {
    public double finalPrice;

    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_NO_SIDE_EFFECT")
    public void calculateFare(Ticket ticket) {
        if ((ticket.getOutTime() == null) || (ticket.getOutTime().before(ticket.getInTime()))) {
            throw new IllegalArgumentException("Out time provided is incorrect:" + ticket.getOutTime().toString());
        }

        if((ticket.getParkingSpot().getParkingType()== null)){
            throw new NullPointerException("Parking type is incorrect:" + ticket.getOutTime().toString());
        }

        long inHour = ticket.getInTime().getTime();
        long outHour = ticket.getOutTime().getTime();
        double totalInMinutes = (outHour-inHour)/(double)(1000*60);

        if (totalInMinutes <= 30.00) {
            finalPrice = 0.0;
            System.out.println("Les 30 first are on us ! You have nothing to pay, See you soon");
            ticket.setPrice(finalPrice);
        } else{
            switch (ticket.getParkingSpot().getParkingType()) {
                case CAR: {
                    free30MinDiscount(totalInMinutes);
                    finalPrice = (totalInMinutes * (Fare.CAR_RATE_PER_HOUR / 60));
                    ticket.setPrice(finalPrice);

                    break;
                }
                case BIKE: {
                    free30MinDiscount(totalInMinutes);
                    finalPrice = (totalInMinutes * (Fare.BIKE_RATE_PER_HOUR / 60));
                    ticket.setPrice(finalPrice);

                    break;
                }
                default:
                    throw new IllegalArgumentException("Unknown Parking Type");

            }

        }
        discountForReturning(ticket);
    }

    public void discountForReturning (Ticket ticket){
        if (ticket.isReturning(ticket.getVehicleRegNumber())) {
            ticket.setPrice(finalPrice-(finalPrice * 0.05));
            System.out.println("Price after discount : " + ticket.getPrice());
        }else{
            ticket.setPrice(finalPrice);
        }
    }
    public Double free30MinDiscount(double totalInMinutes){
        totalInMinutes = totalInMinutes - 30.0;
        return totalInMinutes;
    }




}


