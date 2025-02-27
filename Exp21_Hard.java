import java.util.concurrent.locks.*;

class TicketBookingSystem {
    private boolean[] seats;
    private Lock lock;

    public TicketBookingSystem(int totalSeats) {
        seats = new boolean[totalSeats]; 
        lock = new ReentrantLock();
    }

    public boolean bookSeat(int seatNumber, String customer) {
        lock.lock();
        try {
            if (seatNumber < 0 || seatNumber >= seats.length || seats[seatNumber]) {
                return false; 
            }
            seats[seatNumber] = true;
            System.out.println(customer + " successfully booked seat " + seatNumber);
            return true;
        } finally {
            lock.unlock();
        }
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem system;
    private int seatNumber;
    private String customer;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customer, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customer = customer;
        setPriority(priority);
    }

    @Override
    public void run() {
        if (!system.bookSeat(seatNumber, customer)) {
            System.out.println(customer + " failed to book seat " + seatNumber);
        }
    }
}

public class Exp21_Hard {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem(10);

        Thread vip1 = new BookingThread(system, 2, "VIP Customer 1", Thread.MAX_PRIORITY);
        Thread vip2 = new BookingThread(system, 2, "VIP Customer 2", Thread.MAX_PRIORITY);
        Thread normal1 = new BookingThread(system, 2, "Normal Customer 1", Thread.NORM_PRIORITY);
        Thread normal2 = new BookingThread(system, 3, "Normal Customer 2", Thread.NORM_PRIORITY);

        vip1.start();
        vip2.start();
        normal1.start();
        normal2.start();
    }
}
