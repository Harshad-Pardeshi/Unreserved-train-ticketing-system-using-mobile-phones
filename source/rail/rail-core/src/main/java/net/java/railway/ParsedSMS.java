package net.java.railway;

import java.util.StringTokenizer;

public class ParsedSMS {

    long id;
    long mobileNumber;
    String action;

    String from;
    String to;
    int noOfTicket = 1;
    String others = null;

    public ParsedSMS() {
    }

    /**
     * 01234567890123456789 <br>
     * // TKT book KYLN CST 1 mobilenumber <br>
     * // TKT bal <br>
     * // TKT help
     */
    public ParsedSMS(SMS sms) throws Exception {
        this.id = sms.getId();
        this.mobileNumber = sms.getMobile();

        String body = sms.getBody();

        StringTokenizer st = new StringTokenizer(body, " ");
        if (st.hasMoreTokens()) {
            st.nextToken();
            if (st.hasMoreTokens()) {
                action = st.nextToken();
            }
            if (st.hasMoreTokens()) {
                from = st.nextToken();
            }

            if (st.hasMoreTokens()) {
                to = st.nextToken();
            }

            if (st.hasMoreTokens()) {
                try {
                    noOfTicket = Integer.parseInt(st.nextToken());
                } catch (NumberFormatException nfe) {
                    noOfTicket = 0;
                }
            }
            if (st.hasMoreTokens()) {
                others = st.nextToken();
            }
        }
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getNoOfTicket() {
        return noOfTicket;
    }

    public void setNoOfTicket(int noOfTicket) {
        this.noOfTicket = noOfTicket;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public boolean validateBookingSMS() {
        if (from == null || from.trim().length() == 0 || from.trim().length() < 3 || from.trim().length() > 4) {
            System.out.println("ERROR : from station error");
            return false;
        }

        else if (to == null || to.trim().length() == 0 || to.trim().length() < 3 || to.trim().length() > 4) {
            System.out.println("ERROR : to station error");
            return false;
        }

        else if (noOfTicket == 0 || noOfTicket > 6) {
            System.out.println("ERROR : noOfTicket station error");
            return false;
        }

        return true;
    }

}
