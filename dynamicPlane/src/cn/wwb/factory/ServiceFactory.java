package cn.wwb.factory;

import cn.wwb.service.ARREService;
import cn.wwb.service.AdminService;
import cn.wwb.service.BLLSService;
import cn.wwb.service.CANEService;
import cn.wwb.service.CKLSService;
import cn.wwb.service.DEPEService;
import cn.wwb.service.DFDLService;
import cn.wwb.service.DLYEService;
import cn.wwb.service.GTLSService;
import cn.wwb.service.STLSService;
import cn.wwb.service.impl.ARREServiceimpl;
import cn.wwb.service.impl.AdminServiceimpl;
import cn.wwb.service.impl.BLLSServiceimpl;
import cn.wwb.service.impl.CANEServiceimpl;
import cn.wwb.service.impl.CKLSServiceimpl;
import cn.wwb.service.impl.DEPEServiceimpl;
import cn.wwb.service.impl.DFDLServiceimpl;
import cn.wwb.service.impl.DLYEServiceimpl;
import cn.wwb.service.impl.GTLSServiceimpl;
import cn.wwb.service.impl.STLSServiceimpl;

public class ServiceFactory {
	public static AdminService getAdminServiceinstance(){
		return new AdminServiceimpl();
	}
	
	public static ARREService getARREServiceinstance(){
		return new ARREServiceimpl();
	}
	
	public static BLLSService getBLLSServiceinstance(){
		return new BLLSServiceimpl();
	}
	
	public static CANEService getCANEServiceinstance(){
		return new CANEServiceimpl();
	}
	
	public static CKLSService getCKLSServiceinstance(){
		return new CKLSServiceimpl();
	}
	
	public static DEPEService getDEPEServiceinstance(){
		return new DEPEServiceimpl();
	}
	
	public static DFDLService getDFDLServiceinstance(){
		return new DFDLServiceimpl();
	}
	
	public static DLYEService getDLYEServiceinstance(){
		return new DLYEServiceimpl();
	}
	
	public static GTLSService getGTLSServiceinstance(){
		return new GTLSServiceimpl();
	}
	
	public static STLSService getSTLSServiceinstance(){
		return new STLSServiceimpl();
	}
}
