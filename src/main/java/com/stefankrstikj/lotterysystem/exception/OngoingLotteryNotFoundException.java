package com.stefankrstikj.lotterysystem.exception;

public class OngoingLotteryNotFoundException extends RuntimeException {
    public OngoingLotteryNotFoundException() {
        super("No ongoing lottery found");
    }
}
