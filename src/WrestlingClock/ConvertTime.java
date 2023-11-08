package WrestlingClock;

public class ConvertTime {

	public long timeMS;
	public String enteredTime;

	public ConvertTime(String enteredTime) {
		this.enteredTime = enteredTime;
		this.timeMS = convertStringtoMS();
	}

	public long convertStringtoMS() {
		String[] arr = enteredTime.split(":");
		String minutes = arr[0];
		String seconds = arr[1];

		long ret = Constants.ONE_MINUTE_MS * Integer.parseInt(minutes);
		ret += Constants.ONE_SECOND_MS * (Integer.parseInt(seconds) + 1);
		return ret;
	}

	public String getEnteredTime() {
		return enteredTime;
	}

	public void setEnteredTime(String enteredTime) {
		this.enteredTime = enteredTime;
	}

	public long getTimeMS() {
		return timeMS;
	}

	public void setTimeMS(String enteredTime) {
		this.enteredTime = enteredTime;
	}

}
