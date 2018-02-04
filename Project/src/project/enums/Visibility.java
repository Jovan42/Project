package project.enums;

public enum Visibility {
	PUBLIC(0), UNLISTED(1), PRIVATE(2);
	
	private final int intValue;
	
	private Visibility(int intValue) {
		this.intValue = intValue;
	}
	
	public int getInt() {
		return intValue;
	}
}
