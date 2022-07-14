package com.java.cn.thread.javaconcurrencyprogramingmul.tiker;

public class TestTicketWindow {

    public static void main(String[] args) {
        TicketWindow ticketWindow1 = new TicketWindow("一号机");
        ticketWindow1.start();

        TicketWindow ticketWindow2 = new TicketWindow("二号机");
        ticketWindow2.start();

        TicketWindow ticketWindow3 = new TicketWindow("三号机");
        ticketWindow3.start();

        TicketWindow ticketWindow4 = new TicketWindow("四号机");
        ticketWindow4.start();

        TicketWindow ticketWindow5 = new TicketWindow("五号机");
        ticketWindow5.start();
    }
}
