package UI;

import processing.core.*;

/*
 * @ author: Mark Hinshaw 
 * @ email: mahinshaw@gmail.com
 * @ github: https://github.com/mahinshaw/NetworkingAnimation.git
 * 
 * This class is a message that travels from location to location.  This method will contain all the information needed for display within the message.
 * All calculations are done locally and can be called for use in other classes.
 * 
 */

public class Message {
    private PApplet parent;

    private float width, height;
    PVector position;
    PVector start;
    PVector end;
    PVector ack;
    PVector ackEnd;
    PVector ackStart;

    private PVector speed;

    private String message;
    private String binary;
    private String source;
    private String destination;
    private String destMAC;
    private String destinationMAC;
    private String sourMAC;
    private String sourceMAC;
    private String type;
    private String crc;
    private String preamble;
    private String sfd;

    // sine wave variables
    private float frequency, amplitude;
    private boolean shiftKey;            // if true then fsk, else, ask

    int color;

    public Message(PApplet p, float x, float y, float w, float h, int c, String m) {
        parent = p;
        position.x = x;
        position.y = y;
        width = w;
        height = h;
        speed = new PVector((float) 0.05, (float) 0.05);
        color = c;
        message = m;
        binary = calcBinary(message);
        source = "72.76.5.32";
        destination = "74.188.4.158";
        destMAC = "04:8F:F2:23";
        destinationMAC = "00000100100011111111001000100011";
        sourMAC = "06:4C:F3:4B";
        sourceMAC = "00000110010011001111001101001011";
        type = "21";
        crc = "";
        preamble = "101010101010101010101010101010101010101010";
        sfd = "10101011";
        frequency = 9;
        amplitude = 200;
        shiftKey = true; // default = fsk

    }

    public Message(PApplet p, PVector v, float w, float h, int c, String m) {
        parent = p;
        position = v.get();
        width = w;
        height = h;
        speed = new PVector((float) 0.05, (float) 0.05);
        color = c;
        message = m;
        binary = calcBinary(message);
        source = "192.168.1.1";
        destination = "192.168.1.158";
        destMAC = "04:8F:F2:23";
        destinationMAC = "00000100100011111111001000100011";
        sourMAC = "06:4C:F3:4B";
        sourceMAC = "00000110010011001111001101001011";
        type = "80";
        crc = "10111100";
        preamble = "101010101010101010101010101010101010101010";
        sfd = "10101011";
        frequency = 9;
        amplitude = 200;
        shiftKey = true; // default = fsk

    }

    /*
     * begin get and set methods for global variables
     */
    public void setPosition(PVector p) {
        position = p.get();
    }

    public void setStart(PVector v) {
        start = v.get();
    }

    public void setEnd(PVector v) {
        end = v.get();
    }

    public PVector getSpeed() {
        return speed.get();
    }

    public void setSpeed(PVector s) {
        speed = s.get();
    }

    public void setSpeed(float x, float y) {
        speed.x = x;
        speed.y = y;
    }

    public int getColor() {
        return color;
    }

    public boolean getShiftKey() {
        return shiftKey;
    }

