package com.mattmalec.pterodactyl4j;

public enum DataType {

	MB((byte) 0, 1,       "Megabyte"),
	GB((byte) 1, 1024,    "Gigabyte"),
	TB((byte) 2, 1048576, "Terabyte");

	private final byte identifier;
	private final int mbValue;
	private final String friendlyName;

	DataType(byte identifier, int mbValue, String friendlyName) {
		this.identifier = identifier;
		this.mbValue = mbValue;
		this.friendlyName = friendlyName;
	}

	public static DataType getByIdentifier(byte identifier) {
		for(DataType dataType : values()) {
			if(dataType.getIdentifier() == identifier) {
				return dataType;
			}
		}
		return null;
	}

	public byte getIdentifier() {
		return identifier;
	}

	public int getMbValue() {
		return mbValue;
	}

	public String getFriendlyName() {
		return friendlyName;
	}

}
