package pl.rkul.advanced_programming.increment_example.thread_interupting;

public class Holder {

    boolean isReadyToStop = false;

    public boolean isReadyToStop() {
        return isReadyToStop;
    }

    public void setReadyToStop(boolean readyToStop) {
        isReadyToStop = readyToStop;
    }
}
