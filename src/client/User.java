package client;

	import java.io.Serializable;

	public class User implements Serializable {
		private static final long serialVersionUID = -8518522418186109463L;
		private String name;
		private boolean online = false;
		private int size;

		public User(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setOnline(boolean online) {
			this.online = online;
		}

		public boolean isOnline() {
			return online;
		}

		@Override
		public String toString() {
			return name;
		}

		@Override
		public boolean equals(Object o) {
			// self check
			if (this == o) {
				return true;
			}

			if (!(o instanceof User)) {
				return false;
			}

			User u = (User) o;

			// field comparison
			return u.toString().equals(name);                                       
		}

	}