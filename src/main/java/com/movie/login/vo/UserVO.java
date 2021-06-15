package com.movie.login.vo;

public class UserVO {
		private String userId;
		private String userName; 
		private String userPass;
		private String userRole;
		
		public UserVO() {
		}

		public UserVO(String userId, String userName, String userPass, String userRole) {
			super();
			this.userId = userId;
			this.userName = userName;
			this.userPass = userPass;
			this.userRole = userRole;
		}


		@Override
		public String toString() {
			return "UserVO [userId=" + userId + ", userPass=" + userPass + ", userName=" + userName + ", userRole="
					+ userRole + "]";
		}
			
		public String getUserId() {	
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getUserPass() {
			return userPass;
		}
		public void setUserPass(String userPass) {
			this.userPass = userPass;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserRole() {
			return userRole;
		}
		public void setUserRole(String userRole) {
			this.userRole = userRole;
		}
		
	}
