package net.java.railway.messaging;

/**
 * @author rajul konkar
 */
public class SMSMessage {

    private String originator;
    private String body;

    /**
     * @return the originator
     */
    public String getOriginator() {
        int length = originator.length();
        if (length > 10) {
            originator = originator.substring(length - 10, length);
        }
        return originator;
    }

    /**
     * @param originator
     *            the originator to set
     */
    public void setOriginator(String originator) {
        int len = originator.length();
        if (len > 10)
            this.originator = originator.substring(len - 10);
        else
            this.originator = originator;
    }

    /**
     * @return the body
     */
    public String getBody() {
        return body;
    }

    /**
     * @param body
     *            the body to set
     */
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return originator + " >> " + body;
    }
}
