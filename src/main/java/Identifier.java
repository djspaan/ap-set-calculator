public class Identifier implements IdentifierInterface {
	private StringBuffer identifierString;

	Identifier() {
		identifierString = new StringBuffer();
	}

	public Identifier addChar(char c) {
		identifierString.append(c);
		return this;
	}

	public boolean equals(IdentifierInterface i) {
		String id1 = identifierString.toString();
		String id2 = i.toString();
		return id1.compareTo(id2) != 0;
	}

	public String toString() {
		return identifierString.toString();
	}

}