package com.parkit.parkingsystem.model;

import com.parkit.parkingsystem.dao.TicketDAO;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.util.Calendar;
import java.util.Date;

@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2", "EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
public class Ticket extends TicketDAO {
    private int id;
    private ParkingSpot parkingSpot;
    private String vehicleRegNumber;
    private double price;
    private Date inTime;
    private Date outTime;
    private Boolean returningClient;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public String getVehicleRegNumber() {
        return vehicleRegNumber;
    }

    public void setVehicleRegNumber(String vehicleRegNumber) {
        this.vehicleRegNumber = vehicleRegNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }


    public Boolean setReturningClient(Boolean returningClient) {
        this.returningClient = returningClient;
        return returningClient;
    }

    public Boolean getReturningClient() {
        return returningClient;
    }

    public boolean isReturningClient(String vehicleRegNumber) {
        if(isReturning(vehicleRegNumber)){
            setReturningClient(true);
        }else{
            setReturningClient(false);
        }
        return returningClient;
    }
}
