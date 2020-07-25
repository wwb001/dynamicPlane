package cn.wwb.factory;

import java.sql.Connection;

import cn.wwb.dao.ARREdao;
import cn.wwb.dao.Admindao;
import cn.wwb.dao.BLLSdao;
import cn.wwb.dao.CANEdao;
import cn.wwb.dao.CKLSdao;
import cn.wwb.dao.DEPEdao;
import cn.wwb.dao.DFDLdao;
import cn.wwb.dao.DLYEdao;
import cn.wwb.dao.GTLSdao;
import cn.wwb.dao.STLSdao;
import cn.wwb.dao.impl.ARREimpl;
import cn.wwb.dao.impl.Adminimpl;
import cn.wwb.dao.impl.BLLSimpl;
import cn.wwb.dao.impl.CANEimpl;
import cn.wwb.dao.impl.CKLSimpl;
import cn.wwb.dao.impl.DEPEimpl;
import cn.wwb.dao.impl.DFDLimpl;
import cn.wwb.dao.impl.DLYEimpl;
import cn.wwb.dao.impl.GTLSimpl;
import cn.wwb.dao.impl.STLSimpl;

public class DAOFactory {
	public static ARREdao getARREdaoinstance(Connection connection) {
		return new ARREimpl(connection);
	}
	public static BLLSdao getBLLSdaoinstance(Connection connection) {
		return new BLLSimpl(connection);
	}
	public static CANEdao getCANEdaoinstance(Connection connection) {
		return new CANEimpl(connection);
	}
	public static CKLSdao getCKLSdaoinstance(Connection connection) {
		return new CKLSimpl(connection);
	}
	public static DEPEdao getDEPEdaoinstance(Connection connection) {
		return new DEPEimpl(connection);
	}
	public static DFDLdao getDFDLdaoinstance(Connection connection) {
		return new DFDLimpl(connection);
	}
	public static DLYEdao getDLYEdaoinstance(Connection connection) {
		return new DLYEimpl(connection);
	}
	public static GTLSdao getGTLSdaoinstance(Connection connection) {
		return new GTLSimpl(connection);
	}
	public static STLSdao getSTLSdaoinstance(Connection connection) {
		return new STLSimpl(connection);
	}
	public static Admindao Admindaoinstance(Connection connection) {
		return new Adminimpl(connection);
	}
}
