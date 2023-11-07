package geomex.notice.service;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import geomex.notice.mapper.UserMapper;
import geomex.notice.model.UserVo;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	 
	public int login(UserVo loginData, HttpSession session) {
        String userId=loginData.getUserId();
        String userPassword=loginData.getUserPassword();
		UserVo user = getUser(userId);
        //System.out.println("user:"+user); --> 이부분에서 null값이 계속 나와서 알고보니 카멜케이스 문제
        if (user != null && userPassword.equals(user.getUserPassword())) {
            session.setAttribute("userId", userId);
            return 1; // 로그인 성공
        } else {
            return 0; // 로그인 실패
        }
    }
	public UserVo getUser(String userId) //로그인을 할 때 password로 같이 체크해야하기 때문에 User전체를 들고온다.
	{
			UserVo user=userMapper.findByUserId(userId);
			if(user!=null)
			{
				return user;
			}
			else
			{
				return null;
			}
	}
	public String getUserId(String userId)
	{
			UserVo user=userMapper.findByUserId(userId);
			
			if(user!=null)
			{
				return user.getUserId();
			}
			else
			{
				return null;
			}
	}
	
	 public boolean register(UserVo uservo) {//@RequestBody 에 UserVo 를 하면 알아서 set으로 담아준다.
	        try {
	            userMapper.insertUser(uservo);
	            return true;
	        } catch (Exception e) {
	            //예외 발생시 
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public void logout(HttpSession session) {
	        session.invalidate();
	 }

	
}
