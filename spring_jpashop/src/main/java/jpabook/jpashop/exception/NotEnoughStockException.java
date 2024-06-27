package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException{
    public NotEnoughStockException() {
        super();
    }

    public NotEnoughStockException(String message) {
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause) {
        //메시지와 근원 excetion 출력 -> exception trace 나오도록
        super(message, cause);
    }
    public NotEnoughStockException(Throwable cause) {
        super(cause);
    }

}
