package cn.wwb.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import cn.wwb.cache.LoginCache;

/**
 * Application Lifecycle Listener implementation class LoginSessionListener
 *
 */
@WebListener
public class LoginSessionListener implements HttpSessionAttributeListener {
	
	private static final String LOGIN_USER = "name";

    /**
     * Default constructor. 
     */
    public LoginSessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent hsbe)  { 
         String attrName = hsbe.getName();
         if (LOGIN_USER.equals(attrName)) {
			String attrVal = (String)hsbe.getValue();
			HttpSession session = hsbe.getSession();
			String sessionId = session.getId();
			String sessionId2 = LoginCache.getInstance().getSessionIdByUsername(attrVal);
			if (sessionId2 == null) {
				
			}else {
				HttpSession session2 = LoginCache.getInstance().getSessionBySessionId(sessionId2);
				session2.invalidate();
			}
			
			LoginCache.getInstance().setSessionIdByUserName(attrVal, sessionId);
			LoginCache.getInstance().setSessionIdBySessionId(sessionId, session);
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
}
