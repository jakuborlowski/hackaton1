package models;

public enum Operations {
	ADD("add"), REMOVE("remove"), TRANSFER("transfer"), MULTIPLY("multiply");

	private String name;

	private Operations(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}

	Operations fromString(String name) {
		if (name.equals(ADD.name)) {
			return ADD;
		}

		if (name.equals(REMOVE.name)) {
			return REMOVE;
		}

		if (name.equals(TRANSFER.name)) {
			return TRANSFER;
		}

		if (name.equals(MULTIPLY.name)) {
			return MULTIPLY;
		}

		return null;
	}

}
