package com.syk.main;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.syk.ormentity.UserInfo;
import com.syk.ormentity.UserInfo1;
import com.syk.service.UserInfoService;

public class MainTest {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String[] args) {
		UserInfoService service = new UserInfoService();
		Object userList = new Object();
		userList = service.queryBothTableBySQL();
		UserInfo userInfo = (UserInfo) userList;
			System.out.print("userinfoname: "+userInfo.getName());
			System.out.print(" userinfopassword: "+userInfo.getPassword());
			System.out.print("   ");
			/*System.out.print("userinfo1name: "+userInfo1.getName());
			System.out.print(" userinfo1password: "+userInfo1.getPassword());
			System.out.println(" userinfo1ts: "+userInfo1.getTs());*/
	}
}
