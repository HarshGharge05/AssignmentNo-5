package exceptions;

public class Exceptions {
    public static class InvalidAccountException extends Exception {
        private static final long serialVersionUID = 1L;

		public InvalidAccountException(String message) {
            super(message);
        }
    }

    public static class AccountNotFoundException extends Exception {
        private static final long serialVersionUID = 1L;

		public AccountNotFoundException(String message) {
            super(message);
        }
    }

    public static class InsufficientBalanceException extends Exception {
        private static final long serialVersionUID = 1L;

		public InsufficientBalanceException(String message) {
            super(message);
        }
    }
}