    public void setColor(int c) {
        color = c;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String m) {
        message = m;
        setBinary(message);
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDestMAC() {
        return destMAC;
    }

    public String getSourMAC() {
        return sourMAC;
    }

    public String getType() {
        return type;
    }

    public String getCRC() {
        return crc;
    }

    public String getPreamble() {
        return preamble;
    }

    public String getSFD() {
        return sfd;
    }

    public String getBinary() {
        return binary;
    }

    public float getAmplitude() {
        return amplitude;
    }

    public float getFrequency() {
        return frequency;
    }

    public PVector getACK() {
        return ack;
    }

    public PVector getACKEnd() {
        return ackEnd;
    }

    public PVector getACKStart() {
        return ackStart;
    }

    public void setSource(String s) {
        source = s;
    }

    public void setDestination(String d) {
        destination = d;
    }

    public void setDestMAC(String d) {
        destMAC = d;
    }

    public void setSourMAC(String s) {
        sourMAC = s;
    }

    public void setType(String t) {
        type = t;
    }

    public void setCRC(String c) {
        crc = c;
    }

    public void setPreamble(String p) {
        preamble = p;
    }

    public void setSFD(String s) {
        sfd = s;
    }

    public void setBinary(String m) {
        binary = preamble + sfd + destinationMAC + sourceMAC + calcBinary(m);
    }

    public void setAmplitude(int a) {
        amplitude = a;
    }

    public void setFrequency(int f) {
        frequency = f;
    }

    public void setShiftKey(boolean s) {
        shiftKey = s;
    }

    public void setACK(PVector v) {
        ack = v.get();
    }

    public void setACKEnd(PVector v) {
        ackEnd = v.get();
    }

    public void setACKStart(PVector v) {
        ackStart = v.get();
    }
    /*
     * end get and set methods
	 */

    public void display() {
        parent.fill(color);
        parent.strokeWeight(2);
        parent.stroke(0);
        parent.ellipse(position.x, position.y, width, height);
    }

    private String calcBinary(String m) {
        byte[] bytes = m.getBytes();
        StringBuilder binaryStr = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binaryStr.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binaryStr.toString();
    }

    /*
     * This method takes in a bit (0 or 1 as an integer, and prints out a sine wave for that bit with FSK or ASK
     * @param x and y -  this will be in the width and height of the rectangle to draw sine waves inside of
     * @param i - this will be the bit value passed in from the String of bits(most likely the binary variable
     */
    @SuppressWarnings("static-access")
    public void sineWave(int i, float x, float y) {
        PFont f = parent.createFont("Georgia", 16, true);
        parent.textFont(f);
        parent.textAlign(parent.CENTER, parent.CENTER);
        parent.stroke(0, 0, 0);
        parent.strokeWeight(1);

        float freq = frequency;
        float amp = amplitude;
        if (shiftKey) {
            // FSK
            // increment the incoming integer so that 1 = 2 and 0 = 1, to be used as multiples for frequency
            parent.fill(255, 0, 0);
            parent.text(i - 1, x + freq * i / 2, y + 85);
            freq *= i;
            parent.fill(225);
            parent.bezier(x, y, x + (freq / 3), y - amp, x + (freq * 2 / 3), y + amp, x + freq, y);

        } else {
            // ASK
            // increment the incoming integer so that 1 = 2 and 0 = 1, to be used as divisors for amplitude
            parent.fill(255, 0, 0);
            parent.text(i - 1, x + freq / 2, y + 85);
            amp *= (i - 1);
            parent.fill(225);
            parent.bezier(x, y, x + (freq / 3), y - amp, x + (freq * 2 / 3), y + amp, x + freq, y);

        }
    }

    public void drawSineWave(float x, float y) {
        int index;
        for (index = 0; index < binary.length(); index++) {
            int i = Character.getNumericValue(binary.charAt(index));
            i++;
            sineWave(i, x, y);
            if (shiftKey) {
                x += (frequency * i);
            } else {
                x += frequency;
            }
        }
    }

    @SuppressWarnings("static-access")
    public void digitalWave(int i, float x, float y, boolean next) {
        parent.stroke(0, 0, 0);
        parent.strokeWeight(1);
        PFont f = parent.createFont("Georgia", 16, true);
        parent.textFont(f);
        parent.textAlign(parent.CENTER, parent.CENTER);

        float h = amplitude / 2;
        float l = frequency;

        if (i == 0 && next) {
            parent.line(x, y, x + l, y);
            parent.fill(255, 0, 0);
            parent.text(i, x + l / 2, y + 30);
        } else if (i == 0 && !next) {
            parent.line(x, y, x + l, y);
            parent.line(x + l, y, x + l, y - h);
            parent.fill(255, 0, 0);
            parent.text(i, x + l / 2, y + 30);
        } else if (i == 1 && next) {
            parent.line(x, y - h, x + l, y - h);
            parent.fill(255, 0, 0);
            parent.text(i, x + l / 2, y + 30);
        } else if (i == 1 && !next) {
            parent.line(x, y - h, x + l, y - h);
            parent.line(x + l, y - h, x + l, y);
            parent.fill(255, 0, 0);
            parent.text(i, x + l / 2, y + 30);
        }
    }

    public void drawDigitalWave(float x, float y) {
        int index;
        boolean next = false; // is the next bit the same
        y += (amplitude / 4);
        for (index = 0; index < binary.length(); index++) {
            int i = Character.getNumericValue(binary.charAt(index));
            if (index < binary.length() - 1) {
                if (binary.charAt(index) == binary.charAt(index + 1)) {
                    next = true;
                } else {
                    next = false;
                }
            }
            digitalWave(i, x, y, next);
            x += frequency;
        }
    }

    public void displayACK() {
        parent.fill(color);
        parent.strokeWeight(1);
        parent.ellipse(ack.x, ack.y, width / 2, height / 2);
    }
}