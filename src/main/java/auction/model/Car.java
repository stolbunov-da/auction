package auction.model;

import java.util.Date;

/**
 * Created by stolbunovd
 * on 9/27/18
 */
public class Car {

    private Date auctionDate;
    private Integer year;
    private String model;
    private String auctionName;
    private String vin;
    private String link;
    private Integer finalBid;

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getFinalBid() {
        return finalBid;
    }

    public void setFinalBid(Integer finalBid) {
        this.finalBid = finalBid;
    }
}
