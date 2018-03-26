package net.java.railway;

public interface Constants {

	/***************** CAHNGE ONLY BELOW VALUES ***************/
	public static final String COMPANY_DISPLAY_NAME = "Mumbai RailNet";

	public static final String SMS_IN_PORT = "COM9";
	public static final int SMS_IN_BAUD_RATE = 115200;// 921600;
	public static final boolean SMS_FLAG = true;
	public static final String DEMO_SMS_SENDER = "9930783001";
	public static final String DEMO_SMS_BODY = "TKT book KLYN CST 1";

	public static final String DEFAULT_MESSAGE = "You have entered wrong message, please send sms in proper format for details reply \"TKT HELP\"";
	public static final String HELP_MESSAGE = "Syntax for booking ticket is \n TKT BOOK <SOURCE> <DESTINATION> <NO OF TICKETS>";

	/***********************************************************/

	public static final String TOKEN_TICKET = "TKT";
	public static final String TOKEN_BOOK = "book";
	public static final String TOKEN_HELP = "help";
	public static final String TOKEN_BALANCE = "bal";

	public static final double MIN_BALANCE = 500d;
	public static final double MINIMUM_CARD_BALANCE = 0.0;
	public static final int ACCOUNT_TYPE_SMARTCARD = 1;

	public static final int TRANSACTION_TYPE_INVALID_ID = -1;
	public static final int TRANSACTION_TYPE_OPEN_ID = 1;
	public static final int TRANSACTION_TYPE_DEPOSIT_ID = 2;
	public static final int TRANSACTION_TYPE_WITHDRAW_ID = 3;
	public static final int TRANSACTION_TYPE_DELETE_ID = 4;

	public static final String TRANSACTION_TYPE_INVALID_NAME = "Invalid";
	public static final String TRANSACTION_TYPE_OPEN_NAME = "Open Account";
	public static final String TRANSACTION_TYPE_DEPOSIT_NAME = "Update Balance";
	public static final String TRANSACTION_TYPE_WITHDRAW_NAME = "Book Ticket";
	public static final String TRANSACTION_TYPE_DELETE_NAME = "Delete";

	public static final int FFN_ACCOUNT_ID = 1;
	public static final int FFN_TRANSACTION_ID = 2;
	public static final int FFN_TICKET_ID = 3;
	public static final int FFN_SMS_IN_ID = 4;
	public static final int FFN_SMS_OUT_ID = 5;

	// hhhhMMdd HHmmss : 20100113 235959
	public static final String DATE_PATTERN = "dd/MM/yy";
	public static final String DB_DATE_PATTERN = "yyyyMMddHHmmss";
	public static final String DISPLAY_DATE_PATTERN = "dd/MM/yy HH:mm:ss";

	public static final String MAP_INVALID_VALUE = "map_invalid_value";
	public static final String MAP_CUSTOMER_NAME = "map_customer_name";
	public static final String MAP_CUSTOMER_MOBILE = "map_customer_mobile";
	public static final String MAP_ACCOUNT_TYPE = "map_account_type";

	public static final String MAP_FROM_ADDRESS = "map_from_address";
	public static final String MAP_TO_ADDRESS = "map_to_address";

	enum TransactionType {
		CREDIT, DEBIT;
	};

	enum AccountRole {
		/**
		 * administrator of the portal
		 */
		ADMIN,
		/**
		 * End mobile user
		 */
		ENDUSER;
	};

	enum AccountStatus {
		/**
		 * account is deactivate
		 */
		INACTIVE,
		/**
		 * account is activated
		 */
		ACTIVE,

		/**
		 * account is deleted
		 */
		DELETED;
	};

	enum ProcessingStatus {
		/**
		 * intial insert
		 */
		INITAL,

		/**
		 * sms processing success
		 */
		SUCCESS,

		/**
		 * sms processing failed
		 */
		FAILED

	};

	enum SmsType {

		/**
		 * Incoming request sms
		 */
		REQUEST,

		/**
		 * Outgoing processed sms
		 */
		RESPONSE;

	};
}
